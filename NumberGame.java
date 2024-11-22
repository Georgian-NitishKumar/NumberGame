import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final int MAX_ATTEMPTS = 7;
    private static final int RANGE_MAX = 100;
    
    static class GameStats {
        int totalScore = 0;
        int roundsPlayed = 0;
        
        void updateStats(int roundScore) {
            totalScore += roundScore;
            roundsPlayed++;
        }
        
        double getAverageScore() {
            return roundsPlayed > 0 ? (double) totalScore / roundsPlayed : 0;
        }
        
        void displayFinalStats() {
            System.out.println("\n=== Game Summary ===");
            System.out.println("Rounds Played: " + roundsPlayed);
            System.out.println("Total Score: " + totalScore);
            System.out.printf("Average Score: %.2f%n", getAverageScore());
        }
    }
    
    private static int playRound() {
        int targetNumber = random.nextInt(RANGE_MAX) + 1;
        int attempts = 0;
        
        System.out.println("\nI've picked a number between 1 and " + RANGE_MAX + "!");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.");
        
        while (attempts < MAX_ATTEMPTS) {
            try {
                System.out.print("\nEnter your guess: ");
                int guess = Integer.parseInt(scanner.nextLine());
                attempts++;
                
                if (guess == targetNumber) {
                    int score = MAX_ATTEMPTS - attempts + 1;
                    System.out.println("\nCongratulations! You got it in " + attempts + " attempts!");
                    return score;
                } else if (guess < targetNumber) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
                
                System.out.println("Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;
            }
        }
        
        System.out.println("\nGame Over! The number was " + targetNumber);
        return 0;
    }
    
    private static boolean playAgainPrompt() {
        while (true) {
            System.out.print("\nWould you like to play again? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (response.equals("yes") || response.equals("no")) {
                return response.equals("yes");
            }
            System.out.println("Please enter 'yes' or 'no'");
        }
    }
    
    public static void main(String[] args) {
        GameStats stats = new GameStats();
        
        System.out.println("Welcome to the Number Guessing Game!");
        
        do {
            stats.roundsPlayed++;
            System.out.println("\n=== Round " + stats.roundsPlayed + " ===");
            
            int roundScore = playRound();
            stats.updateStats(roundScore);
            
            System.out.println("\nCurrent Score: " + stats.totalScore);
            System.out.printf("Average Score: %.2f%n", stats.getAverageScore());
            
        } while (playAgainPrompt());
        
        stats.displayFinalStats();
        scanner.close();
    }
}