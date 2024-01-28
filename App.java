import java.util.List;

public class App {

  public static void main(String[] args) throws Exception {
    FlightGraph flightGraph = new FlightGraphImpl();

    flightGraph.addAirport("JFK", "John F. Kennedy International Airport");
    flightGraph.addAirport("LAX", "Los Angeles International Airport");
    flightGraph.addAirport("SFO", "San Francisco International Airport");

    flightGraph.addFlight(
        "JFK",
        "LAX",
        "AA123",
        "10:00 AM",
        "1:00 PM",
        10,
        220.0);
    flightGraph.addFlight(
        "JFK",
        "LAX",
        "DL456",
        "2:00 PM",
        "5:00 PM",
        10,
        210.0);
    flightGraph.addFlight(
        "LAX",
        "SFO",
        "FJ932",
        "3:00 PM",
        "6:00 PM",
        10,
        180.0);
    flightGraph.addFlight(
        "LAX",
        "SFO",
        "UA789",
        "3:00 PM",
        "6:00 PM",
        10,
        160.0);

    List<AvailableFlight> availableFlights = flightGraph.searchForFlights(
        "JFK",
        "SFO",
        "2023-12-07");

    System.out.println("Available Flights from JFK to LAX on 2023-12-07:");
    for (AvailableFlight flight : availableFlights) {
      System.out.println(
          "Flight Number: " +
              flight.getFlightNumber() +
              "\nDeparture Time: " +
              flight.getDepartureTime() +
              "\nArrival Time: " +
              flight.getArrivalTime() +
              "\nPrice: $" +
              flight.getPrice());
    }

    boolean bookingResult = flightGraph.bookFlight("AA123", "John Doe", 1); // Assuming 0 as the seat number

    if (bookingResult) {
      System.out.println("Booking successful!");
    } else {
      System.out.println("Booking unsuccessful. No available seats.");
    }

    boolean deletionResult = flightGraph.deleteFlight("DL456");

    if (deletionResult) {
      System.out.println("Flight DL456 deleted successfully.");
    } else {
      System.out.println("Flight DL456 not found. Deletion unsuccessful.");
    }
  }
}
