import java.util.Scanner;

public class Quiz {

    public static void start(String category) {
        APIClient apiClient = new APIClient();
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        int numOfQuestions = 0;
        boolean continueQuiz = true;

        while (continueQuiz) {
            System.out.println("Generating new question...");
            Question question = apiClient.generatePrompt(category);
            System.out.println("Question: " + question.question);
            for (String option : question.options) {
                System.out.println(option);
            }
            System.out.println("Your answer: A/B/C/D");
            String answer = scanner.next().toUpperCase();

            if (question.correctAnswer.indexOf(answer) == 0) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer is: " + question.correctAnswer);
            }
            numOfQuestions++;

            System.out.println("Your current score: " + score + "/" + numOfQuestions);

            System.out.println("Do you want another question?");
            if (scanner.next().toLowerCase().equals("yes")) {
                continueQuiz = true;
            } else {
                continueQuiz = false;
            }
        }

        System.out.println("Your score: " + score + "/" + numOfQuestions);
        System.out.println("Thanks for playing!");
    }
}
