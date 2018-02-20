/**
 * ATM class simulates the ATM that a customer/actor will interact with.
 * ATM takes the input account number, validates, transacts, and prints receipt.
 * @author Andrew Yehle, Andrew Huelsman
 */

import java.sql.Timestamp;
import java.util.Date;

public class ATM {

		private Bank testBank;
		private Card testCard;
		private int lastNumber = -1;
		private Printer _printer;
		private CashDispenser _cashDispenser;
		private CardReader _cardReader;
		
		private static enum STATES {NOCUSTOMER, TAKEPIN, TAKEBUTTON, TAKEWITHDRAW, TAKEDEPOSIT};
		private STATES state;
		
		/**
		 * Creates an ATM object that takes an input of a Bank object.
		 * @param newBank Input of a Bank object for the constructor.
		 */
		public ATM(Bank newBank){
			
			this.testBank = newBank;
			state = STATES.NOCUSTOMER;
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
					testCard = new Card(_cardReader.acctNumber(new Card(Integer.parseInt(hasTime? command[2] : command[1]))));
					if(testBank.validate(testCard))
					{
						System.out.println("Card read. Account number: " + testCard.accountNumber());
						state = STATES.TAKEPIN;
						if(hasTime) printTime(command[0]); else printTime();
						System.out.println("Enter Pin:");
					}
					else
					{
						System.out.println("Account does not exist");
					}
				}
				catch(NumberFormatException e){System.out.println("Invalid Card...");}
				break;
			}
			case "NUM":
			{
				if(state != STATES.TAKEPIN && state != STATES.TAKEWITHDRAW && state != STATES.TAKEDEPOSIT) 
				{
					System.out.println("Don't you put numbers in me when I'm not ready, asshole");
					break;
				}
				if(hasTime) printTime(command[0]); else printTime();
				try{ 
					lastNumber = Integer.parseInt(hasTime? command[2] : command[1]);
					if(state==STATES.TAKEPIN) 
					{
						if(testBank.validate(testCard))
						{
							System.out.println("Pin entered. Choose Transaction:");
							state=STATES.TAKEBUTTON;
						}
					}
					else if(state==STATES.TAKEWITHDRAW)
					{
						if(testBank.withdraw(testCard, lastNumber))
							{
							_cashDispenser.dispense(lastNumber);
							state=STATES.TAKEBUTTON;
							}
						else
							System.out.println("Not enough cash, asshat");
					}
					else if (state==STATES.TAKEDEPOSIT)
					{
						if(testBank.deposit(testCard, lastNumber))
							{
							System.out.println("Thanks");
							state=STATES.TAKEBUTTON;
							}
						else
							System.out.println("how did you get here");
					}
				}
				catch(NumberFormatException e){System.out.println("Invalid Number..."); lastNumber=-1;}
				break;
			}
				
			}
		}

		public void printTime()
		{
			Timestamp ts = new Timestamp(new Date().getTime());
			System.out.print(ts.toString().split(" ")[1].split(".")[0]);
		}
		
		private void printTime(String t)
		{
			System.out.print(t);
		}
}
