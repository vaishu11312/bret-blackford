package bret.blackford.harvard.cscie160.hw3;

import java.util.Vector;

/** {@code Floor} is used to represent a single floor in a building.
 *   @author M. Bret Blackford (20849347)
 *   @version 1.1
 *   @since October 11, 2011
 */
public class Floor {

    private Vector residentPassengers;
    private Vector passengersGoingUp;
    private Vector passengersGoingDown;
    private final int floorNumber;
    private String className = "Floor.";
    private Vector passengersLeaving = new Vector();


    /** constructore creates a basic instance of the florr number pased in
     * @param _floorNumber
     */
    public Floor(int _floorNumber) {
        super();
       String classMethodName = className + "Floor()";
        residentPassengers = new Vector();
        passengersGoingUp = new Vector();
        passengersGoingDown = new Vector();
        floorNumber = _floorNumber;
    }

    /** prefered constructor method ... will create a floor instance and populate with a number of passengers (randomly named with random goals)
     * @param _floorNumber
     * @param noOfPassengersOnFloor
     */
    public Floor(int _floorNumber, int noOfPassengersOnFloor) {
        String classMethodName = className + "Floor(int floorNum, int noOfPassengersOnFloor)";
        residentPassengers = new Vector();
        passengersGoingUp = new Vector();
        passengersGoingDown = new Vector();
        floorNumber = _floorNumber;

        for (int i = 0; i < noOfPassengersOnFloor; i++) {

            Passenger newPassenger = new Passenger(floorNumber);

            if (Coin.toss() == "HEADS") {
                if (Coin.toss() == "HEADS") {

                    if (newPassenger.getDestinationFloor() > floorNumber) {
                        passengersGoingUp.add(newPassenger);
                    } else {
                        passengersGoingDown.add(newPassenger);
                    }

                }
            } else {
                residentPassengers.add(newPassenger);
            }
        } //end of FOR loop
    }


    /** the Floor instance will take a Passenger from an Elevator and add to appropraite queue and set Passenger state
     * @param _passenger
     */
    public void unloadPassengers(Passenger _passenger) {
        String classMethodName = className + "unloadPassengers(Passenger)";
        _passenger.setCurrentFloor(floorNumber);
        
        if (floorNumber == 1) {
            passengersLeaving.add(_passenger);
            System.out.println(classMethodName + " and " + _passenger.getName() + " has left the building.   >>>>");
        } else {
            residentPassengers.add(_passenger);
        }
        System.out.println(classMethodName + " and adding " + _passenger.getName() + " to floor " + floorNumber);
    }

    /** logical TRUE/FALSE information about individuals waiting
     * @return
     */
    public boolean isAnyoneWaiting() {
        String classMethodName = className + "isAnyoneWaiting()";
        boolean waiting = false;

        if (isAnyoneWaitingToGoUp() || isAnyoneWaitingToGoDown()) {
            waiting = true;
        }
        return waiting;
    }

    /** logical TRUE/FALSE information about individuals waiting to go up
     * @return
     */
    public boolean isAnyoneWaitingToGoUp() {
        String classMethodName = className + "isAnyoneWaitingToGoUp()";
        boolean waiting = true;
        int numberWaiting = passengersGoingUp.size();

        if (numberWaiting < 1) {
            waiting = false;
        }
        return waiting;
    }

    /** logical TRUE/FALSE information about individuals waiting to go down
     * @return
     */
    public boolean isAnyoneWaitingToGoDown() {
        String classMethodName = className + "isAnyoneWaitingToGoDown()";
        boolean waiting = true;
        int numberWaiting = passengersGoingDown.size();

        if (numberWaiting < 1) {
            waiting = false;
        }
        return waiting;
    }


    private void addPassenger() {
        String classMethodName = className + "addPassenger()";
        Passenger newPassenger = new Passenger(floorNumber);

        if (newPassenger.isReadyForNewFloor1() == false) {
            setResidentPassengers(newPassenger);
        }
        if (newPassenger.getDestinationFloor() > floorNumber || newPassenger.isReadyForNewFloor1() == true) {
            setPassengersGoingUp(newPassenger);
        } else {
            setPassengersGoingDown(newPassenger);
        }
    }

    /** Method will 'touch' each individual Passenger on this floor and randomly set state (will individual now want to leave the floor?)
     */
    public void checkOccupantsState() {

        Passenger tempPerson = new Passenger();

        for (int i = 0; i < residentPassengers.size(); i++) {

            tempPerson = (Passenger)residentPassengers.elementAt(i);
            tempPerson.checkState();

            if (tempPerson.isReadyForNewFloor1()) {
                residentPassengers.remove(i);
                tempPerson.setDestinationFloor(1);
                passengersGoingDown.add(tempPerson);
            }
        }
    }

    public void setPassengersGoingUp(Passenger _passengersGoingUp) {
        this.passengersGoingUp.add(_passengersGoingUp);
    }

    public void setPassengersGoingDown(Passenger _passengersGoingDown) {
        this.passengersGoingDown.add(_passengersGoingDown);
    }

    public void setResidentPassengers(Passenger _residentPassengers) {
        this.residentPassengers.add(_residentPassengers);
    }

    /** Provides state information for the specific floor instance
     * @return
     */
    public String toString() {
        String outString = new String();
        outString =
                "  Floor [" + floorNumber + "], passengers going up [" + passengersGoingUp.size() + "] going down [" +
                passengersGoingDown.size() + "], and resident [" + residentPassengers.size() + "] total-[" + totalPeopleOnFloor() + "]";
        
        return outString;
    }

    /** returns an integer of individuals on the floor
     * @return
     */
    public int totalPeopleOnFloor() {
        int count = 0;
        count +=  passengersGoingUp.size();
        count += passengersGoingDown.size();
        count += residentPassengers.size();
        
        return count;
    }

    /** Informs elevator of those waiting to go down
     * @return
     */
    public Vector getPassengersGoingDown() {
        return passengersGoingDown;
    }

    /** Informs elevator of those waiting to go up
     * @return
     */
    public Vector getPassengersGoingUp() {
        return passengersGoingUp;
    }
}

