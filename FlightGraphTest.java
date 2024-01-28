import java.util.List;

public class FlightGraphTest {

    public static void main(String[] args) throws Exception {
        testFlightGraphOperations();
    }

    public static void testFlightGraphOperations() throws Exception {
        FlightGraph flightGraph = new FlightGraphImpl();
        flightGraph.addAirport("JFK", "John F. Kennedy International Airport");
        flightGraph.addAirport("LAX", "Los Angeles International Airport");
        flightGraph.addAirport("SFO", "San Francisco International Airport");

        flightGraph.addFlight("JFK", "LAX", "AA123", "10:00 AM", "1:00 PM", 10, 220.0);
        flightGraph.addFlight("JFK", "LAX", "DL456", "2:00 PM", "5:00 PM", 10, 210.0);
        flightGraph.addFlight("LAX", "SFO", "FJ932", "3:00 PM", "6:00 PM", 10, 180.0);
        flightGraph.addFlight("LAX", "SFO", "UA789", "3:00 PM", "6:00 PM", 10, 160.0);

        List<AvailableFlight> availableFlights = flightGraph.searchForFlights("JFK", "SFO", "2023-12-07");

        assert availableFlights.size() > 0;

        boolean bookingResult = flightGraph.bookFlight("AA123", "John Doe", 1);

        assert bookingResult;

        boolean deletionResult = flightGraph.deleteFlight("DL456");

        assert deletionResult;

        System.out.println("FlightGraph operations tests passed!");

    }
}
