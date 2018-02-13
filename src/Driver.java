import java.util.HashMap;


public class Driver {

	public static void main(String[] args)
	{
		HashMap<Integer, Account> accountList = new HashMap<Integer, Account>();
		accountList.put(1234, new Account(1234, 6789, 80));
		accountList.put(6789, new Account(6789, 4321, 60));
		Bank bank = new Bank(accountList);
	}
	
}
