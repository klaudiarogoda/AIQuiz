import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class QuestionParser {
    public Question parseQuestion(String jsonResponse) {
        System.out.println(jsonResponse);
        try {
            Gson gson = new Gson();
            JsonObject rootNode = gson.fromJson(jsonResponse, JsonObject.class);
            JsonObject contentNode =
                    rootNode.getAsJsonArray("candidates")
                            .get(0)
                            .getAsJsonObject()
                            .getAsJsonObject("content")
                            .getAsJsonArray("parts")
                            .get(0)
                            .getAsJsonObject();

            String jsonText =
                    contentNode.get("text").getAsString().replaceAll("```json|```", "").trim();
            JsonObject questionNode = gson.fromJson(jsonText, JsonObject.class);

            String question = questionNode.get("question").getAsString();
            JsonArray answersArray = questionNode.getAsJsonArray("answers");
            String[] options = new String[answersArray.size()];
            for (int i = 0; i < answersArray.size(); i++) {
                options[i] = answersArray.get(i).getAsString();
            }
            int correctAnswerIndex = questionNode.get("correctAnswer").getAsInt();
            String correctAnswer = options[correctAnswerIndex];

            return new Question(question, options, correctAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
