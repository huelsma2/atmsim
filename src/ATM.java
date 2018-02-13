import java.util.Scanner;

public class ATM {

		private boolean inserted;
		private Account testAccount;
		private Scanner input = new Scanner(System.in);
		private Bank testBank;
		private int accountNum;
		private int PIN;
		private String action;
		
		public void execute(){
			
		}
		
		public void start(){
			if(inserted == true){
				
			System.out.print("Please insert card(enter account number): ");
			accountNum = input.nextInt();
			System.out.printf("Please enter PIN code: ");
			PIN = input.nextInt();
			testAccount = new Account(accountNum, PIN);
			//testBank.validate(testAccount);
			System.out.print("Would you like to Withdrawal(W) or Deposit(D):  ");
			action = input.nextLine();
			//execute();
			
			}
			
		}


}
