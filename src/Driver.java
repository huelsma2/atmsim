import java.util.HashMap;
import java.util.Scanner;

/**
 * Test driver which creates a bank with a map of accounts, and simulates a Customer putting their card
 * in a machine.
 * @author Andrew Huelsman
 *
 */
public class Driver {

	public static void main(String[] args)
	{
		/// Initialize testing values
		HashMap<Integer, Account> accountList = new HashMap<Integer, Account>();
		accountList.put(1234, new Account(1234, 6789, 80));
		accountList.put(6789, new Account(6789, 4321, 60));
		Bank _bank = new Bank(accountList);
		///
		Scanner stdin = new Scanner(System.in);
		/// Loop simulation 
		while(true)
		{
			System.out.println("SIMULATION: USER ENTERS CARD (ENTER ACCOUNT NUMBER) Enter 'quit' to quit:");
			String input = stdin.next();
			int accnum = -1;
			try{accnum= Integer.parseInt(input);} 
			catch (NumberFormatException e) { if(input.toLowerCase().equals("quit")) break;}
			new ATM(_bank).start(accnum,stdin);
			System.out.println("Transaction finished.");
		}
		stdin.close();
	}
}
