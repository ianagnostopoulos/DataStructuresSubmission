public class AvailableFlightTest {

    public static void main(String[] args) {
        testAvailableFlightOperations();
    }

    public static void testAvailableFlightOperations() {
        AvailableFlight availableFlight = new AvailableFlight("AA123", "10:00 AM", "1:00 PM", 220.0);

        assert availableFlight.getFlightNumber().equals("AA123");
        assert availableFlight.getDepartureTime().equals("10:00 AM");
        assert availableFlight.getArrivalTime().equals("1:00 PM");
        assert availableFlight.getPrice() == 220.0;

        availableFlight.setFlightNumber("DL456");
        availableFlight.setDepartureTime("2:00 PM");
        availableFlight.setArrivalTime("5:00 PM");
        availableFlight.setPrice(200.0);

        assert availableFlight.getFlightNumber().equals("DL456");
        assert availableFlight.getDepartureTime().equals("2:00 PM");
        assert availableFlight.getArrivalTime().equals("5:00 PM");
        assert availableFlight.getPrice() == 200.0;

        System.out.println("AvailableFlight operations tests passed!");
    }
}
