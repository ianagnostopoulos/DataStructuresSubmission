public class PassengerTest {

    public static void main(String[] args) {
        testPassengerOperations();
    }

    public static void testPassengerOperations() {
        Passenger passenger = new Passenger("John Doe", 1);

        assert passenger.getName().equals("John Doe");
        assert passenger.getSeatNumber() == 1;

        passenger.setName("Jane Doe");
        passenger.setSeatNumber(2);

        assert passenger.getName().equals("Jane Doe");
        assert passenger.getSeatNumber() == 2;

        System.out.println("Passenger operations tests passed!");
    }
}
