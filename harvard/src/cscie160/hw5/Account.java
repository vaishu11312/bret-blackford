package cscie160.hw5;

/**
 * A basic ACCOUNT for use in a bank application
 * @author M. Bret Blackford
 *
 */
public class Account {

	private final int id;
	private Float balance;
	private String info = "Account.";
	
	/**
	 * Constructor requires an initial Account ID and Balance
	 * @param id
	 * @param bal
	 */
	public Account(int id, Float bal) {
		System.out.println(info + "Account(" + id + ", " + bal + ")");
		
		this.id = id;
		balance = bal;
	}
	
	/**
	 * Constructor will generate an Account ID and give a zero Balance
	 */
	public Account() {
		System.out.println(info + "Account()");
		this.id = AccountID.accountId++;
		this.balance = (float) 0;
	}

	/**
	 * Gives the Account ID 
	 * @return
	 */
	public int getId() {
		System.out.println(info + "getId()");
		return id;
	}

	/**
	 * Returns the Account's Balance
	 * @return
	 */
	public double getBalance() {
		System.out.println(info + "getBalance()");
		return balance;
	}

	/**
	 * Returns the Account's Balance (in cents -- or in an Integer format)
	 * @param cents
	 * @return
	 */
	public String deposit(int cents) {
		System.out.println(info + "deposit(int)");
		String ret = "GOOD";
		if (cents < 0) {
			ret = " deposit must be a positive number";
			System.out.println("ERROR trying to depoist");
		}
		this.balance = (float) cents;
		
		return ret;
	}
	
	/**
	 * Will attempt to deposit funds into the account.  Edit checks require a positive deposit amount
	 * @param amt
	 * @return
	 */
	public String deposit(Float amt) {
		System.out.println(info + "deposit(" + amt + ")");
		String ret = "GOOD";
		if (amt < 0) {
			ret = " deposit must be a positive number";
			System.out.println("ERROR trying to depoist");
		}
		this.balance = (float) amt;
		
		return ret;
	}
	
	/**
	 * Will attempt to withdraw funds. Edit checks make sure amount requested does not exceed balance and is positive.
	 * @param amt
	 * @return
	 */
	public String withdraw(Float amt) {
		System.out.println(info + "witdraw(" + amt + ")");
		String ret = "GOOD";
		if(amt > balance) {
			ret = "ERROR: insufficient funds for withdrwal";
		} else {
			balance =  (balance - amt);
			ret = " witdrawl successful";
		}
		return ret;
	}
}

