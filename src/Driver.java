import java.util.HashMap;
import java.util.Scanner;

/**
 * Test driver which creates a bank with a map of accounts, and simulates a Customer putting their card
 * in a machine.
 * @author Andrew Huelsman
 *
 */
public class Driver {

	static Bank _bank;
	static char transaction;
	public static void main(String[] args)
	{
		HashMap<Integer, Account> accountList = new HashMap<Integer, Account>();
		accountList.put(1234, new Account(1234, 6789, 80));
		accountList.put(6789, new Account(6789, 4321, 60));
		_bank = new Bank(accountList);
		Scanner stdin = new Scanner(System.in);
		while(true)
		{
			System.out.println("SIMULATION: USER ENTERS CARD (ENTER ACCOUNT NUMBER):");
			int accnum = -1;
			try{accnum= stdin.nextInt();} catch (NumberFormatException e) { System.out.println("Invalid format"); }
			new ATM(_bank).start(accnum);
			System.out.println("Transaction finished. Continue? (y for yes, n no)");
			transaction = stdin.next().charAt(0);
			if(!(transaction == 'y') || !(transaction == 'Y'))
				break;
		}
		stdin.close();
	}
}
