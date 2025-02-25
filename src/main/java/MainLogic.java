import java.util.Scanner;

public class MainLogic {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Welcome to the Quiz! Your questions are generated with AI. Please enter desired category: ");
        String category = scanner.next();
        Quiz.start(category);
    }
}
