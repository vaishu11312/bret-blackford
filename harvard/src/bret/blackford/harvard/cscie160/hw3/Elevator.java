package bret.blackford.harvard.cscie160.hw3;


import java.util.Vector;


/** {@code Elevator} is a class to model an elevator's behavior.
 *
 * @author M. Bret Blackford (20849347)
 * @version 1.1
 * @since October 1, 2011
 */
public class Elevator {
    
    /** This is the theoretical maximum number of passengers who can ride the elevator.  Current code is not constrained by this limit.  (is in person count, could also be in weight)
     */
    public static final int CAPACITY = 10;

    /** The fixed number of floor serviced by the elevator
     */
    public static final int NO_OF_FLOORS = 7;
    
    private Vector passengerManifest;
    private int currentFloor;
    private Direction direction; 
    private boolean doorOpen = true;
    private boolean inService = true;
    private String className = "Elevator.";
    private Floor [] floorArray;

    /** constructor will create an elevator object along with a number of floor for a building (based on NO_OF_FLOORS)
     */
    public Elevator() {
        super();
        String classMethodName = className + "Elevator()";
        passengerManifest = new Vector();
        currentFloor = 1;
        direction = Direction.UP;
        createFloors();
    }

    /** Method kicks-off the elevator's movements up and down. Contains logic to handle stoping, loading, and unloading (with Floor assistance)
     */
    public void sweep() {
        while(isElevatorInService()) {
            move();
            //System.out.println( toString() );
        }
    }


    private void move() {

        String classMethodName = className +  "move()";
        closeDoor();

        if (currentFloor == NO_OF_FLOORS) {
            direction = Direction.DOWN;
        }
        if (currentFloor < 2) {
            direction = Direction.UP;
        }

        if (direction == Direction.UP) {
            currentFloor += 1;
        } else {
            currentFloor = currentFloor - 1;
        }

        if (currentFloor == 1) {
            System.out.println(" <<  ON GROUND FLOOR ---" + currentFloor);
            System.out.println(printPassengerManifest());
            printFloorDetail();
        }
        if (currentFloor == 7 ) {
            System.out.println(" <<<<<< I'M ON TOP OF THE WORLD >>>>>");
        }
        
        if ( currentPassengerWantsOff(currentFloor)) {
            stop();
        }

        try {
            if ( registerRequest() ) {

        }
        } catch (ElevatorFullException e) {
        }
        System.out.println(classMethodName + "  on floor " + currentFloor);

    }
    
    private void unloadPassengersOnFloor() {
        
        String classMethodName = className + "unloadPassengersOnFloor()";
        Passenger tempPerson = new Passenger();
        
        for (int i = 0; i < passengerManifest.size(); i++) {
            tempPerson = (Passenger)passengerManifest.elementAt(i);
            
            if (tempPerson.getDestinationFloor() == currentFloor) {
                floorArray[currentFloor - 1].unloadPassengers(tempPerson);
                passengerManifest.remove(i);
            }
        }
    }


    private void stop() {
        
        String classMethodName = className + "stop()";
        unloadPassengersOnFloor();
    }


    /** Provides the number of individuals currently riding an elevator
     * @return
     */
    public int noOfPassengers() {
        String classMethodName = className +  "noOfPassengers()";
        int count = 0;
        if (passengerManifest == null || passengerManifest.size() < 1) {
            count = 0;
        } else {
            count = passengerManifest.size();
        }
        return count;
    }


    private boolean registerRequest() throws ElevatorFullException {
        boolean request = false;
        boolean waitingUp = floorArray[currentFloor-1].isAnyoneWaitingToGoUp();
        boolean waitingDown = floorArray[currentFloor-1].isAnyoneWaitingToGoDown();

        if (direction == Direction.UP) {
            if (floorArray[currentFloor-1].isAnyoneWaitingToGoUp()) {
                loadElevatorFromFloor(floorArray[currentFloor-1].getPassengersGoingUp());
                request = true;
            }
        }
        if (direction == Direction.DOWN && floorArray[currentFloor-1].isAnyoneWaitingToGoDown() || currentFloor == 7) {
            loadElevatorFromFloor(floorArray[currentFloor-1].getPassengersGoingDown());
            request = false;
        }
        floorArray[currentFloor-1].checkOccupantsState();
        return request;
    }


