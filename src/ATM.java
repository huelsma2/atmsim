import java.util.Scanner;

public class ATM {

		private Bank testBank;
		private Card testCard;
		private int PIN;
		private Scanner input = new Scanner(System.in);
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
				testBank.withdraw(card1, amount);
			}
			
			if(transaction == 'D'){
				System.out.printf("Enter deposit amount: ");
				try{
					amount = input.nextInt();
				}catch(NumberFormatException e){
					System.out.println("Invalid amount");
				}
				testBank.deposit(card1, amount);
			}
			
			
		}
		
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
			
			System.out.println("Would you like to Withdrawal(W) or Deposit(D):  ");
			char transaction = input.next().charAt(0);
			execute(testCard, transaction);
			
		}


}
