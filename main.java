import java.util.Scanner;
import java.util.Random;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int secretNumber = random.nextInt(100) + 1; // 1-100
        System.out.print("Guess a number between 1-100: ");
            int guess = scanner.nextInt();

        scanner.close();
    }
}