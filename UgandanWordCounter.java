import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the book description:");
        String bookDescription = scanner.nextLine();

        // The word to search for
        String targetWord = "Uganda";
        int count = 0;

        // Convert the entire book description to lowercase for case-insensitive matching
        String lowerCaseDescription = bookDescription.toLowerCase();
        // Convert the target word to lowercase as well for consistent comparison
        String lowerCaseTargetWord = targetWord.toLowerCase();

        // Split the description into words. Using a regex for common word delimiters.
        // \\b is a word boundary. This ensures "Uganda" in "Ugandan" isn't counted.
        // It's more robust than just split(" ").
        String[] words = lowerCaseDescription.split("\\b"); // Splits by word boundaries

        for (String word : words) {
            // Trim whitespace and check if the word matches our target word
            if (word.trim().equals(lowerCaseTargetWord)) {
                count++;
            }
        }

        System.out.println("The word \"" + targetWord + "\" appears " + count + " time(s) in the description.");

        scanner.close();
    }
}

