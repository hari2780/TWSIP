import java.util.Scanner;
public class NumberGuessingGame {
    public static void main(String[] args) {
        int randomNumber = (int) (Math.random() * 100) + 1;
        Scanner scanner = new Scanner(System.in);
        int guess;
        int numberOfGuesses = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Try to guess the number between 1 and 100.");

        do {
            System.out.println("Enter your guess: ");
            guess = scanner.nextInt();
            numberOfGuesses++;

            if (guess < randomNumber) {
                System.out.println("Too low! Try again.");
            } else if (guess > randomNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You guessed the number in " + numberOfGuesses + " guesses.");
            }
        } while (guess != randomNumber);
    }
}
