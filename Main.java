import java.net.http.HttpClient;   // 入力装置を使いますよ(全てクラスの呼び出し)
import java.net.http.HttpRequest;  // HTTP通信装置を使いますよ
import java.net.http.HttpResponse; // HTTPリクエストを使いますよ
import java.net.URI;               // HTTPレスポンスを使いますよ
import java.nio.file.Files;          // URL/URIを使いますよ
import java.nio.file.Path;
public class Main { //この2行は、JavaはMainから始まる、おまじない的に必ず書く
    public static void main(String[] args) throws Exception {
        
        String input = Files.readString(Path.of(args[0]));

        System.out.println("📂 読み込んだファイル：" + args[0]);
        System.out.println(input);

        String apiKey = System.getenv("ANTHROPIC_API_KEY");

        String jsonBody = """
                {
                    "model": "claude-sonnet-4-20250514",
                    "max_tokens": 1024,
                    "messages": [
                        {
                            "role": "user",
                            "content": "以下のJavaコードをレビューしてください。改善点を日本語で教えてください：\\n%s"
                        }
                    ]
                }
                """.formatted(input);

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

        int startIndex = body.indexOf("\"text\":\"") + 8;
        int endIndex = body.lastIndexOf("\"type\":\"text\"") -2;
        String reviewText = body.substring(startIndex, endIndex);

        reviewText = reviewText.replace("\\n", "\n");
        reviewText = reviewText.replace("\\\"", "\"");

        System.out.println("\n📝 レビュー結果：");
        System.out.println(reviewText);

    }
}