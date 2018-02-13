
public class Account {
	
	int _accountNumber;
	int _pinCode;
	double _balance;
	
	/** Creates a new account with an account number, pin code, and starting balance.
	 * 	Use other constructor for $0 starting balance
	 * 
	 * @param accountNumber The account number for the account, positive integer
	 * @param pinCode The pin code for the account, positive integer
	 * @param balance The starting balance of the account as a double
	 */
	public Account(int accountNumber, int pinCode, double balance) {
		_accountNumber = accountNumber;
		_pinCode = pinCode;
		_balance = balance;
	}
	
	/** Creates a new account with an account number and pin code. The starting balance is $0.
	 * 	Use other constructor for non-zero starting balance.
	 * 
	 * @param accountNumber The account number for the account, positive integer
	 * @param pinCode The pin code for the account, positive integer
	 */
	public Account(int accountNumber, int pinCode) {
		_accountNumber = accountNumber;
		_pinCode = pinCode;
		_balance = 0;
	}
	
	/** 
	 * @return The account number of the account as an integer
	 */
	public int getAccountNumber(){
		return _accountNumber;
	}
	
	/**
	 * @return The pin code of the account for bank validation as an integer
	 */
	public int getPinCode(){
		return _pinCode;
	}
	
	/**
	 * @return The balance of the account as a double
	 */
	public double getBalance() {
		return _balance;
	}
	
	/** Validates a withdrawal value and 
	 * 
	 * @param money
	 * @return
	 */
	public boolean withdrawal(double money) {
		if(_balance > money) {
			_balance -= money;
			return true;
		}
		return false;
	}
	
	public void deposite(double money) {
		_balance += money;
	}
}
