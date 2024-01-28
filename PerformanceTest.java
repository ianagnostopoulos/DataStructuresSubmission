import java.util.Random;

public class PerformanceTest {

    public static void main(String[] args) {
        FlightGraphImpl flightGraph = new FlightGraphImpl();

        generateDummyData(flightGraph, 1000);

        measureSearchPerformance(flightGraph, "JFK", "LAX");
        measureSearchPerformance(flightGraph, "LAX", "SFO");
        measureSearchPerformance(flightGraph, "SFO", "JFK");
    }

    private static void generateDummyData(FlightGraphImpl flightGraph, int dataSize) {
        Random random = new Random();

        for (int i = 0; i < dataSize; i++) {
            String departureAirport = "Airport" + i;
            String destinationAirport = "Airport" + (i + 1);
            String flightNumber = "Flight" + i;
            String departureTime = "10:00 AM";
            String arrivalTime = "1:00 PM";
            int totalSeats = 10;
            double price = random.nextDouble() * 300.0;

            flightGraph.addAirport(departureAirport, "Airport " + i);
            flightGraph.addAirport(destinationAirport, "Airport " + (i + 1));
            flightGraph.addFlight(departureAirport, destinationAirport, flightNumber, departureTime, arrivalTime,
                    totalSeats, price);
        }
    }

    private static void measureSearchPerformance(FlightGraphImpl flightGraph, String departure, String destination) {
        long startTime = System.currentTimeMillis();

        flightGraph.searchForFlights(departure, destination, "2023-12-07");

        long endTime = System.currentTimeMillis();

        System.out
                .println("Search from " + departure + " to " + destination + " took: " + (endTime - startTime) + " ms");
    }
}
