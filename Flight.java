import java.util.ArrayList;
import java.util.List;

public class Flight {
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;
    private int remainingSeats;
    private double price;
    private List<Passenger> bookedPassengers;
    private Airport destinationAirport;
    private Airport departureAirport;

    public Flight(String flightNumber, String departureTime, String arrivalTime, int totalSeats, double price,
            Airport destinationAirport, Airport departureAirport) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.remainingSeats = totalSeats;
        this.price = price;
        this.bookedPassengers = new ArrayList<>();
        this.destinationAirport = destinationAirport;
        this.departureAirport = departureAirport;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public double getPrice() {
        return price;
    }

    public List<Passenger> getBookedPassengers() {
        return bookedPassengers;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getDestination() {
        return destinationAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public boolean bookSeat(Passenger passenger) {
        if (remainingSeats > 0) {
            bookedPassengers.add(passenger);
            remainingSeats--;
            System.out.println("Booking successful. Remaining seats: " + remainingSeats);
            return true;
        } else {
            System.out.println("Booking unsuccessful. No available seats.");
            return false;
        }
    }

}