import java.util.*;

public class FlightGraphImpl implements FlightGraph {

  private Map<String, Airport> airports;
  List<Flight> flights;

  public FlightGraphImpl() {
    this.airports = new HashMap<>();
    this.flights = new ArrayList<>();
  }

  @Override
  public void addAirport(String airportCode, String airportName) {
    Airport airport = new Airport(airportCode, airportName);
    airports.put(airportCode, airport);
  }

  @Override
  public void addFlight(
      String departureAirport,
      String destinationAirport,
      String flightNumber,
      String departureTime,
      String arrivalTime,
      int totalSeats,
      double price) {
    Airport departure = airports.get(departureAirport);
    Airport destination = airports.get(destinationAirport);

    if (departure != null && destination != null) {
      Flight flight = new Flight(
          flightNumber,
          departureTime,
          arrivalTime,
          totalSeats,
          price,
          destination,
          departure);
      departure.addFlight(flight);
    }
  }

  @Override
  public List<AvailableFlight> searchForFlights(
      String departureAirport,
      String destinationAirport,
      String date) {
    Map<String, Double> totalPrices = new HashMap<>();
    Map<String, String> previousNodes = new HashMap<>();
    PriorityQueue<String> priorityQueue = new PriorityQueue<>(
        Comparator.comparingDouble(totalPrices::get));

    for (String airportCode : airports.keySet()) {
      totalPrices.put(airportCode, Double.POSITIVE_INFINITY);
      previousNodes.put(airportCode, null);
      priorityQueue.add(airportCode);
    }

    totalPrices.put(departureAirport, 0.0);

    while (!priorityQueue.isEmpty()) {
      String currentAirport = priorityQueue.poll();

      Airport current = airports.get(currentAirport);

      for (Flight flight : current.getFlights()) {
        double newPrice = totalPrices.get(currentAirport) + flight.getPrice();

        if (newPrice < totalPrices.get(flight.getDestination().getAirportCode())) {
          totalPrices.put(flight.getDestination().getAirportCode(), newPrice);
          previousNodes.put(
              flight.getDestination().getAirportCode(),
              currentAirport);
          priorityQueue.add(flight.getDestination().getAirportCode());
        }
      }
    }

    System.out.println("Total Prices: " + totalPrices);
    System.out.println("Previous Nodes: " + previousNodes);

    List<String> path = new ArrayList<>();
    String current = destinationAirport;
    while (current != null) {
      path.add(current);
      current = previousNodes.get(current);
    }

    System.out.println("Reconstructed Path: " + path);

    Collections.reverse(path);

    List<AvailableFlight> result = new ArrayList<>();
    for (int i = 0; i < path.size() - 1; i++) {
      String currentAirport = path.get(i);

      Airport currentAport = airports.get(currentAirport);

      String nextAirport = path.get(i + 1);

      Flight connectingFlight = null;
      for (Flight flight : currentAport.getFlights()) {
        if (flight.getDestination().getAirportCode().equals(nextAirport)) {
          connectingFlight = flight;
          break;
        }
      }

      result.add(
          new AvailableFlight(
              connectingFlight.getFlightNumber(),
              connectingFlight.getDepartureTime(),
              connectingFlight.getArrivalTime(),
              connectingFlight.getPrice()));
    }
    return result;
  }

  @Override
  public boolean bookFlight(
      String flightNumber,
      String passengerName,
      int seatNumber) {
    for (Airport airport : airports.values()) {
      for (Flight flight : airport.getFlights()) {
        if (flight.getFlightNumber().equals(flightNumber)) {
          return flight.bookSeat(new Passenger(passengerName, seatNumber));
        }
      }
    }
    return false;
  }

  @Override
  public boolean deleteFlight(String flightNumber) {
    for (Airport airport : airports.values()) {
      if (airport.removeFlight(flightNumber)) {
        return true;
      }
    }
    return false;
  }
}
