import java.util.Arrays;
import java.util.Random;

public class AQIAnalysis {

    public static void main(String[] args) {
        final int NUMBER_OF_READINGS = 30;
        int[] aqiReadings = new int[NUMBER_OF_READINGS];
        Random random = new Random();

        // i) Generates 30 random air quality index (AQI) readings between 1 and 300.
        System.out.println("--- Generating AQI Readings ---");
        for (int i = 0; i < NUMBER_OF_READINGS; i++) {
            // nextInt(max - min + 1) + min  for range [min, max]
            aqiReadings[i] = random.nextInt(300) + 1; // Generates between 1 and 300
            System.out.printf("Day %2d AQI: %d%n", (i + 1), aqiReadings[i]);
        }

        // ii) Computes and displays the median AQI value.
        System.out.println("\n--- Calculating Median AQI ---");
        // To find the median, first sort the array
        Arrays.sort(aqiReadings);

        double medianAQI;
        int middle = aqiReadings.length / 2;

        if (aqiReadings.length % 2 == 1) {
            // If the number of readings is odd, median is the middle element
            medianAQI = aqiReadings[middle];
        } else {
            // If the number of readings is even, median is the average of the two middle elements
            medianAQI = (aqiReadings[middle - 1] + aqiReadings[middle]) / 2.0;
        }
        System.out.println("Sorted AQI Readings: " + Arrays.toString(aqiReadings));
        System.out.printf("Median AQI Value: %.2f%n", medianAQI);


        // iii) Identifies and counts the number of "hazardous" days (AQI > 200).
        System.out.println("\n--- Identifying Hazardous Days ---");
        int hazardousDaysCount = 0;
        System.out.println("Hazardous AQI Readings (AQI > 200):");
        for (int i = 0; i < NUMBER_OF_READINGS; i++) {
            if (aqiReadings[i] > 200) {
                hazardousDaysCount++;
                System.out.printf("Day %2d: AQI = %d (Hazardous)%n", (i + 1), aqiReadings[i]);
            }
        }
        System.out.println("\nTotal number of hazardous days (AQI > 200): " + hazardousDaysCount);
    }
}
