import static org.junit.Assert.*;
/** BankTest Test Cases 
 * 
 * @author Michael D
 *
 */

import java.util.HashMap;

import org.junit.Test;


public class ATMTest{
	
	static HashMap<Integer, Account> accountList = new HashMap<Integer, Account>();
	static Bank _bank;
	static Account _account;
	static ATM _atm;
	
	
	/**
	 * Adds accounts in the following HashMap
	 * ( @param1, new Account( @param2, @param3, @param4 )
	 * @param1, @param2 accountNumber The account number for the account, positive integer
	 * @param3 pinCode The pin code for the account, positive integer
	 * @param4 balance The starting balance of the account as a double
	 * 
	 */
	
	static
	{
		accountList.put(1234, new Account(1234, 6789, 80.00));
		accountList.put(6789, new Account(6789, 4321, 60.00));
		_bank = new Bank(accountList);
		_atm = new ATM(_bank);

	};
	
	

	/** 
	 * Set initial balance for each of the desired accounts to default values.
	 */
	public void setup() {
	accountList.put(1234, new Account(1234, 6789, 80.00));
	accountList.put(6789, new Account(6789, 4321, 60.00));
	_bank = new Bank(accountList);
	_atm = new ATM(_bank);
	//card read commands
	String NUM;
 	String DIS;
 	String PRINT;
 	
 	//Button Commands
 	String W;
 	String CB;
 	String CANCEL;
 	String D;
 }

	
	/** 
	 * Tests if card1's account # is valid to an account
	 * Tests if card2's account # is valid to an account
	 */
	@Test
	public void TestValidAccountNumber() {
		setup();
		 _atm.runCommand("CARDREAD");
	
	}
	
	/** 
	 * Tests if card3's account # is invalid to an account
	 * Tests if card4's account # is invalid to an account
	 */
	@Test
	public void TestInvalidAccountNumber() {
		setup();
		
	
	}
	
	/** 
	 * Tests to insure incorrect Pin with Card1 fails
	 * Tests to insure incorrect Pin with Card2 fails
	 */
	@Test
	public void CheckValidAccountIncorrectPin() {
		setup();
 
	
	}
	
	
	/** 
	 * Tests if card1's Balance is correct to corresponding beginning balance
	 * Tests if card2's Balance is correct to corresponding beginning balance
	 */
	@Test
	public void TestBalanceOfEachAccount() {
		setup();

	}
	
	/** 
	 * Tests depositing from card1 of 90.00
	 * Tests deposit from card 2 of 10.00
	 */
	@Test
	public void TestDeposit() {
		setup();
	
	}
	
	
	/** 
	 * Tests if card1's Balance is correct to corresponding beginning balance
	 * Tests if card2's Balance is correct to corresponding beginning balance
	 */
	@Test
	public void TestWithdraw() {
		setup();

	
	}
	/** 
	 * Tests Over withdrawing from card1 of 120.00 when balance is 60.00
	 * Tests Over withdrawing from card2 of 60.00 when balance is 20.00
	 */
	@Test
	public void OverWithDraw() {
		setup();

	}
	
	/** 
	 * Tests withdrawing a negative number from card1
	 * Tests withdrawing a negative number from card2
	 *
	 */
	
	@Test
	public void WithDrawNegative() {
		setup();

	}
	

	
	
}