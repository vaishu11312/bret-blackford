package cscie160.hw5;

import java.rmi.Naming;


/**
 * Basic server to start an ATM for a client to use.
 * @author M. Bret Blackford
 * CSCIE160
 */
public class Server {

	public static void main(String[] args) {

		try {
			System.out
					.println("in ATMImpl.ATMImpl() and getting ready to rebind a factory");
			ATMFactoryImpl obj = new ATMFactoryImpl();
			Naming.rebind("rmi://localhost/atmfactory", obj);
			System.out.println(" SERVER READY !");

		} catch (Exception e) {
			System.out.println("ATMImpl.ATMImpl() found an ERROR ***");
			System.out.println(e.getMessage());
		}
	}
}

