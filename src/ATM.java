/**
 * ATM class simulates the ATM that a customer/actor will interact with.
 * ATM takes the input account number, validates, transacts, and prints receipt.
 * @author Andrew Yehle
 */

import java.util.Scanner;

public class ATM {

		private Bank testBank;
		private Card testCard;
		private int PIN;
		private Scanner input = new Scanner(System.in);
		private int amount = 0;
		
		/**
		 * Creates an ATM object that takes an input of a Bank object.
		 * @param newBank Input of a Bank object for the constructor.
		 */
		public ATM(Bank newBank){
			
			this.testBank = newBank;
			
		}
		
		/**
		 * Execute method takes an input of a card and a 'W' or 'D' character
		 * from the start() method to create a withdrawl or deposit transaction.
		 * @param card1
		 * @param transaction
		 */
		public void execute(Card card1, char transaction){
			
			
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
		public void start(int accountNum){
				
			testCard = new Card(accountNum);
			
			if(!testBank.validate(testCard)){
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
			execute(testCard, transaction);
		}else{
			return;
		}
	}
}
