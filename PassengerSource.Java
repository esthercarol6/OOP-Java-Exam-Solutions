import java.util.Random;

// Company, Passenger, and Location classes assumed to exist
// Placeholder for Location class
class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

// Placeholder for Passenger class
class Passenger {
    private Location pickupLocation;
    private Location destinationLocation;
    private int numberOfSeats; // Added based on case study "number of seats required"

    public Passenger(Location pickupLocation, Location destinationLocation, int numberOfSeats) {
        this.pickupLocation = pickupLocation;
        this.destinationLocation = destinationLocation;
        this.numberOfSeats = numberOfSeats;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public Location getDestinationLocation() {
        return destinationLocation;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}

// Placeholder for Company class - simplified for PassengerSource to compile
class Company {
    // This is a simplified representation. In a real scenario,
    // it would have a list of vehicles, logic for scheduling, etc.
    public boolean scheduleVehicle(Passenger passenger) {
        // For demonstration, let's randomly succeed or fail.
        // In a real system, this would check vehicle availability.
        Random random = new Random();
        boolean success = random.nextBoolean(); // 50% chance of success
        if (success) {
            System.out.println("Company: Successfully scheduled vehicle for pickup from " +
                               passenger.getPickupLocation() + " to " +
                               passenger.getDestinationLocation() + " for " +
                               passenger.getNumberOfSeats() + " seats.");
        } else {
            System.out.println("Company: Failed to schedule vehicle for pickup from " +
                               passenger.getPickupLocation() + " to " +
                               passenger.getDestinationLocation() + ". No vehicles available.");
            // As per case study: "If no vehicles are available, the request is lost without queuing." [cite: 7]
        }
        return success;
    }
}

// The actual PassengerSource class
public class PassengerSource {
    private Company company;
    private Random random;

    /**
     * Constructor for PassengerSource.
     * @param company The Company object responsible for scheduling vehicles.
     */
    public PassengerSource(Company company) {
        this.company = company;
        this.random = new Random();
    }

    /**
     * Creates a new Passenger, generates random pickup and destination Location objects,
     * and uses the company to schedule a vehicle.
     * @param numberOfSeats The number of seats required for the passenger(s).
     * @return true if the company successfully schedules the pickup, false otherwise.
     */
    public boolean requestPickup(int numberOfSeats) {
        // Generate random x and y coordinates between 0 and 100 for pickup
        int pickupX = random.nextInt(101); // 0 to 100 inclusive
        int pickupY = random.nextInt(101);
        Location pickupLocation = new Location(pickupX, pickupY);

        // Generate random x and y coordinates between 0 and 100 for destination
        int destinationX = random.nextInt(101);
        int destinationY = random.nextInt(101);
        Location destinationLocation = new Location(destinationX, destinationY);

        // Create a new Passenger object
        Passenger newPassenger = new Passenger(pickupLocation, destinationLocation, numberOfSeats);

        // Use the company to schedule a vehicle
        return company.scheduleVehicle(newPassenger);
    }

    public static void main(String[] args) {
        // Example Usage:
        Company balextranitCompany = new Company();
        PassengerSource individualPassenger = new PassengerSource(balextranitCompany);

        System.out.println("--- Testing PassengerSource requests ---");

        // Simulate a few pickup requests
        System.out.println("Request 1 (1 seat): " + (individualPassenger.requestPickup(1) ? "Scheduled" : "Not Scheduled"));
        System.out.println("Request 2 (3 seats): " + (individualPassenger.requestPickup(3) ? "Scheduled" : "Not Scheduled"));
        System.out.println("Request 3 (2 seats): " + (individualPassenger.requestPickup(2) ? "Scheduled" : "Not Scheduled"));
        System.out.println("Request 4 (4 seats): " + (individualPassenger.requestPickup(4) ? "Scheduled" : "Not Scheduled"));
    }
}

