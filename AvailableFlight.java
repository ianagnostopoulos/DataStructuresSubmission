public class AvailableFlight {
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private double price;

    public AvailableFlight(String flightNumber, String departureTime, String arrivalTime, double price) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}