    private void loadElevatorFromFloor(Vector queue) {
        String classMethodName = className + "loadElevatorFromFloor()";

        Passenger temp = new Passenger();
        try {

            for (int i = 0; i < queue.size(); i++) {
                temp = (Passenger)queue.remove(i);
                passengerManifest.add(temp);
                System.out.println(classMethodName + " adding " + temp.getName() + " to elevator on floor[" + currentFloor + "]");

                if (passengerManifest.size() > CAPACITY) {
                    System.out.println("\n        ************************************************************************");
                    System.out.println("        ************************************************************************");
                    System.out.println("        in " + classMethodName + ": Houston, we have a problem!      ");
                    throw new ElevatorFullException(" TO MANY PEOPLE TRYING TO BOARD ELEVATOR !!!");
                }
            }
        } catch (ElevatorFullException efe) {
            System.out.println("        **** ERROR found in " + classMethodName + " ******");
            System.out.println(efe);
            System.out.println("        ************************************************************************");
            System.out.println("        ************************************************************************ \n");
            queue.add(temp); //add passenger back on floor to wait
        }
    }


    /** Takes individuals from a Floor and adds them to the elevator.  Contains logic to inform elevator what floors are desired by each passenger.
     * @param _passenger
     * @throws ElevatorFullException
     */
    public void boardPassengers(Passenger _passenger) throws ElevatorFullException {
        String classMethodName = className +  "boardPassengers()";
        if( CAPACITY - noOfPassengers()  < 1 ) {
            System.out.println("ERROR in " + classMethodName);                                      
            throw new ElevatorFullException();
        } else {
            passengerManifest.add(_passenger);
        }
    }


    private boolean currentPassengerWantsOff(int _currentFloor) {

        for (int i = 0; i < passengerManifest.size(); i++) {
            Passenger tempPassenger = (Passenger)passengerManifest.elementAt(i);
            int tempDestFloor = tempPassenger.getDestinationFloor();
        
            if (tempPassenger.getDestinationFloor() == _currentFloor) {
                return true;
            }
        }
        return false;
    }


    /** Method will display a list of the individuals currently on board along with their desired destinations.
     * @return
     */
    public String printPassengerManifest() {
        String manifestInfo = "\n";
        manifestInfo += "  Passenger Manifest: \n";
        manifestInfo += "  ------------------- \n";
            
        Passenger tempPassenger = new Passenger();
        for( int i=0; i < passengerManifest.size(); i++) {
            tempPassenger = (Passenger)passengerManifest.get(i);
            manifestInfo += "    ";
            manifestInfo += tempPassenger.toString();
            manifestInfo += "\n";
        }

        return manifestInfo;
    }
    
    
    private void createFloors() {
        
        String classMethodName = className +  "createFloors()";
        floorArray = new Floor[NO_OF_FLOORS];

        floorArray[0] = new Floor(1,0); //floor 1 has 0 in queue
        floorArray[1] = new Floor(2,3); //floor 2 has 3 in queue
        floorArray[2] = new Floor(3,0); //floor 3 has 0 in queue
        floorArray[3] = new Floor(4,18); //floor 4 has 8 in queue
        floorArray[4] = new Floor(5,0); //floor 5 has 0 in queue
        floorArray[5] = new Floor(6,5); //floor 6 has 5 in queue
        floorArray[6] = new Floor(7,8); //floor 7 has 8 in queue
    }


    /** Prints the Elevator's state (floor, riders, direction)
     * @return
     */
    public String toString() {
        String returnString = DateUtils.now() + " Elevator is currently on the " + currentFloor + " floor and going " + direction + " with " + passengerManifest.size()  + " passengers on board.";
        returnString += printPassengerManifest();
        return returnString;
    }


    /** Displays the state detail for all the floors in the building.
     */
    public void printFloorDetail() {
        
        for ( int i=0; i < floorArray.length; i++ ) {
            System.out.println( floorArray[i] );
        }
        System.out.println();
    }
    
    
    private void closeDoor() {
        doorOpen = false;
    }
    
    
    private boolean isElevatorInService() {
        if (passengerManifest.size() > 0) {
            inService = true;
            return true;
        }
        if ( isAnyoneWaitingOnFloors() ) {
            inService = true;
            return true;
        } 
        inService = false;
        return false;
    }
    
    
    private boolean isAnyoneWaitingOnFloors() {
        
        for (int i=0; i<floorArray.length; i++) {
            
            floorArray[i].checkOccupantsState();
            
            if ( floorArray[i].isAnyoneWaiting() ) {
                return true;
            }
        }
        return false;
    }
    
}
