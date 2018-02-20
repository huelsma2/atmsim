/**
 * ATM class simulates the ATM that a customer/actor will interact with.
 * ATM takes the input account number, validates, transacts, and prints receipt.
 * @author Andrew Yehle, Andrew Huelsman
 */

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class ATM {

		private Bank testBank;
		private Card testCard;
		private int PIN;
		private int amount = 0;
		private Printer _printer;
		private CashDispenser _cashDispenser;
		private CardReader _cardReader;
		
		private static final String[] COMMANDS = {"CARDREAD", "NUM", "DIS", "PRINT", "BUTTON"};
		
		private static enum STATES {NOCUSTOMER, TAKENUMBER, TAKEBUTTON};
		private STATES state;
		
		/**
		 * Creates an ATM object that takes an input of a Bank object.
		 * @param newBank Input of a Bank object for the constructor.
		 */
		public ATM(Bank newBank){
			
			this.testBank = newBank;
			state = STATES.NOCUSTOMER;
		}
		
		/**
		 * Execute method takes an input of a card and a 'W' or 'D' character
		 * from the start() method to create a withdrawl or deposit transaction.
		 * @param card1
		 * @param transaction
		 */
		public void execute(Card card1, char transaction, Scanner input){
			
			
			if(transaction == 'W' || transaction == 'w'){
				System.out.println("Enter withdrawal amount: ");
				try{
					amount = input.nextInt();
				}catch(NumberFormatException e){
					System.out.println("Invalid amount");
				}
				
				if(testBank.withdraw(card1, amount)){
					System.out.println("Transaction successful. \nPrevious balance: $" + (testBank.getBalance(card1) + amount) + "\nWithdrawn: $" + amount + "\nCurrent Balance: $" + testBank.getBalance(card1));
				}else{
					amount = 0;
					System.out.println("Transaction unsuccessful. \nPrevious balance: $" + (testBank.getBalance(card1) + amount) + "\nWithdrawn: $" + amount + "\nCurrent Balance: $" + testBank.getBalance(card1));
				}
			}
			
			if(transaction == 'D' || transaction == 'd'){
				System.out.println("Enter deposit amount: ");
				try{
					amount = input.nextInt();
				}catch(NumberFormatException e){
					System.out.println("Invalid amount");
				}
				
				if(testBank.deposit(card1, amount)){
					System.out.println("Transaction successful. \nPrevious balance: $" + (testBank.getBalance(card1) - amount) + "\nDeposited: $" + amount + "\nCurrent Balance: $" + testBank.getBalance(card1));
				}else{
					amount = 0;
					System.out.println("Transaction unsuccessful. \nPrevious balance: $" + (testBank.getBalance(card1) - amount) + "\nDeposited: $" + amount + "\nCurrent Balance: $" + testBank.getBalance(card1));
				}
			}
			
		}
		
		/**Start method takes an input of account number and initiates the ATM
		 * process of waiting for account number input.
		 * @param accountNum
		 */
		public void start(int accountNum, Scanner input){
				
			testCard = new Card(accountNum);
			
			if(!testBank.validate(testCard)){
				System.out.println("Account number not recognized.");
				return;
			}
			
			System.out.println("Please enter PIN code: ");
			try{
				PIN = input.nextInt();
			}catch(NumberFormatException e){
				System.out.println("Invalid PIN code");
				return;
			}
			
			if(testBank.validate(testCard, PIN)){
				
			System.out.println("Would you like to Withdrawal(W) or Deposit(D)?:  ");
			char transaction = input.next().charAt(0);
			execute(testCard, transaction,input);
		}else{
			return;
			}
		}
		
		public void runCommand(String[] command)
		{
			boolean hasTime = command.length==3;
			switch(hasTime? command[1].toUpperCase() : command[0].toUpperCase())
			{
			case "CARDREAD":
			{
				if(state != STATES.NOCUSTOMER) 
				{
					System.out.println("I'm already reading a card, asshole");
					break;
				}
				if(hasTime) printTime(command[0]); else printTime();
				try{ 
					testCard = new Card(_cardReader.acctNumber(Integer.parseInt(hasTime? command[2] : command[1])));
					System.out.println("Card read. Account number: " + testCard.accountNumber());
					state = STATES.TAKENUMBER;
					if(hasTime) printTime(command[0]); else printTime();
					System.out.println("Enter Pin:");
				}
				catch(NumberFormatException e){System.out.println("Invalid Card...");}
				break;
			}
			case "NUM":
			{
				if(state != STATES.TAKENUMBER) 
				{
					System.out.println("Don't you put numbers in me when I'm not ready, asshole");
					break;
				}
				if(hasTime) printTime(command[0]); else printTime();
				try{ 
					testCard = new Card(_cardReader.acctNumber(Integer.parseInt(hasTime? command[2] : command[1])));
					System.out.println("Card read. Account number: " + testCard.accountNumber());
					state = STATES.TAKENUMBER;
				}
				catch(NumberFormatException e){System.out.println("Invalid Card...");}
				break;
			}
				
			}
		}

		private void printTime()
		{
			Timestamp ts = new Timestamp(new Date().getTime());
			System.out.print(ts.toString().split(" ")[1].split(".")[0]);
		}
		
		private void printTime(String t)
		{
			System.out.print(t);
		}
}
