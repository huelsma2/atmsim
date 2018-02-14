 
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


/** BankTest Test Cases 
 * 
 * @author Michael D
 *
 */


public class BankTest{
	
	HashMap<Integer, Account> accountList = new HashMap<Integer, Account>();
	static Bank _bank;
	static Account _account;
	
	Card card1 = new Card(1234);
	Card card2 = new Card(6789);
	Card card3 = new Card(1111); //Bad account Value 
	
	
	@Test
	public void setup() {
	accountList.put(1234, new Account(1234, 6789, 80));
	accountList.put(6789, new Account(6789, 4321, 60));
	_bank = new Bank(accountList);
	
	
	}
	
	@Test
	public void TestValidAccountNumber() {
		assertTrue(_bank.validate(card1));
		assertTrue(_bank.validate(card2));
		System.out.println(accountList.containsKey(card1.accountNumber()));
		System.out.println(accountList.get(card2.accountNumber()));

		
	}
	
	@Test
	public void TestInvalidAccountNumber() {
	
		//System.out.println(card3.accountNumber());
		assertFalse(_bank.validate(card3));
		
	
	}
}


