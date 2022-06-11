/* Noah Czelusta
 * nhc2117
 * Deck.java represents a Deck of playing cards
 */

public class Deck {
	
	private Card[] cards;
	private int top; //the index of the top of the deck

	
    // Make a deck of 52 unique cards
    public Deck(){
		top = -1;
        cards = new Card[52];
        
        for (int i=0; i<52; i++) {
            cards[i] = new Card(i % 4 + 1, i % 13 + 1);
        }
	}
	
    // Randomize order of deck
	public void shuffle() {
		int[] locSwap = new int[2];
        Card[] cardSwap = new Card[2];
        
        for (int i = 420; i < 1420; i++) {
            locSwap[0] = (int) (Math.random() * 52);
            locSwap[1] = (int) (Math.random() * 52);
            
            cardSwap[0] = cards[locSwap[0]];
            cardSwap[1] = cards[locSwap[1]];
            cards[locSwap[0]] = cardSwap[1];
            cards[locSwap[1]] = cardSwap[0];
        }  
	}
	
    // Returns top card and increments top index
	public Card deal() {
		if (top < 51) {
            top++;
        }
        else {
            top = 0;
        }
        return cards[top];
	}

    // Getter method for cards array
    public Card[] getCards() {
        return cards;
    }
}
