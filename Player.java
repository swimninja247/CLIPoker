/* Noah Czelusta
 * nhc2117
 * Player.java represents a player in poker
 */

import java.util.ArrayList;

public class Player {
	
		
	private ArrayList<Card> hand; //the player's cards
	private double bankroll; // credit balance
    private double bet;
		
    // Instantiate player object with 100 credits to start
	public Player(){		
	    hand = new ArrayList<Card>();
        bankroll = 100;
	}

    // Add a card to the back of hand arraylist
	public void addCard(Card c){
        hand.add(c);
	}

    // Remove card specified in parameter
	public void removeCard(Card c){
	    hand.remove(c);
    }
		
    // Subtract credits from bankroll based on bet amt
    public void bets(double amt){
        bankroll -= amt;
    }

    // Add winnings to bankroll based on amt
    public void winnings(double amt){
        bankroll += amt;
    }

    // Getter method for bankroll
    public double getBankroll(){
        return bankroll;
    }
    
    // Getter method for bet
    public double getBet() {
        return bet;
    }
    
    // Setter method for bet
    public void setBet(double x) {
        bet = x;
    }
    
    // Getter method for hand
    public ArrayList<Card> getHand() {
        return hand;
    }
    
    // Makes hand an empty arraylist
    public void resetHand() {
        hand = new ArrayList<Card>();
    }
}


