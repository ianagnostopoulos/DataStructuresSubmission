import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlightGraphImplTest {

    private FlightGraphImpl flightGraph;

    @BeforeEach
    void setUp() {
        flightGraph = new FlightGraphImpl();
    }

    @Test
    void testAddAirport() {
        String airportCode = "JFK";
        String airportName = "John F. Kennedy International Airport";

        flightGraph.addAirport(airportCode, airportName);

        assertTrue(flightGraph.getAirports().containsKey(airportCode));
        assertEquals(airportName, flightGraph.getAirports().get(airportCode).getAirportName());
    }

    @Test
    void testAddFlight() {
        String departureAirport = "JFK";
        String destinationAirport = "LAX";
        String flightNumber = "AA123";
        String departureTime = "10:00 AM";
        String arrivalTime = "1:00 PM";
        int totalSeats = 10;
        double price = 220.0;

        flightGraph.addAirport(departureAirport, "John F. Kennedy International Airport");
        flightGraph.addAirport(destinationAirport, "Los Angeles International Airport");
        flightGraph.addFlight(departureAirport, destinationAirport, flightNumber, departureTime, arrivalTime,
                totalSeats, price);

         assertEquals(flightNumber, flightGraph.getFlights().get(0).getFlightNumber());
        assertEquals(departureAirport, flightGraph.getFlights().get(0).getDeparture().getAirportCode());
        assertEquals(destinationAirport, flightGraph.getFlights().get(0).getDestination().getAirportCode());
    }

    @Test
    void testSearchForFlights() {
        flightGraph.addAirport("JFK", "John F. Kennedy International Airport");
        flightGraph.addAirport("LAX", "Los Angeles International Airport");
        flightGraph.addAirport("SFO", "San Francisco International Airport");

        flightGraph.addFlight("JFK", "LAX", "AA123", "10:00 AM", "1:00 PM", 10, 220.0);
        flightGraph.addFlight("JFK", "LAX", "DL456", "2:00 PM", "5:00 PM", 10, 210.0);
        flightGraph.addFlight("LAX", "SFO", "FJ932", "3:00 PM", "6:00 PM", 10, 180.0);
        flightGraph.addFlight("LAX", "SFO", "UA789", "3:00 PM", "6:00 PM", 10, 160.0);

        List<AvailableFlight> availableFlights = flightGraph.searchForFlights("JFK", "SFO", "2023-12-07");

        assertNotNull(availableFlights);
        assertEquals(2, availableFlights.size());

        AvailableFlight flight1 = availableFlights.get(0);
        assertEquals("AA123", flight1.getFlightNumber());
        assertEquals("10:00 AM", flight1.getDepartureTime());
        assertEquals("1:00 PM", flight1.getArrivalTime());
        assertEquals(220.0, flight1.getPrice());

        AvailableFlight flight2 = availableFlights.get(1);
        assertEquals("DL456", flight2.getFlightNumber());
        assertEquals("2:00 PM", flight2.getDepartureTime());
        assertEquals("5:00 PM", flight2.getArrivalTime());
        assertEquals(210.0, flight2.getPrice());
    }

    @Test
    void testBookFlight() {
        flightGraph.addAirport("JFK", "John F. Kennedy International Airport");
        flightGraph.addAirport("LAX", "Los Angeles International Airport");

        flightGraph.addFlight("JFK", "LAX", "AA123", "10:00 AM", "1:00 PM", 10, 220.0);

        boolean bookingResult = flightGraph.bookFlight("AA123", "John Doe", 1);

        assertTrue(bookingResult);

        Flight bookedFlight = flightGraph.getFlightByNumber("AA123");
        assertNotNull(bookedFlight);
        assertEquals(9, bookedFlight.getAvailableSeats());

        List<Passenger> passengerList = bookedFlight.getPassengerList();
        assertNotNull(passengerList);
        assertEquals(1, passengerList.size());

        Passenger bookedPassenger = passengerList.get(0);
        assertEquals("John Doe", bookedPassenger.getName());
        assertEquals(1, bookedPassenger.getSeatNumber());
    }

    @Test
    void testDeleteFlight() {
        flightGraph.addAirport("JFK", "John F. Kennedy International Airport");
        flightGraph.addAirport("LAX", "Los Angeles International Airport");

        flightGraph.addFlight("JFK", "LAX", "AA123", "10:00 AM", "1:00 PM", 10, 220.0);
        flightGraph.addFlight("JFK", "LAX", "DL456", "2:00 PM", "5:00 PM", 10, 210.0);

        boolean deletionResult = flightGraph.deleteFlight("AA123");

        assertTrue(deletionResult);

        Flight deletedFlight = flightGraph.getFlightByNumber("AA123");
        assertNull(deletedFlight);

        List<Flight> remainingFlights = flightGraph.getFlights();
        assertEquals(1, remainingFlights.size());
    }
}
