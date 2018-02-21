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
		
		//A set of all valid states the ATM can be in. eg it can be waiting for a customer, waiting
		//		for a pin, waiting for a button, or waiting for transaction amounts.
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
		
		/**
		 * Private helper method to "display a message on the screen" (print to the console) by calling 
		 * runCommand with a "DIS" command.
		 * @param command command is sent in simply to check if a timestamp was included or not
		 * @param message message to be "displayed on the screen"
		 */
		private void println(String[] command, String message)
		{
			boolean hasTime = command.length==3;
			if(hasTime)
				runCommand(command[0], "DIS", message);
			else
				runCommand("DIS", message);
		}
		
		/**
		 * Takes in a command (Assumed to be tested as a valid command from the simulator), and performs
		 * an operation according to the command. The command argument is validated in each case.
		 * @param command A tuple of 2 or 3 strings, which, in the former, contains a command and an argument.
		 * in the latter, it contains a timestamp, a command, and an argument.
		 */
		public void runCommand(String... command)
		{
			boolean hasTime = command.length==3;
			switch(hasTime? command[1].toUpperCase() : command[0].toUpperCase())
			{
			case "CARDREAD":
			{
				if(state != STATES.NOCUSTOMER) 
				{
					println(command, "New Card cannot be read during an existing transaction");
					break;
				}
				try{ 
					testCard = new Card(_cardReader.acctNumber(new Card(Integer.parseInt(hasTime? command[2] : command[1]))));
					if(testBank.validate(testCard))
					{
						println(command,"Card read. Account number: " + testCard.accountNumber());
						state = STATES.TAKEPIN;
						println(command,"Enter Pin:");
					}
					else
					{
						println(command,"Account does not exist");
					}
				}
				catch(NumberFormatException e){println(command,"Invalid account number");}
				break;
			}
			case "NUM":
			{
				if(state != STATES.TAKEPIN && state != STATES.TAKEWITHDRAW && state != STATES.TAKEDEPOSIT) 
				{
					println(command,"Entering a number has no effect during the current state of the ATM");
					break;
				}
				try{ 
					lastNumber = Integer.parseInt(hasTime? command[2] : command[1]);
					if(state==STATES.TAKEPIN) 
					{
						if(testBank.validate(testCard))
						{
							println(command,"Pin entered. Choose Transaction:");
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
							println(command,"Money deposited");
							state=STATES.TAKEBUTTON;
							}
						else
							System.out.println("how did you get here");
					}
				}
				catch(NumberFormatException e){println(command, "Invalid Number..."); lastNumber=-1;}
				break;
			}

			case "BUTTON":
			{
				if(state != STATES.TAKEBUTTON && ((hasTime && !command[2].toLowerCase().equals("cancel"))
						|| (!hasTime && !command[1].toLowerCase().equals("cancel")))) 
				{
					println(command,"Button has no effect currently.");
					break;
				}
				String buttonType = hasTime? command[2] : command[1];
				if(buttonType.toLowerCase().equals("w"))
				{
					println(command,"Enter amount:");
					state = STATES.TAKEWITHDRAW;
				}
				else if(buttonType.toLowerCase().equals("d"))
				{
					println(command,"Enter amount:");
					state = STATES.TAKEDEPOSIT;
				}
				else if (buttonType.toLowerCase().equals("cb"))
				{
					println(command,"Balance: " + testBank.getBalance(testCard));
				}
				else if(buttonType.toLowerCase().equals("cancel"))
				{
					state = STATES.NOCUSTOMER;
				}
				else
				{
					System.out.println("That's not a button...");
				}
				break;
			}
			
			case "PRINT":
			{
				Timestamp ts = new Timestamp(new Date().getTime());
				
				_printer.print(hasTime? command[0] : ts.toString().split(" ")[1].split(".")[0], 
						hasTime? command[1] : command[0], hasTime? command[2]:command[1]);
				break;
			}
			
			case "DIS":
			{
				if(hasTime) printTime(command[0]); else printTime();
				System.out.println(hasTime? command[1]:command[0]);
			}
			default: {System.out.println("This should not be able to happen!"); break;}
			}
		}

		/**
		 * Prints out the current time according to the system running this simulation.
		 */
		public void printTime()
		{
			Timestamp ts = new Timestamp(new Date().getTime());
			System.out.print(ts.toString().split(" ")[1].split(".")[0]);
		}
		
		/**
		 * Prints out a predefined timestamp
		 * @param t the timestamp
		 */
		private void printTime(String t)
		{
			System.out.print(t);
		}
}
