import java.util.Scanner;

public class ATM {

		private Bank testBank;
		private Card testCard;
		private int PIN;
		private Scanner input = new Scanner(System.in);
		private String action;
		private int amount;
		
		
		public ATM(Bank inputBank){
			
			this.testBank = inputBank;
			
		}
		
		public void execute(Card card1, char transaction){
			
			
			
			if(transaction == 'W'){
				System.out.println("Enter withdrawal amount: ");
				try{
					amount = input.nextInt();
				}catch(NumberFormatException e){
					System.out.println("Invalid amount");
				}
				//testBank.getAccount(card1).withdrawal(amount);
			}
			
			if(transaction == 'D'){
				System.out.printf("Enter deposit amount: ");
				try{
					amount = input.nextInt();
				}catch(NumberFormatException e){
					System.out.println("Invalid amount");
				}
				//testBank.getAccount(card1).deposit(amount);
			}
			
			
		}
		
		public void start(int accountNum){
				
			testCard = new Card(accountNum);
			if(!testBank.validate(testCard)){
				return;
			}
			
			accountNum = input.nextInt();
			System.out.println("Please enter PIN code: ");
			
			try{
				PIN = input.nextInt();
			}catch(NumberFormatException e){
				System.out.println("Invalid PIN code");
				return;
			}
			
			System.out.println("Would you like to Withdrawal(W) or Deposit(D):  ");
				action = input.nextLine();
			char transaction = action.charAt(0);
			execute(testCard, transaction);
			
		}


}
