import java.util.HashMap;


public class Driver {

	static Bank _bank;
	public static void main(String[] args)
	{
		HashMap<Integer, Account> accountList = new HashMap<Integer, Account>();
		accountList.put(1234, new Account(1234, 6789, 80));
		accountList.put(6789, new Account(6789, 4321, 60));
		_bank = new Bank(accountList);
	}
	
	
	private boolean sendForValidation(Card number)
	{
		return _bank.validate(number);
	}
	
	private boolean isValidPin(Card in, int input)
	{
		return _bank.validate(in, input);
	}
	
	private Account getAccount(Card in)
	{
		return _bank.getAccount(in);
	}
}
