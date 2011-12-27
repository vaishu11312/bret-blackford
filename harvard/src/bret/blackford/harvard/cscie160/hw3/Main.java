package bret.blackford.harvard.cscie160.hw3;

public class Main {
    public Main() {
        super();
    }

    public static void main(String[] args) throws ElevatorFullException {
        Main main = new Main();
        Elevator lift = new Elevator();

        try {
            Passenger mike = new Passenger(1, 2, "Michael_", 206188);
            Passenger booi = new Passenger(1, 4, "Booi", 206189);

            lift.printPassengerManifest();
            System.out.println(lift);
            lift.boardPassengers(mike);
            lift.boardPassengers(booi);
            
            System.out.println(lift);
            lift.printFloorDetail();
            lift.sweep();
        } catch (ElevatorFullException e) {
            System.out.println(" *** EXCEPTION in Main() ");
            e.printStackTrace();
            throw e;
        }
        System.out.println(lift);
    }
}
