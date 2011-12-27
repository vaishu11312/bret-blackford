package cscie160.hw5;

import java.rmi.RemoteException;


/**
 * Generic exception used in an ATM applications
 * @author M. Bret Blackford
 *
 */
public class ATMException extends RemoteException {
	public ATMException(String msg) {
		super(msg);
	}
}
