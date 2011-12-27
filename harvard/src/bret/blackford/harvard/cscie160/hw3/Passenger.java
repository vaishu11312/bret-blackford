package bret.blackford.harvard.cscie160.hw3;

import java.util.Random;

/** {@code Passenger} is used to represent a single passenger/individual.
 *   @author M. Bret Blackford (20849347)
 *   @version 1.1
 *   @since October 11, 2011
 */
public class Passenger {
    
    private int currentFloor;
    private int destinationFloor;
    private final int passengerID;
    private final String name;
    private static int counter;
    private boolean readyForNewFloor = false;
    private String classMethodName = "Passenger.";

    /** Contsructor will create a passenger/individual.  Depricated. Used to create a generic individual.
     */
     public Passenger() {
        super();
        classMethodName += "Passenger()";
        currentFloor = 1;
        destinationFloor = 1;
        name = NamesEnum.getRandomName();
        passengerID = counter++;
    } 

    /** Contsructor will create a passenger/individual on floor requested, heading to floor specified, with name and ID given
     * @param _currentFloor
     * @param _destinationFloor
     * @param _name
     * @param _ID
     */
    public Passenger(int _currentFloor, int _destinationFloor, String _name, int _ID) {
        classMethodName += "Passenger(int,int,String,int)";
        name = _name;
        currentFloor = _currentFloor;
        destinationFloor = _destinationFloor;
        passengerID = _ID;
    }

    /** Constructor will create a passenger/individual on floor requested.  The name, ID, and destination floor will be randomly generated for each instance.
     * @param _currentFloor
     */
    public Passenger(int _currentFloor) {
        classMethodName += "Passenger(currentFloor)";
        currentFloor = _currentFloor;
        destinationFloor = getRandom0toX(Elevator.NO_OF_FLOORS);
        readyForNewFloor = isReadyForNewFloor();
        name = NamesEnum.getRandomName();
        passengerID = counter++;
        
        if( currentFloor == 1 && destinationFloor < 2 ){
            destinationFloor = 2;
        }
        if( currentFloor == 7 && destinationFloor > 6 ) {
            destinationFloor = 2;
        }
            
    }

    /** Determines if the floor is where the passenger/individual wants to be
     * @return
     */
    public boolean arrive() {
        classMethodName += "arrive()";

        boolean returnValue;
        if (currentFloor == destinationFloor) {
            returnValue = true;
        } else {
            returnValue = false;
        }
        return returnValue;
    }
    
    public void boardPassengerOnElevator() {
        classMethodName += "boardPassengerOnElevator()";
        //ToDo
    }


    private int getRandom0toX(int end) {
        classMethodName += "getRandom0toX(int)";
        Random randomNumberGenerator = new Random();
        int randomInt = 1;
        randomInt = randomNumberGenerator.nextInt(end);

        if (randomInt == 0) {
            randomInt = 1;
        }
        if (randomInt > 7) {
            randomInt = 7;
        }
        return randomInt;
    }

    private boolean isReadyForNewFloor(){
        classMethodName += "isReadyForNewFloor()";
        boolean ready = false;
        if( Coin.toss() == "HEADS") {
            ready = true;
        }
        return ready;
    }

    /** Prints state information for the Passenger instance (Name, ID, Destination floor, Current floor)
     * @return
     */
    public String toString() {
        String outString = "Name is " + name + ", ID = " + passengerID + ", Destination = floor " + destinationFloor + ", currently on floor " + currentFloor;
        return outString;
    }

    /** Returns the destination floor for the person/individual
     * @return
     */
    public int getDestinationFloor() {
        return destinationFloor;
    }

    /** Returns state information -- if the person/individual is ready to go
     * @return
     */
    public boolean isReadyForNewFloor1() {
        return readyForNewFloor;
    }

    /** Used to inform passonger/individual of current location/floor
     * @param currentFloor
     */
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /** Used to obtain the current location/floor of the passenger/individual 
     * @return
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /** Returns the name of the passenger/individual
     * @return
     */
    public String getName() {
        return name;
    }

    public void checkState() {
        if (readyForNewFloor == false) {
            if (Coin.toss().equalsIgnoreCase("HEADS")) {
                if (Coin.toss().equalsIgnoreCase("HEADS")) {
                    readyForNewFloor = true; //finished business and ready to leave
                    destinationFloor = 1;    //default is to go down and out
                }
            }
        }
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
}
