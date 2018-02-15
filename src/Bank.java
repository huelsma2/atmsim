import java.util.HashMap;

/**
 * An object which holds a map of valid account numbers linked to accounts
 * @author Andrew Huelsman
 *
 *
 */

public class Bank {

	private HashMap<Integer, Account> _accountList = new HashMap<Integer, Account>();
	
	/**
	 * Creates a new bank, with a map of account numbers linked to accounts
	 * @param list the list of account numbers (key) associated with an account
	 */
	public Bank(HashMap<Integer, Account> list)
	{
		_accountList=list;
	}
	
	/**
	 * @return The balance of the account as a double
	 */
	public double getBalance(Card in) {
		return _accountList.get(in.accountNumber()).getBalance();
	}
	
	/**
	 * Validates whether or not the card sent in belongs to a valid Account in this bank
	 * @param in the card being read by the ATM, whose information is sent to this bank
	 * @return whether or not the card's account number is valid
	 */
	public boolean validate(Card in)
	{
		try{
			if(!_accountList.containsKey(in.accountNumber()))
	
				return false;
		}catch (NullPointerException e) { System.out.println("Fuck"); return false; }
		return true;
	}
	
	/**
	 * Validates whether or not the pin sent to this bank matches the card sent to this bank
	 * @param in the card being read by the ATM, whose information is sent to this bank
	 * @param pin the inputed string to be validated against the card's account
	 * @return whether or not the pin sent in matches the account's actual pin
	 */
	public boolean validate(Card in, int pin)
	{
		if(!validate(in))
			return false;
		if(_accountList.get(in.accountNumber()).getPinCode()==pin)
			return true;
		return false;
	}
	
	/**
	 * Allows an ATM contacting this bank to make a withdrawal request, for an amount of money.
	 * @param in The card being read by the ATM whose information is sent to this bank
	 * @param amt The amount of money that will be withdrawn from the ATM
	 * @return
	 */
	public boolean withdraw(Card in, double amt)
	{
		if(!validate(in))
			return false;
		return _accountList.get(in.accountNumber()).withdrawal(amt);
		
	}
	
	/**
	 * Deposits an amount of money that a user inserts into an ATM into an account in this bank
	 * @param in The card being read by the ATM whose information is being sent to this bank
	 * @param amt The amount of money that will be deposited into the account defined by in
	 * @return
	 */
	public boolean deposit(Card in, double amt)
	{
		if(!validate(in))
			return false;
		_accountList.get(in.accountNumber()).deposit(amt);
		return true;
	}
	
	//test
	public boolean execute(Card in, int pin)
	{
		return validate(in,pin);
	}
}
