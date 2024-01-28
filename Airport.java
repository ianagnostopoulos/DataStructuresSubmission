import java.util.ArrayList;
import java.util.List;

public class Airport {
    private String airportCode;
    private String airportName;
    private List<Flight> flights;

    public Airport(String airportCode, String airportName) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.flights = new ArrayList<>();
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public boolean hasFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return true;
            }
        }
        return false;
    }

    public Flight getFlightTo(String destinationAirportCode) {
        for (Flight flight : flights) {
            if (flight.getDestination().getAirportCode().equals(destinationAirportCode)) {
                return flight;
            }
        }
        return null;
    }

    public boolean removeFlight(String flightNumber) {
        Flight flightToRemove = null;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                flightToRemove = flight;
                break;
            }
        }
        if (flightToRemove != null) {
            flights.remove(flightToRemove);
            return true;
        }
        return false;
    }
}