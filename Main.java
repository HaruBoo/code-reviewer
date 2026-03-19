import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {

        String input = Files.readString(Path.of(args[0]));

        input = input.replace("\\", "\\\\")
                     .replace("\"", "\\\"")
                     .replace("\n", "\\n")
                     .replace("\r", "\\r")
                     .replace("\t", "\\t");

        System.out.println("📂 読み込んだファイル：" + args[0]);

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

        String marker = "\"text\":\"";
        int startIndex = body.indexOf(marker) + marker.length();
        int endIndex = body.indexOf("\"}]", startIndex);
        String reviewText = body.substring(startIndex, endIndex);

        reviewText = reviewText.replace("\\n", "\n");
        reviewText = reviewText.replace("\\\"", "\"");
        reviewText = reviewText.replace("\\t", "\t");

        System.out.println("\n📝 レビュー結果：");
        System.out.println(reviewText);
        
    }
}