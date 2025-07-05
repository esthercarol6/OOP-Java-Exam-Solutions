import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MedicalLogReader {

    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);
        System.out.print("Enter the path to the medical log file (e.g., medical_log.txt): ");
        String filePath = consoleScanner.nextLine();

        // Create a File object representing the medical log file
        File medicalLogFile = new File(filePath);

        // Use try-with-resources for automatic closing of BufferedReader and FileReader
        // This is the most modern and recommended way to handle closable resources.
        try (BufferedReader reader = new BufferedReader(new FileReader(medicalLogFile))) {
            System.out.println("\n--- Content of Medical Log File: " + medicalLogFile.getName() + " ---");
            String line;
            // Read line by line until the end of the file (reader.readLine() returns null)
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Display each line on the screen
            }
            System.out.println("--- End of File ---");

        } catch (FileNotFoundException e) {
            // This catch block handles the specific case where the file does not exist.
            System.err.println("Error: The specified medical log file was not found.");
            System.err.println("Path attempted: " + medicalLogFile.getAbsolutePath());
            System.err.println("Details: " + e.getMessage());
        } catch (IOException e) {
            // This catch block handles any other general I/O errors that might occur
            // during reading (e.g., permission issues, disk read errors).
            System.err.println("An I/O error occurred while reading the medical log file.");
            System.err.println("Details: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
        } finally {
            // The try-with-resources statement handles the closing,
            // so a separate finally block for closing is often not needed here.
            // However, if we were not using try-with-resources,
            // a finally block would be essential for closing resources.
            // For example, if BufferedReader and FileReader were declared outside try.
            consoleScanner.close(); // Close the console scanner here
            System.out.println("Program finished. Resources closed.");
        }
    }
}













