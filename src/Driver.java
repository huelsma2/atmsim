import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Test driver which creates a bank with a map of accounts, and simulates a Customer putting their card
 * in a machine.
 * @author Steve Messer
 *
 */
public class Driver {
	
	static final String _fileLocation = "InputCommands.txt";
	
	static Scanner stdin = new Scanner(System.in);
	static ATM atm;

	public static void main(String[] args)
	{
		/// Initialize testing values
		HashMap<Integer, Account> accountList = new HashMap<Integer, Account>();
		accountList.put(1234, new Account(1234, 6789, 80));
		accountList.put(6789, new Account(6789, 4321, 60));
		// Create the bank and ATM
		Bank _bank = new Bank(accountList);
		atm = new ATM(_bank);
		// Start the simulation loop
		simulationLoop();
	}
	
	 private static void simulationLoop() {
		 System.out.println("[C]onsole, [F]ile, or [E]xit");
		 String input = stdin.next();
		 if(input.equals("C") || input.equals("c")) consoleInput();
		 else if(input.equals("F") || input.equals("f")) fileInput();
		 else if(input.equals("E") || input.equals("e")) exitSim();
		 simulationLoop();
	 }
	 
	 private static void consoleInput() {
		 System.out.println("ATM command ([E]xit):");
		 String input = stdin.nextLine();
		 if(input.equals("E") || input.equals("e")) simulationLoop();
		 inputCommand(input);
		 consoleInput();
	 }
	 
	 private static void fileInput() {
		 Scanner fileScanner = null;
		 InputStream in = Driver.class.getResourceAsStream(_fileLocation);
		 String input;
		 
		 try {
				fileScanner = new Scanner(in);
			} catch (Exception e) {
					System.out.println("Failed at opening resource " + e);
					System.exit(0);
			}
		 while(fileScanner.hasNextLine()) {
			 input = fileScanner.nextLine();
			 inputCommand(input);
		 }
		 try {
			in.close();
		} catch (IOException e) {
			System.out.println("Failed at closing resource " + e);
		}
		 simulationLoop();
	 }
	 
	 private static void inputCommand(String input) {
		 String[] inputArray = input.split(" ");
		 String command;
		 String mod;
		 boolean goodCommand = false;
		 
		 if(inputArray.length < 2 || inputArray.length > 3) return;
		 if(inputArray.length == 2) {
			 command = inputArray[0].toUpperCase();
			 mod = inputArray[1].toUpperCase();
		 }
		 else {
			 command = inputArray[1].toUpperCase();
			 mod = inputArray[2].toUpperCase();
		 }
		 switch(command) {
		 	case "CARDREAD" :
		 	case "NUM" :
		 	case "DIS" :
		 	case "PRINT" : goodCommand = true;
		 		break;
		 	case "BUTTON" :
		 		switch(mod) {
		 			case "W" :
		 			case "CB" :
		 			case "CANCEL" :
		 			case "D" : goodCommand = true;
		 				break;
		 		}
		 }
		 if(goodCommand) atm.runCommand(inputArray);
	 }
	 
	 private static void exitSim() {
		 stdin.close();
		 System.exit(0);
	 }
}
