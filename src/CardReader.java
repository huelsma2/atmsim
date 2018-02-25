/**
 * 
 * @author Andrew Yehle
 *Card reader owns the method acctNumber
 *which returns the card's account number
 *
 */
public class CardReader {
	
	public CardReader(){
		
	}
	
	public int acctNumber(Card newCard){
		
		return newCard.accountNumber();
		
	}
	
}