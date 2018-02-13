 
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
	
	Card card1 = new Card(1234);
	Card card2 = new Card(6789);
	
	
	@Test
	public void setup() {
	accountList.put(1234, new Account(1234, 6789, 80));
	accountList.put(6789, new Account(6789, 4321, 60));
	_bank = new Bank(accountList);
	
	
	}
	
	public void TestValidAccountNumber() {
		assertEquals(_bank.getAccount(card1), 1234);
		assertEquals(_bank.getAccount(card2), 6789);
		
	}
	
	@Test
	public void TestInvalidAccountNumber() {
		assertThat(_bank.getAccount(card1), not(6789));
	
	}
}


