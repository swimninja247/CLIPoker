/* Noah Czelusta
 * nhc2117
 *  Card.java represents a playing card
 */

public class Card implements Comparable<Card>{
	
	private int suit; //use integers 1-4 to encode the suit, c, d, h, s
	private int rank; //use integers 1-13 to encode the rank
	
    //make a card with suit s and value v
	public Card(int s, int r) {
        suit = s;
        rank = r;
	}
	
    // Compare to method for Card type
	public int compareTo(Card c){
        if (this.rank != c.rank) {
            return (this.rank-c.rank/Math.abs(this.rank-c.rank));
        }
        else {
            if (this.suit < c.suit) {
                return -1;
            }
            else if (this.suit > c.suit) {
                return 1;
            }
            else {
                return 0;
            }
        }
	}
	
    // toString method for card type
	public String toString(){
        return rankToString() + " of " + suitToString();
	}

    // Getter method for rank
    public int getRank() {
        return rank;
    }
    
    // Getter method for suit
    public int getSuit() {
        return suit;
    }
    
    // Returns string corresponding to card rank
    private String rankToString() {
        if (rank >= 2 && rank <= 10) {
            return String.valueOf(rank);
        }
        else if (rank == 11) {
            return "Jack";
        }
        else if (rank == 12) {
            return "Queen";
        }
        else if (rank == 13) {
            return "King";
        }
        else {
            return "Ace";
        }
    }
    
    // Returns string corresponding to card suit
    private String suitToString() {
        String output;
        if (suit == 1) {
            output = "Clubs";
        }
        else if (suit == 2) {
            output = "Diamonds";
        }
        else if (suit == 3) {
            output = "Hearts";
        }
        else {
            output = "Spades";
        }
        return output;
    }
}
