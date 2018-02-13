 
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;


/** BankTest Test Cases 
 * 
 * @author Michael D
 *
 */


public class BankTest{
	Account Account1 = new Account(1234, 6789, 80);
	Account Account2 = new Account(6789, 4321, 60);
	
	
	@Test
	public void TestWithdraw() {
		assertEquals(1234, Account1.getAccountNumber());
	
	}
}


