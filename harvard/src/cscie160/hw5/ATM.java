package cscie160.hw5;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Interface provides the basic API for an ATM machine, for use in RMI applications
 * @author M. Bret Blackford
 *
 */
public interface ATM extends Remote {
	public String deposit(int id, Float amt) throws RemoteException;
	public String witdraw(int id, Float amt) throws RemoteException;
	public Float getBalance(int id) throws RemoteException;
	public String deposit(int id, int i)throws RemoteException;;
	public String withdraw(int i, int j)throws RemoteException;;
}
