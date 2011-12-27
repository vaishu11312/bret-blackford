package cscie160.hw5;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;



/**
 * Class implements the logic and methods required by the ATM interface.
 * @author M. Bret Blackford
 *
 */
public class ATMImpl extends UnicastRemoteObject implements ATM {

	private HashMap<Integer, Account> accounts = new HashMap<Integer, Account>();
	
	/**
	 * Constructor generates three (3) accounts with specific balances
	 * @throws RemoteException
	 */
	public ATMImpl() throws RemoteException {
		super();
		
		System.out.println("in ATMImpl.ATMImpl() ...");
		
		accounts.put(1, new Account(1,(float) 0.0));
		accounts.put(2, new Account(1,(float) 100.0));
		accounts.put(3, new Account(3,(float) 500.00));
		accounts.put(4, new Account(4,(float) 0.0));
		
	}
	

	/**
	 * Method will attempt to make a deposit to a specific customer accounts. Some basic edit checks  
	 */
	public String deposit(int id, Float amt) throws RemoteException {
		
		check(id,amt);

		String tmpString = accounts.get(id).deposit(amt);
		return tmpString;
	}

	/**
	 * Method attempts to withdraw from a specific customer account. Some basic edit checks
	 */
	public String witdraw(int id, Float amt) throws RemoteException {
		
		check(id, amt);
		String tmpString;
		if (amt>accounts.get(id).getBalance()) {
			tmpString = " insufficient funds";
			throw new ATMException("insufficient funds");
		}
		
		tmpString = accounts.get(id).withdraw(amt);
		return tmpString;
	}

	/**
	 * Method returns the balance for a specific customer account
	 */
	public Float getBalance(int id) throws RemoteException {
		
		check(id, 0);
		
		Float temp = (float) accounts.get(id).getBalance();
		return temp;
	}

	/**
	 * Method will attempt to make a deposit to a specific customer accounts. Some basic edit checks  
	 */
	public String deposit(int id, int i) throws ATMException{
		try {
			check(id, 0);
		} catch (ATMException e) {
			e.printStackTrace();
		}
		String tmpString = accounts.get(id).deposit(i);
		return tmpString;
	}


	/**
	 * Method attempts to withdraw from a specific customer account. Some basic edit checks
	 */
	public String withdraw(int id, int amt) throws ATMException{
		try {
			check(id, amt);
			if (amt>accounts.get(id).getBalance()) {
				throw new ATMException("insufficient funds");
			}
		} catch (ATMException e) {
			e.printStackTrace();
		}
		Float amount = new Float(amt);
		String tmpString = accounts.get(id).withdraw(amount);
		return tmpString;
	}
	
	
	private void check(int id, float amt) throws ATMException{
		if(amt < 0) {
			throw new ATMException("Amount must not be a negative number");
		}
		
		Account tmpAcct = accounts.get(id);
		if (tmpAcct == null){
			throw new ATMException(id + ": is an unknown account");
		}
	}

	
}

