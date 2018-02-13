import java.util.HashMap;


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
	 * Validates whether or not the card sent in belongs to a valid Account in this bank
	 * @param in the card being read by the ATM, whose information is sent to this bank
	 * @return whether or not the card's account number is valid
	 */
	public boolean validate(Card in)
	{
		if(!_accountList.containsKey(in.accountNumber()))
			return false;
		return true;
	}
	
	/**
	 * Validates whether or not the pin sent to this bank matches the card sent to this bank
	 * @param in the card being read by the ATM, whose information is sent to this bank
	 * @param pin the inputted string to be validated against the card's account
	 * @return whether or not the pin sent in matches the account's actual pin
	 */
	public boolean validate(Card in, int pin)
	{
		if(!validate(in))
			return false;
		if(_accountList.get(in).getPinCode()==pin)
			return true;
		return false;
	}
}
