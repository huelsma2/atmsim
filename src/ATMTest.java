/**
 * This test class tests the functionality of the bank and making transactions.
 * It also tests the ATM class's ability to input commands and change states.
 * @author Michael Davis, Andrew Yehle
 */
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
	static CardReader _cardreader;
	static CashDispenser _cashdispenser;
	static Printer _printer;
	
	
	
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
	
	

	/** 
	 * Set initial balance for each of the desired accounts to default values.
	 */
	public void setup() {
	accountList.put(1234, new Account(1234, 6789, 80.00));
	accountList.put(6789, new Account(6789, 4321, 60.00));
	_bank = new Bank(accountList);
	_atm = new ATM(_bank);
	_printer = new Printer();
	_cashdispenser = new CashDispenser();
	_cardreader = new CardReader();
 }


	
	/** 
	 * Tests to see if current time is printed to console.
	 */
	@Test
	public void TestPrintTime() {
		setup();
		
		_atm.printTime();
	}
	
	@Test
	public void TestInvalidAccountNumber() {
		setup();
		assertFalse(_bank.validate(card3));
		assertFalse(_bank.validate(card4));
	
	}
	
	/** 
	 * Tests to insure incorrect Pin with Card1 fails
	 * Tests to insure incorrect Pin with Card2 fails
	 */
	@Test
	public void CheckValidAccountIncorrectPin() {
		setup();
		assertFalse(_bank.validate(card1, 1235));
		assertFalse(_bank.validate(card2, 6781)); 
	
	}
	
	
	/** 
	 * Tests if card1's Balance is correct to corresponding beginning balance
	 * Tests if card2's Balance is correct to corresponding beginning balance
	 */
	@Test
	public void TestBalanceOfEachAccount() {
		setup();
		assertEquals(80.00, _bank.getBalance(card1), 0); 
		assertEquals(60.00, _bank.getBalance(card2), 0); 	
	
	}
	
	/** 
	 * Tests depositing from card1 of 90.00
	 * Tests deposit from card 2 of 10.00
	 */
	@Test
	public void TestDeposit() {
		setup();
		assertTrue(_bank.deposit(card1, 10.00));
		assertEquals(90.00, _bank.getBalance(card1), 0); 
	
		assertTrue(_bank.deposit(card2, 10.00)); 
		assertEquals(70.00, _bank.getBalance(card2), 0); 
	
	}
	
	
	/** 
	 * Tests if card1's Balance is correct to corresponding beginning balance
	 * Tests if card2's Balance is correct to corresponding beginning balance
	 */
	@Test
	public void TestWithdraw() {
		setup();
		assertTrue(_bank.withdraw(card1, 30.00)); 
		assertEquals(50.00, _bank.getBalance(card1), 0); 
	
		assertTrue(_bank.withdraw(card2, 50.00)); 
		assertEquals(10.00, _bank.getBalance(card2), 0); 
	
	}
	
	/** 
	 * Tests Over withdrawing from card1 of 120.00 when balance is 60.00
	 * Tests Over withdrawing from card2 of 60.00 when balance is 20.00
	 */
	@Test
	public void OverWithDraw() {
		setup();
		assertFalse(_bank.withdraw(card1, 120.00)); 
		assertEquals(80.00, _bank.getBalance(card1), 0); 
		
		assertFalse(_bank.withdraw(card2, 70.00)); 
		assertEquals(60.00, _bank.getBalance(card2), 0); 
	
	}
	
	/** 
	 * Tests withdrawing a negative number from card1
	 * Tests withdrawing a negative number from card2
	 *
	 */
	
	@Test
	public void WithDrawNegative() {
		setup();
		assertFalse(_bank.withdraw(card1, -100.00)); 
		assertEquals(80.00, _bank.getBalance(card1), 0); 
		
		assertFalse(_bank.withdraw(card2, -200.00)); 
		assertEquals(60.00, _bank.getBalance(card2), 0); 
	
	}
	
	
	/**
	 * Tests ATM commands
	 */
	@Test
	public void CommandsTest(){
		setup();
		
		String input = "CARDREAD 1234";
		String[] inputArray = input.split(" ");
		
		String command;
		String cardNumber;
		
		if(inputArray.length ==2){
			command = inputArray[0].toUpperCase();
			cardNumber = inputArray[1].toUpperCase();
		}
		
		assertTrue(_atm.getState().equals("NOCUSTOMER"));
		try{
			_atm.runCommand(inputArray);
		}catch(Exception e){
			fail();
		}
		
	}
	
}