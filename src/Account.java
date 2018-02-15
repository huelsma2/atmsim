/** Stores the account of a user. Independently validates withdrawals, but deposits and access to the account
 * must be validated by the bank.
 * 
 * @author Steven Messer
 *
 */


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
	
	/** Validates and withdrawals money from the account
	 * 
	 * @param money Dollar value to attempt to withdrawal from the account
	 * @return True if money successfully removed from the account, false if non enough money
	 */
	public boolean withdrawal(double money) {
		if(_balance >= money) {
			_balance -= money;
			return true;
		}
		return false;
	}
	
	/** Deposits money into the account
	 * 
	 * @param money The money to deposit
	 */
	public void deposit(double money) {
		_balance += money;
	}
}
