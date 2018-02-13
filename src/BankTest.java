 
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;


/** BankTest Test Cases 
 * 
 * @author Michael D
 *
 */


public class BankTest{
	
	HashMap<Integer, Account> accountList = new HashMap<Integer, Account>();
	static Bank _bank;
	
	
	
	@Test
	public void setup() {
	accountList.put(1234, new Account(1234, 6789, 80));
	accountList.put(6789, new Account(6789, 4321, 60));
	_bank = new Bank(accountList);
	
	
	}
	
	public void TestValidAccountNumber() {
		assertEquals(bank ;
		//assertEquals(6789, Account2.getAccountNumber());
	
	}
	
	@Test
	public void TestInvalidAccountNumber() {
		//assertEquals(1234, Account1.getAccountNumber());
		//assertEquals(6789, Account2.getAccountNumber());
	
	}
}


