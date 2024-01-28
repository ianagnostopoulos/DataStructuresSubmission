public class AirportTest {

    public static void main(String[] args) {
        testAirportOperations();
    }

    public static void testAirportOperations() {
        Airport airport = new Airport("ABC", "Test Airport");
        assert airport.getAirportCode().equals("ABC");
        assert airport.getAirportName().equals("Test Airport");

        Flight flight1 = new Flight("F123", "10:00 AM", "1:00 PM", 10, 220.0, null, null);
        Flight flight2 = new Flight("F456", "2:00 PM", "5:00 PM", 10, 210.0, null, null);
        airport.addFlight(flight1);
        airport.addFlight(flight2);
        assert airport.getFlights().size() == 2;

        assert airport.hasFlight("F123");
        assert !airport.hasFlight("NonExistentFlight");

        Flight destinationFlight = airport.getFlightTo("XYZ");
        assert destinationFlight == null;

        assert airport.removeFlight("F123");
        assert airport.getFlights().size() == 1;

        assert !airport.removeFlight("NonExistentFlight");
        assert airport.getFlights().size() == 1;

        System.out.println("Airport operations tests passed!");
    }
}
