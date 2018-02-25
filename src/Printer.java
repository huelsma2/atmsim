/**
 * 
 * @author Andrew Yehle
 * Returns a string that acts as the receipt given inputs
 * time, transaction type, and money amount
 *
 */
public class Printer {

	public Printer(){
		
	}
	
	public String print(String time, String transaction, String amount){
		
		return time + " " + transaction + " $" + amount;
	}
	
}