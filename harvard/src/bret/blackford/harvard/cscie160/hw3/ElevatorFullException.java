package bret.blackford.harvard.cscie160.hw3;

 /** {@code ElevatorFullException} creates a new, customized error message.
  *   @author M. Bret Blackford (20849347) 
  *   @version 1.0
  *   @since October 11, 2011
 */
public class ElevatorFullException extends Exception {
     
    public ElevatorFullException(Throwable throwable) {
        super(throwable);
    }

    public ElevatorFullException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public ElevatorFullException(String string) {
        super(string);
    }

    public ElevatorFullException() {
        super();
    }

}