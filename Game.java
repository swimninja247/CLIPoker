/* Noah Czelusta
 * nhc2117
 * Game.java represents a game of poker
 */

import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	
	private Player p;
	private Deck cards;
	// you'll probably need some more here
	private Scanner scan;
    private int payout;
    private boolean playAgain;
	
	public Game(String[] testHand){
		p = new Player();
        cards = new Deck();
        scan = new Scanner(System.in);
        for (String arg : testHand) {
            p.addCard(cmdCardConvert(arg));
        }
        playAgain = true;
	}
	
    // helper for constructor with explicit parameter
    private Card cmdCardConvert(String arg) {
        int rank = Integer.parseInt(arg.substring(1,arg.length()));
        if (arg.charAt(0) == 'c') {
            return new Card(1, rank);
        }
        else if (arg.charAt(0) == 'd') {
            return new Card(2, rank);
        }
        else if (arg.charAt(0) == 'h') {
            return new Card(3, rank);
        }
        else {
            return new Card(4, rank);
        }
    }
    
    
	public Game(){
		p = new Player();
        cards = new Deck();
        scan = new Scanner(System.in);
        playAgain = true;
	}
	
    
	// Next four methods are helpers for play()
    public void play(){
        System.out.print("Welcome to gambling.  ");
        while (playAgain) {
            playerBet();
            cards.shuffle();
            dealCards();
            listCards();
            playerChangeCards();
            dealCards();
            listCards();
            checkPayPlayAgain();
        }
    }
	
    private void listCards() {
        System.out.println("Here are your cards:\n");
        for (Card card : p.getHand()) {
            System.out.println("  " + card + "\n");
        }
    }
    
    private void dealCards() {
        while (p.getHand().size() < 5) {
            p.addCard(cards.deal());
        }
    }
    
    private void playerBet() {
        System.out.println("Your balance is " + p.getBankroll());
        
        // Prompt user
        System.out.print("How much would you like to bet? ");
        p.setBet(scan.nextDouble());
        
        // Update and show bankroll
        p.bets(p.getBet());
        System.out.println("\nYour new balance is " + p.getBankroll() + "\n");
    }
    
    private void playerChangeCards() {
        System.out.print("Pick cards to remove. ");
        System.out.println("Use 1-5 to choose respective cards. 0 to skip.");
        boolean playerSkip = false;
        while (p.getHand().size() > 0 && !playerSkip) {
            int input = scan.nextInt();
            if (input >= 1 && input <= 5) {
                p.removeCard(p.getHand().get(input - 1));
                listCards();
            }
            else {
                playerSkip = true;
            }
        }
    }
    
    private void checkPayPlayAgain() {
        System.out.println("Your hand has a " + checkHand(p.getHand()));
        p.winnings(p.getBet() * payout);
        System.out.println("Your new balance is " + p.getBankroll());
        System.out.println("Play again? (1 for yes, 0 for no)");
        if (scan.nextInt() == 1) {
            playAgain = true;
            p.resetHand();
        }
        else {
            playAgain = false;
        }
    }
    
    
    // Next four methods for checking hand
	private String checkHand(ArrayList<Card> hand){
		Collections.sort(hand);
        if (royalFlush(hand)) {
            payout = 250;
            return "Royal Flush";
        }
        else if (straight(hand) && flush(hand)) {
            payout = 50;
            return "Straight Flush";
        }
        else {
            return checkHandTwo(hand);
        }
	}
	
    private String checkHandTwo(ArrayList<Card> hand) {
        if (fourKind(hand)) {
            payout = 25;
            return "Four of a Kind";
        }
        else if (fullHouse(hand)) {
            payout = 6;
            return "Full House";
        }
        else {
            return checkHandThree(hand);
        }
    }
    
    private String checkHandThree(ArrayList<Card> hand) {
        if (flush(hand)) {
            payout = 5;
            return "Flush";
        }
        else if (straight(hand)) {
            payout = 4;
            return "Straight";
        }
        else if (threeKind(hand) != 0) {
            payout = 3;
            return "Three of a Kind";
        }
        return checkHandFour(hand);        
    }
    
    private String checkHandFour(ArrayList<Card> hand) {
        if (twoPair(hand)) {
            payout = 2;
            return "Two Pair";
        }
        else if (onePair(hand) != 0) {
            payout = 1;
            return "One Pair";
        }
        else {
            payout = 0;
            return "High Card";
        }
    }

    
    // Below return true if meet name condition unless noted otherwise
    private boolean royalFlush(ArrayList<Card> hand) {
        if (flush(hand) && aceHighStraight(hand)) {
            return true;
        }       
        else {
            return false;
        }
    }
    
    private boolean fourKind(ArrayList<Card> hand) {
        int fourKind = 0;
        for (int i = 0; i < 5; i++) {
            ArrayList<Card> temp = new ArrayList<Card>(hand);
            temp.remove(i);
            for (Card card : temp) {
                if (card.getRank() != temp.get(0).getRank()) {
                    fourKind--;
                    break;
                }
            }
        }
        return fourKind>-5;
    }
    
    private boolean fullHouse(ArrayList<Card> hand) {
        int threeKindRank = threeKind(hand);
        int pairRank = 0;
        
        for (int i = 0; i < 5; i++) {
            if (hand.get(i).getRank() != threeKindRank) {
                for (int j = i + 1; j < 5; j++) {
                    if (hand.get(i).getRank() == hand.get(j).getRank()) {
                        pairRank = hand.get(i).getRank();
                    }
                }
            }
        }
        
        return threeKindRank != 0 && pairRank != 0;
    }
    
    private boolean flush(ArrayList<Card> hand) {
        int suit = hand.get(0).getSuit();
        
        for (Card card : hand) {
            if (card.getSuit() != suit) {
                return false;
            }
        }
        
        return true;
    }
        
    private boolean straight(ArrayList<Card> hand) {
        if (aceHighStraight(hand) || regularStraight(hand)) {
            return true;
        }
        else {
            return false;
        }
        
    }
    
    private boolean aceHighStraight(ArrayList<Card> hand) {
        int[] aceHigh = {1, 10, 11, 12, 13};
        for (int i = 0; i < 5; i++) {
            if (hand.get(i).getRank() != aceHigh[i]) {
                return false;
            }
        }
        return true;
    }
    
    private boolean regularStraight(ArrayList<Card> hand) {
        for (int i = 1; i < 5; i++) {
            if (hand.get(i).getRank() != hand.get(i-1).getRank() + 1) {
                return false;
            }
        }
        return true;
    }
    
    // Returns rank of the three of a kind if not then 0
    private int threeKind(ArrayList<Card> hand) {
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (hand.get(i).getRank() == hand.get(j).getRank()) {
                    for (int k = j + 1; k < 5; k++) {
                        if (hand.get(k).getRank() == hand.get(j).getRank()) {
                            return hand.get(k).getRank();
                        }
                    }
                }
            }
        }
        
        return 0;
    }
    
    private boolean twoPair(ArrayList<Card> hand) {
        int pairOneRank = onePair(hand);  
        int pairTwoRank = 0;
        for (int i = 0; i < 5; i++) {
            if (hand.get(i).getRank() != pairOneRank) {
                for (int j = i + 1; j < 5; j++) {
                    if (hand.get(i).getRank() == hand.get(j).getRank()) {
                        pairTwoRank = hand.get(i).getRank();
                    }
                }
            } 
        }
        return (pairOneRank != 0 && pairTwoRank != 0);
    }

    // Returns rank of pair.  If no pair return 0
    private int onePair(ArrayList<Card> hand) {
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (hand.get(i).getRank() == hand.get(j).getRank()) {
                    return hand.get(i).getRank();
                }
            }
        }
        
        return 0;
    }
    

}
