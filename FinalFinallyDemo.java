final class Constants {
    public static final double PI = 3.1416;
}

// finally block
try {
    Scanner sc = new Scanner(new File("report.txt"));
} catch (FileNotFoundException e) {
    System.out.println("File not found.");
} finally {
    System.out.println("Closing file access.");
}