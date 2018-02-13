/**
 * Object which holds the account number for a specific account
 * @author Andrew Huelsman
 *
 */
public class Card {

	private int _accountNumber;
	
	/**
	 * Creates a new card with an account number
	 * @param num
	 */
	public Card(int num)
	{
		_accountNumber=num;
	}
	
	/**
	 * Returns the account number associated with this card
	 * @return the account number associated with this card
	 */
	public int accountNumber()
	{
		return _accountNumber;
	}
}
