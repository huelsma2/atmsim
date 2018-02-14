 
import static org.junit.Assert.*;
/** BankTest Test Cases 
 * 
 * @author Michael D
 *
 */

import java.util.HashMap;

import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;



public class BankTest{
	
	HashMap<Integer, Account> accountList = new HashMap<Integer, Account>();
	static Bank _bank;
	static Account _account;
	
	Card card1 = new Card(1234);
	Card card2 = new Card(6789);
	Card card3 = new Card(1111); //Bad account Value 
	Card card4 = new Card(12345); //Bad account Value
	
	
	/**
	 * Adds accounts in the following HashMap
	 * ( @param1, new Account( @param2, @param3, @param4 )
	 * @param1, @param2 accountNumber The account number for the account, positive integer
	 * @param3 pinCode The pin code for the account, positive integer
	 * @param4 balance The starting balance of the account as a double
	 * 
	 */
	
	@Test
	public void setup() {
	accountList.put(1234, new Account(1234, 6789, 80.00));
	accountList.put(6789, new Account(6789, 4321, 60.00));
	_bank = new Bank(accountList);

	
	}
	
	/** 
	 * Tests if card1's account # is valid to an account
	 * Tests if card2's account # is valid to an account
	 */
	@Test
	public void TestValidAccountNumber() {
		assertTrue(_bank.validate(card1));
		assertTrue(_bank.validate(card2));
	
	}
	
	/** 
	 * Tests if card3's account # is invalid to an account
	 * Tests if card4's account # is invalid to an account
	 */
	@Test
	public void TestInvalidAccountNumber() {
		assertFalse(_bank.validate(card3));
		assertFalse(_bank.validate(card4));
	
		
	
	}
	
	/** 
	 * Tests if card1's Balance is correct to corresponding beginning balance
	 * Tests if card2's Balance is correct to corresponding beginning balance
	 */
	@Test
	public void TestBalanceOfEachAccount() {
		assertEquals(80.00, _bank.getBalance(card1), 0); //something up. this should work correctly..
		assertEquals(60.00, _bank.getBalance(card2), 0); //something up. this should work correctly..
	
	
	}
	
	
	
}


