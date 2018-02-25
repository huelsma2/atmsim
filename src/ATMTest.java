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
	 * Tests ATM commands
	 */
	@Test
	public void TestCommands1(){
		setup();
		
		String input = "CARDREAD 1234";
		String[] inputArray = input.split(" ");
		
		String command;
		String cardNumber;
		
		if(inputArray.length ==2){
			command = inputArray[0].toUpperCase();
			cardNumber = inputArray[1].toUpperCase();
		}
		
		try{
			_atm.runCommand(inputArray);
		}catch(Exception e){
			fail();
		}
		
	}
	
	/**
	 * Tests ATM commands
	 */
	@Test
	public void TestCommands2(){
		setup();
		
		String input = "CARDREAD 1234";
		String[] inputArray = input.split(" ");
		
		String command;
		String cardNumber;
		
		if(inputArray.length ==2){
			command = inputArray[0].toUpperCase();
			cardNumber = inputArray[1].toUpperCase();
		}
		
		try{
			_atm.runCommand(inputArray);
		}catch(Exception e){
			fail();
		}
		
	}
	
	/**
	 * Tests ATM commands
	 */
	@Test
	public void TestNoCustomerState(){
		setup();

		assertTrue(_atm.getState().equals("NOCUSTOMER"));
		
	}
	
	/**
	 * Tests ATM commands
	 */
	@Test
	public void TestTakePinState(){
		setup();

		assertTrue(_atm.getState().equals("NOCUSTOMER"));
		
	}
	
	/**
	 * Tests ATM commands
	 */
	@Test
	public void TestTakeButtonState(){
		setup();

		assertTrue(_atm.getState().equals("NOCUSTOMER"));
		
	}
	
	/**
	 * Tests ATM commands
	 */
	@Test
	public void TestTakeWithdrawState(){
		setup();

		assertTrue(_atm.getState().equals("NOCUSTOMER"));
		
	}
	
	/**
	 * Tests ATM commands
	 */
	@Test
	public void TestTakeDepositState(){
		setup();

		assertTrue(_atm.getState().equals("NOCUSTOMER"));
		
	}
	
}