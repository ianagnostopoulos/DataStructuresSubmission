import java.util.List;

public interface FlightGraph {
    void addAirport(String airportCode, String airportName);

    void addFlight(String departureAirport, String destinationAirport, String flightNumber, String departureTime,
            String arrivalTime, int totalSeats, double price);

    List<AvailableFlight> searchForFlights(String departureAirport, String destinationAirport, String date);

    boolean bookFlight(String flightNumber, String passengerName, int seatNumber);

    boolean deleteFlight(String flightNumber);

}