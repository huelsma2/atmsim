import java.util.Scanner;

public class ATM {

		private Bank testBank;
		private Card testCard;
		private int PIN;
		private Scanner input = new Scanner(System.in);
		private int amount;
		
		
		public ATM(Bank newBank){
			
			this.testBank = newBank;
			
		}
		
		public void execute(Card card1, char transaction){
			
			
			if(transaction == 'W' || transaction == 'w'){
				System.out.println("Enter withdrawal amount: ");
				try{
					amount = input.nextInt();
				}catch(NumberFormatException e){
					System.out.println("Invalid amount");
				}
				
				if(testBank.withdraw(card1, amount)){
					System.out.println("Transaction successful. Previous balance: ");
				}else{
					System.out.println("Transaction unsuccessful. Previous balance: ");
				}
			}
			
			if(transaction == 'D' || transaction == 'd'){
				System.out.printf("Enter deposit amount: ");
				try{
					amount = input.nextInt();
				}catch(NumberFormatException e){
					System.out.println("Invalid amount");
				}
				
				if(testBank.deposit(card1, amount)){
					System.out.println("Transaction successful. Previous balance: ");
				}else{
					System.out.println("Transaction unsuccessful. Previous balance: ");
				}
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
			
			if(testBank.validate(testCard, PIN)){
				
			System.out.println("Would you like to Withdrawal(W) or Deposit(D)?:  ");
			char transaction = input.next().charAt(0);
			execute(testCard, transaction);
		}else{
			return;
		}
	}
}
