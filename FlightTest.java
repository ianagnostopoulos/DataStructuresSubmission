public class FlightTest {

    public static void main(String[] args) {
        testFlightOperations();
    }

    public static void testFlightOperations() {
        Airport departureAirport = new Airport("JFK", "John F. Kennedy International Airport");
        Airport destinationAirport = new Airport("LAX", "Los Angeles International Airport");
        Flight flight = new Flight("AA123", "10:00 AM", "1:00 PM", 10, 220.0, destinationAirport, departureAirport);

        assert flight.getFlightNumber().equals("AA123");
        assert flight.getDepartureTime().equals("10:00 AM");
        assert flight.getArrivalTime().equals("1:00 PM");
        assert flight.getTotalSeats() == 10;
        assert flight.getRemainingSeats() == 10;
        assert flight.getPrice() == 220.0;
        assert flight.getBookedPassengers().isEmpty();
        assert flight.getDepartureAirport().equals(departureAirport);
        assert flight.getDestination().equals(destinationAirport);

        Passenger passenger = new Passenger("John Doe", 1);
        boolean bookingResult = flight.bookSeat(passenger);
        assert bookingResult;
        assert flight.getRemainingSeats() == 9;
        assert flight.getBookedPassengers().size() == 1;
        assert flight.getBookedPassengers().contains(passenger);

        System.out.println("Flight operations tests passed!");
    }
}
