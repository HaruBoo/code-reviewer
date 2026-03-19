import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.out.println("使い方：java Main.java <ファイル名>");
            System.out.println("例：java Main.java Sample.java");
            return;
        }

        Path filePath = Path.of(args[0]);

        if (!Files.exists(filePath)) {
            System.out.println("❌ ファイルが見つかりません：" + args[0]);
            return;
        }

        String input = Files.readString(filePath);

        input = input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");

        System.out.println("📂 読み込んだファイル：" + args[0]);

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nレビュー観点を選んでください：");
        System.out.println("1. 総合レビュー");
        System.out.println("2. セキュリティ");
        System.out.println("3. パフォーマンス");
        System.out.println("4. 可読性・命名規則");
        System.out.print("番号を入力：");

        String choice = scanner.nextLine();

        String reviewPrompt;

        if (choice.equals("1")) {
            reviewPrompt = "以下のJavaコードを総合的にレビューしてください。改善点を日本語で教えてください：\\n";
        } else if (choice.equals("2")) {
            reviewPrompt = "以下のJavaコードをセキュリティの観点でレビューしてください。脆弱性や危険な箇所を日本語で教えてください：\\n";
        } else if (choice.equals("3")) {
            reviewPrompt = "以下のJavaコードをパフォーマンスの観点でレビューしてください。処理速度やメモリ効率の改善点を日本語で教えてください：\\n";
        } else if (choice.equals("4")) {
            reviewPrompt = "以下のJavaコードの可読性と命名規則をレビューしてください。変数名やコード構造の改善点を日本語で教えてください：\\n";
        } else {
            reviewPrompt = "以下のJavaコードを総合的にレビューしてください。改善点を日本語で教えてください：\\n";
        }

        String apiKey = System.getenv("ANTHROPIC_API_KEY");

        if (apiKey == null) {
            System.out.println("❌ APIキーが設定されていません");
            System.out.println("export ANTHROPIC_API_KEY=\"your-key\" を実行してください");
            return;
        }

        String jsonBody = """
                {
                    "model": "claude-sonnet-4-20250514",
                    "max_tokens": 1024,
                    "messages": [
                        {
                            "role": "user",
                            "content": "%s%s"
                        }
                    ]
                }
                """.formatted(reviewPrompt, input);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.anthropic.com/v1/messages"))
                .header("Content-Type", "application/json")
                .header("x-api-key", apiKey)
                .header("anthropic-version", "2023-06-01")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        System.out.println("🔍 レビュー中...");

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();

        String marker = "\"text\":\"";
        int startIndex = body.indexOf(marker) + marker.length();
        int endIndex = body.indexOf("\"}]", startIndex);
        String reviewText = body.substring(startIndex, endIndex);

        reviewText = reviewText.replace("\\n", "\n");
        reviewText = reviewText.replace("\\\"", "\"");
        reviewText = reviewText.replace("\\t", "\t");

        System.out.println("\n📝 レビュー結果：");
        System.out.println(reviewText);

        scanner.close();

    }
}