package cscie160.hw5;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 * Implementation of the ATMFactory used to provide an ATM object for clients
 * @author M. Bret Blackford
 *
 */
public class ATMFactoryImpl extends UnicastRemoteObject implements ATMFactory {
	
	public ATMFactoryImpl() throws RemoteException {
		super();
	}

	public ATM getATM() throws RemoteException {
		
		//ATMImpl atm = new ATMImpl();
		return new ATMImpl();
	}

}
