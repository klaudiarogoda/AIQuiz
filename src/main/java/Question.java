public class Question {
    String question;
    String[] options;
    String correctAnswer;

    public Question(String question, String[] options, String correctAnswer) {
        this.correctAnswer = correctAnswer;
        this.options = options;
        this.question = question;
    }
}
