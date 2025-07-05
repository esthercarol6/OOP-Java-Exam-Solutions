import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest {

    private Company company;
    private Location dummyLocation; // A dummy location for vehicle initialization

    @BeforeEach
    void setUp() {
        company = new Company();
        dummyLocation = new Location(0, 0); // Initialize a dummy location
        // Add some vehicles to the company for testing
        company.addVehicle(new Vehicle("Taxi1", 4, dummyLocation));
        company.addVehicle(new Vehicle("Shuttle1", 8, dummyLocation));
        company.addVehicle(new Vehicle("Taxi2", 2, dummyLocation));
    }

    @Test
    void testScheduleVehicleSuccess() {
        // Test case 1: Successfully schedule a vehicle
        // A passenger requiring 3 seats
        Passenger passenger1 = new Passenger(new Location(10, 20), new Location(50, 60), 3);
        boolean scheduled = company.scheduleVehicle(passenger1);
        assertTrue(scheduled, "Vehicle should be scheduled successfully for a valid request.");
        assertEquals(0, company.getLostFaresCount(), "No lost fares expected on successful scheduling.");
    }

    @Test
    void testScheduleVehicleNoAvailability() {
        // Test case 2: Attempt to schedule when no vehicles are available or have sufficient capacity
        // First, make all vehicles busy
        company.scheduleVehicle(new Passenger(new Location(1,1), new Location(2,2), 4)); // Takes Taxi1 (capacity 4)
        company.scheduleVehicle(new Passenger(new Location(3,3), new Location(4,4), 8)); // Takes Shuttle1 (capacity 8)
        company.scheduleVehicle(new Passenger(new Location(5,5), new Location(6,6), 2)); // Takes Taxi2 (capacity 2)

        // Now, try to schedule another passenger
        Passenger passenger2 = new Passenger(new Location(70, 80), new Location(10, 5), 1);
        boolean scheduled = company.scheduleVehicle(passenger2);
        assertFalse(scheduled, "Vehicle should not be scheduled if none are available.");
        assertEquals(1, company.getLostFaresCount(), "Lost fares count should increment when scheduling fails due to no availability.");
    }

    @Test
    void testScheduleVehicleInsufficientCapacity() {
        // Test case 3: Attempt to schedule a passenger requiring more seats than any available vehicle
        // Assume only a taxi (capacity 4) and a shuttle (capacity 8) are available.
        // Request for 10 seats
        Passenger passenger3 = new Passenger(new Location(5, 5), new Location(90, 90), 10);
        boolean scheduled = company.scheduleVehicle(passenger3);
        assertFalse(scheduled, "Vehicle should not be scheduled if no vehicle has enough capacity.");
        assertEquals(1, company.getLostFaresCount(), "Lost fares count should increment if no vehicle has sufficient capacity.");
    }

    @Test
    void testDriverDroppedOffPassengersMakesVehicleAvailable() {
        // Test case 4: Verify that driverDroppedOffPassengers makes a vehicle available again
        Passenger passenger = new Passenger(new Location(1,1), new Location(2,2), 4);
        company.scheduleVehicle(passenger); // This should take "Taxi1"

        // Simulate driver dropping off passengers
        company.driverDroppedOffPassengers("Taxi1");

        // Now, try to schedule another passenger that would require "Taxi1"
        Passenger passenger2 = new Passenger(new Location(3,3), new Location(4,4), 3);
        boolean scheduledAgain = company.scheduleVehicle(passenger2);
        assertTrue(scheduledAgain, "Vehicle should be available after dropping off passengers.");
        assertEquals(0, company.getLostFaresCount(), "No lost fares expected as vehicle should be available again.");
    }
}/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var Hello = React.createClass({
    render: function () {
        return (
                <div></div>
                );
    }
});
