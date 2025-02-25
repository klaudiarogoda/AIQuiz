import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIClient {

    String url =
            "https://aiplatform.googleapis.com/v1/publishers/google/models/gemini-2.0-flash-001:generateContent?key=AQ.Ab8RN6LfmZQwoecIEB8DBaso1Pa7Megx-axD59UBVHpb4TzSTQ";

    Question generatePrompt(String category) {
        String requestBody =
                "{ \"contents\": [ { \"role\": \"user\", \"parts\": [ { \"text\": \"Generate a multiple-choice "
                        + "question about "
                        + category
                        + " with four options. Indicate the correct answer. Answer only with JSON. "
                        + "The Json should have following fields: question of type string that contains "
                        + "a question, answers which is an array of length 4 of answers to take (the answers "
                        + "should be enumerated with A, B, C and D), and correctAnswer, which is the zero-based "
                        + "index of correct answer.\" } ] } ] }";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build();
        String output = "";
        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            output = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        QuestionParser questionParser = new QuestionParser();
        Question question = questionParser.parseQuestion(output);

        return question;
    }
}
