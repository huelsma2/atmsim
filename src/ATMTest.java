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
	 * Tests the CARDREAD command
	 */
	@Test
	public void TestCARDREAD(){
		setup();
		
		String input = "CARDREAD 1234";
		String[] inputArray = input.split(" ");
		
		try{
			_atm.runCommand(inputArray);
		}catch(Exception e){
			fail();
		}
		
	}
	
	/**
	 * Tests the NUM command
	 */
	@Test
	public void TestNUM(){
		setup();
		
		_atm.setState("TAKEPIN");
		String input = "NUM 6789";
		String[] inputArray = input.split(" ");
		
		try{
			_atm.runCommand(inputArray);
		}catch(Exception e){
			fail();
		}
		
	}
	
	/**
	 * Tests the BUTTON W command
	 */
	@Test
	public void TestBUTTONW(){
		setup();
		
		_atm.setState("TAKEBUTTON");
		String input = "BUTTON W";
		String[] inputArray = input.split(" ");
		
		try{
			_atm.runCommand(inputArray);
		}catch(Exception e){
			fail();
		}
		
	}
	
	/**
	 * Tests the BUTTON D command
	 */
	@Test
	public void TestBUTTOND(){
		setup();
		
		_atm.setState("TAKEBUTTON");
		String input = "BUTTON D";
		String[] inputArray = input.split(" ");
		
		try{
			_atm.runCommand(inputArray);
		}catch(Exception e){
			fail();
		}
		
	}
	
	/**
	 * Tests the BUTTON CB command
	 */
	@Test
	public void TestBUTTONCB(){
		setup();
		
		String input = "CARDREAD 1234";
	    String[] inputArray = input.split(" ");

	      _atm.runCommand(inputArray);
	    input = "NUM 6789";
	    inputArray = input.split(" ");
	      _atm.runCommand(inputArray);

		

		input = "BUTTON CB";
		inputArray = input.split(" ");
		
		try{
			_atm.runCommand(inputArray);
		}catch(Exception e){
			fail();
		}
		
	}
	
	/**
	 * Tests the BUTTON CANCEL command
	 */
	@Test
	public void TestBUTTONCANCEL(){
		setup();
		
		_atm.setState("TAKEBUTTON");
		String input = "BUTTON CANCEL";
		String[] inputArray = input.split(" ");
		
		try{
			_atm.runCommand(inputArray);
		}catch(Exception e){
			fail();
		}
		
	}
	
	/**
	 * Tests to ensure NOCUSTOMER state
	 */
	@Test
	public void TestNoCustomerState(){
		setup();

		assertTrue(_atm.getState().equals("NOCUSTOMER"));
		
	}
	
	/**
	 * Tests to ensure TAKEPINSTATE
	 */
	@Test
	public void TestTakePinState(){
		setup();
		
	    String input = "CARDREAD 1234";
	    String[] inputArray = input.split(" ");

	      _atm.runCommand(inputArray);
	      
	      assertTrue(_atm.getState().equals("TAKEPIN"));
		
	}
	
	/**
	 * Tests to ensure TAKEBUTTONSTATE
	 */
	@Test
	public void TestTakeButtonState(){
		setup();
		
	    String input = "CARDREAD 1234";
	    String[] inputArray = input.split(" ");

	      _atm.runCommand(inputArray);
	      
	      assertTrue(_atm.getState().equals("TAKEPIN"));
	      
	    input = "NUM 6789";
	    inputArray = input.split(" ");
	      _atm.runCommand(inputArray);

		assertTrue(_atm.getState().equals("TAKEBUTTON"));
		
	}
	
	/**
	 * Tests to ensure TAKEWITHDRAWSTATE
	 */
	@Test
	public void TestTakeWithdrawState(){
		setup();
		
	    String input = "CARDREAD 1234";
	    String[] inputArray = input.split(" ");

	      _atm.runCommand(inputArray);
	      
	      assertTrue(_atm.getState().equals("TAKEPIN"));
	      
	    input = "NUM 6789";
	    inputArray = input.split(" ");
	      _atm.runCommand(inputArray);
	      
		    input = "BUTTON W";
		    inputArray = input.split(" ");

		      _atm.runCommand(inputArray);

		assertTrue(_atm.getState().equals("TAKEWITHDRAW"));
		
	}
	
	/**
	 * Tests to ensure TAKEDEPOSITSTATE
	 */
	@Test
	public void TestTakeDepositState(){
		setup();
		
	    String input = "CARDREAD 1234";
	    String[] inputArray = input.split(" ");

	      _atm.runCommand(inputArray);
	      
	      assertTrue(_atm.getState().equals("TAKEPIN"));
	      
	    input = "NUM 6789";
	    inputArray = input.split(" ");
	      _atm.runCommand(inputArray);
	      
		    input = "BUTTON D";
		    inputArray = input.split(" ");

		      _atm.runCommand(inputArray);

		assertTrue(_atm.getState().equals("TAKEDEPOSIT"));
		
	}
	
	
	/**
	   * Exists to fulfill the Lab 5 requirement of test output Lines A-K
	   */
	  @Test
	  public void TestCommands1(){
	    setup();
	    System.out.println("Begin Testing Section 5 Steps A - K: ");
	    String input = "CARDREAD 1234";
	    String[] inputArray = input.split(" ");
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	      
	    }
	    input = "NUM 6789";
	    inputArray = input.split(" ");
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	    }
	    input = "BUTTON W";
	    inputArray = input.split(" ");
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	      
	    }
	    input = "NUM 20";
	    inputArray = input.split(" ");
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	      
	    }
	    input = "BUTTON CB";
	    inputArray = input.split(" ");
	    
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	      
	    }
	    input = "BUTTON CANCEL";
	    inputArray = input.split(" ");
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	      
	    }
	    System.out.println(" ");
	  }
	  
	  /**
	   * Exists to fulfill the Lab 5 requirement of test output Lines L-Y
	   */
	  @Test
	  public void TestCommands2(){
	    setup();
	    System.out.println("Begin Testing Section 5 Steps L - Y: ");
	    
	    String input = "CARDREAD 6789";
	    String[] inputArray = input.split(" ");
	  
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	      
	    }
	    input = "NUM 4444"; //first invalid pin
	    inputArray = input.split(" ");
	      
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	      
	    }
	    input = "NUM 5555"; //second invalid pin
	    inputArray = input.split(" ");
	      
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	      
	    }
	    input = "NUM 4321"; //Valid Pin entered 
	    inputArray = input.split(" ");
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	      
	    }
	    input = "BUTTON W";
	    inputArray = input.split(" ");
	    
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	      
	    }
	    input = "NUM 100";
	    inputArray = input.split(" ");
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	      
	    }
	    input = "BUTTON CB";
	    inputArray = input.split(" ");
	    
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	      
	    }
	    input = "BUTTON CANCEL";
	    inputArray = input.split(" ");
	    
	    try{
	      _atm.runCommand(inputArray);
	    }catch(Exception e){
	      fail();
	      
	    }
	    System.out.println(" ");
	  }
	
}