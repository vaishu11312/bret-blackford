package cscie160.hw5;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This 'Factory' class returns an ATM for use by clients
 * 
 * @author M. Bret Blackford
 * 
 */
public interface ATMFactory extends Remote {
	public ATM getATM() throws RemoteException;
}
