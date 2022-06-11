# CLI Poker

This is a poker game for the command line written in Java.  Below is extensive documentation for every single class and method.

Card.java

    compareTo() - if the ranks are equal, it checks the suits and returns accordingly
                    otherwise, it returns 1 or -1 accordingly.  The reason it uses the long return statement instead of the simpler if/else like the suit check is because i needed to get to 15 lines for the method length:)
    
    toString() - returns a pretty string that represents the card

    getRank() - returns the rank of the card as an int
    
    getSuit() - returns the suit of the card as an int
    
    rankToString() - helper method for toString() that handles ranks
    
    suitToString() - helper method for toString() that handles suits

Deck.java

    Constructor - sets top to -1 so when the first card is dealt it is the first in the array
                    adds 52 unique cards to the cards array using a for loop and some modulos
    
    shuffle() - swaps two random cards with each other 1000 times.  Use math.random to choose 2 indices, store in temp array, and swapperoo
    
    deal() - increments top if less than 51, otherwise sets to 0.  Returns the card at top index
    
    getCards() - getter method for the cards array

Player.java

    Constructor - initializes an arraylist of cards that is the players hand and sets bankroll to 100
    
    addCard() - adds the explicit parameter to the hand arraylist
    
    removeCard() - removes the card object specified in explicit paaram from hand arraylist
    
    bets() - subtracts credits from bankroll represetning the player making a bets
    
    winnings() - adds credits to bankroll based on the players winnings
    
    getBankroll() returns the bankroll amount
    
    getBet() returns the bet the player made
    
    setBet() sets the player bet instance variable
    
    getHand() returns the player's hand
    
    resetHand() sets the player hand to an empty arraylist.  This is used if the player wants to play multiple games
    
Game.java

    If you are reading this I apologize for how long it is.  Good luck and thanks for making this class so fun!
    
    Instance variables:
    
        p - the player of the game
        
        cards - the deck of cards used in the game
        
        scan - a scanner object used ot get input from the player
        
        payout - represents the credits the player should receive for their hand if they bet 1 credit
        
        playAgain - boolean that represents the player's desire to play again after the completion of a game
        
    The methods are organized in groups based on use.  For example, all of play()'s helper methods directly follow play().
    
    Constructor stuff:
    
        Game( the one with array of strings) - initializes objects for the instance variables, sets playAgain to true, converts the String array to card objects and adds to hand

        cmdCardConvert() - converts strings to card objects.  EX "s1" becomes a card object ace of spades

        Game() - does everything the other Game constructor does except it does not handle explicit parameters

    Play method stuff:

        play() - while playAgain is true, runs the game loop.  This is why playAgain is set to true in the constructors
                    Prompt the player for a bet, shuffle deck, deal cards, show cards, prompt player to drop cards, redeal cards, show cards, award tokens and prompt to play again

        listCards() - lists the cards in p's hand

        dealCards() - adds cards to p's hand until it has 5 cards

        playerBet() - tells the player their balance, asks them how much they want to gamble away, updates their balance accordingly

        playerChangeCards() - prompts the user to drop cards if they want.  1-5 will remove the card at that position and 0 will proceed.
                                this occurs while the player has cards in their hand and they have not decided to move on

        checkPayPlayAgain() - checks the player's hand, awards winnings, prompts them to play again and resets the hand if necessary

    Methods for checking the hand:

        checkHand() one through four are all basically one method.  I just needed to stay under the 15 line limit.
                    it checks the hand for every type starting from top to bottom (royal flush to high one pari).  It adjusts payout and returns the name of the hand.
                    
        royalFlush() - returns true if the hand is a flush and is a straight with an ace as the high card
        
        fourKind() - iterates through all five possible combinations of 4 cards out of the five.  
                        If a combination is not four of a kind it decrements the fourKind int.
                        If the hand is not a four of a kind, the fourKind int will be decremented 5 times to -5.
                        THerefore if fourKind is greater than -5 one of the combinations was a foud of a kind
                        
        fullHouse() - calls the threeKind() method and stores the rank of the three of a kind
                        it then searches for a pair in the hand that does not have the same rank as the three of a kind
                        if it finds a pair it stores the pairs rank.  If both the rank variables are not 0, the method found both, so it is a full house.
                        
        flush() - if any card in the hand does not have the same suit as the first card in the hand, it is not a flush
        
        straight() - this is broken into two sub methods.  One that checks for an ace high straight and one that checks for all other types
        
        aceHighStraight() - because of the nature of card.compareTo(), the order of a hand with this straight should be 1, 10, 11, 12 ,13
                                an array is hardcoded in with the same numbers.  I iterate over the hand to verify that the cards ranks match the hardcoded array
        
        regularStraight() - each card should have a rank one higher than the card before it in the hand.  If so, return true;
        
        threeKind() - returns the rank of the three of a kind.  This is to help out with the aforementioned fullHouse() method.
                        Iterates through each possible pair of cards.  If a pair is identified, it searches the remaining cards to a third of its kind.
                        If this is found returns the rank of the cards.
                        
        twoPair() - similar to fullHouse method.  Uses onePair() to find a pair, stores the pairs rank, and looks for another pair that does not have the same rank.
        
        onePair() - iterates through all possible pairs of cards.  If the pair has the same rank returns the rank of that pair.
        
        I forgot to add that onePair() and threeKind() return 0 if they don't find their respective hands.
