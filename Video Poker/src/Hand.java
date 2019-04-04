import java.io.*;
import java.util.*;

public class Hand extends Deck{
    private ArrayList<String> hand;
    private Deck deck ;
    
    // Constructs a new hand from the deck of cards
    public Hand(Deck thisDeck) throws FileNotFoundException {
        hand = new ArrayList<>();
        deck = thisDeck;
    }
    
    // Draws a number of cards into the hand
    public ArrayList drawCard(int numCards){
        int cardsAdded = 0;
        while(cardsAdded < numCards){
            hand.add(deck.getCard());
            cardsAdded++;
        }
        return hand;
    }
    
    // Returns the card in hand's numerical value for later calculations
    public int getCardValue(int card, ArrayList<String> thisHand){
        String value = "" + thisHand.get(card).charAt(0);
        String value2 = "" + thisHand.get(card).charAt(1);
        int cardValue;
        if(value.equals("A")){
            cardValue = 14;
        } else if (value.equals("J")){
            cardValue = 11;
        } else if (value.equals("Q")){
            cardValue = 12;
        } else if (value.equals("K")){
            cardValue = 13;
        } else if (value.equals("1") && value2.equals("0")){
            cardValue = 10;
        }  else {
            cardValue = Integer.parseInt(value);
        }
        return cardValue;
    }
    
    // Returns the suit of the entered card
    public String getCardSuit(int card){
        int cardLength = hand.get(card).length();
        String suit = "" + hand.get(card).charAt(cardLength - 2);
        if(suit.equals("e")){
            return "Spades";
        } else if(suit.equals("d")){
            return "Diamonds";
        } else if(suit.equals("b")){
            return "Clubs";
        } else {
            return "Hearts";
        }
    }
    
    // Removes one card from the hand and places it into the deck
    public void discardCard(int handIndex){
        handIndex--;
        deck.addCard(hand.get(handIndex));
        hand.remove(handIndex);
        hand.trimToSize();
    }
    
    // Clears the player's hand, and places it into the deck
    public void clearHand(){
        for (int i = 4; i >= 0; i--){
            deck.addCard(hand.get(i));
            hand.remove(i);
            hand.trimToSize();
        }
    }
    
    // Prints the hand of cards
    public ArrayList printHand(){
        return hand;
    }
   
    // Sorts the hand by increasing card values
    public ArrayList sortHand(){
        ArrayList<String> sortedHand = new ArrayList(hand);
        String temp;
        for (int i = 0; i < sortedHand.size() - 1; i++){
            for (int j = i + 1; j < sortedHand.size(); j++){
                if(getCardValue(i, sortedHand) >= getCardValue(j, sortedHand)){
                    temp = sortedHand.get(i);
                    sortedHand.set(i, sortedHand.get(j));
                    sortedHand.set(j, temp);
                }
            }
        }
        return sortedHand;
    }
    
    // Returns as a string which poker hand the player currently has
    public String detectHand(){
        if (detectRoyalFlush()){
            return "Royal Flush";
        } else if (detectStraightFlush()){
            return "Straight Flush";
        } else if (detectFourKind()){
            return "Four of a Kind";
        } else if (detectFullHouse()){
            return "Full House";
        } else if (detectFlush()){
            return "Flush";
        } else if (detectStraight()){
            return "Straight";
        } else if (detectThreeKind()){
            return "Three of a Kind";
        } else if (detectPairs() == 2){
            return "Two Pairs";
        } else if (detectJackPairs()){
            return "Pair of Jacks";
        } else {
            return "High Card";
        }
    }
    
    // Returns true if the player's hand has a four of a kind
    public boolean detectFourKind(){
         for(int i = 0; i < hand.size(); i++){
            char firstCard = hand.get(i).charAt(0);
            for(int j = i + 1; j < hand.size(); j++){
                char secondCard = hand.get(j).charAt(0);
                for(int k = j + 1; k < hand.size(); k++){
                    char thirdCard = hand.get(k).charAt(0);
                    for(int l = k + 1; l < hand.size(); l++){
                        char fourthCard = hand.get(l).charAt(0);
                        if(firstCard == secondCard && firstCard == thirdCard && firstCard == fourthCard){
                        return true;
                    }
                }
            }
        } 
       }
       return false;
    }
    
    // Returns true if the player's hand has a royal flush
    public boolean detectRoyalFlush(){
        return (getCardValue(0, sortHand()) == 10 && detectStraightFlush());
    }
    
    // Returns true if the player's hand has a straight flush
    public boolean detectStraightFlush(){
        return detectStraight() && detectFlush();
    }
    
    // Returns true if the player's hand has a full house
    public boolean detectFullHouse(){
        return (detectPairs() == 1 && detectThreeKind());
    }
    
    // Returns true if the player's hand has a flush
    public boolean detectFlush(){
        int cardsSameSuit = 0;
        for (int i = 0; i < hand.size() - 1; i++){
            if(getCardSuit(i).equals(getCardSuit(i + 1))){
                cardsSameSuit++;
            }
        }
        if (cardsSameSuit == 4){
            return true;
        }
        return false;
    }
    
    // Returns true if the hand has a pair of cards
    public int detectPairs(){
        int pairs = 0;
        for(int i = 0; i < hand.size(); i++){
            char firstCard = hand.get(i).charAt(0);
            for(int j = i + 1; j < hand.size(); j++){
                char secondCard = hand.get(j).charAt(0);
                if (firstCard == secondCard){
                    pairs++;
                }
            }
        }
        return pairs;
    }
    
    // Returns true if the player's hand has a pair of jacks
    public boolean detectJackPairs(){
        for(int i = 0; i < hand.size(); i++){
            char firstCard = hand.get(i).charAt(0);
            for(int j = i + 1; j < hand.size(); j++){
                char secondCard = hand.get(j).charAt(0);
                if (firstCard == secondCard && firstCard == ('J')){
                    return true;
                }
            }
        }
        return false;
    }
    
    // Returns true if the hand has a three of a kind
    public boolean detectThreeKind(){
        for(int i = 0; i < hand.size(); i++){
            char firstCard = hand.get(i).charAt(0);
            for(int j = i + 1; j < hand.size(); j++){
                char secondCard = hand.get(j).charAt(0);
                for(int k = j + 1; k < hand.size(); k++){
                    char thirdCard = hand.get(k).charAt(0);
                    if(firstCard == secondCard && firstCard == thirdCard){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    // Returns true if the hand has a straight
    public boolean detectStraight(){
        ArrayList<String> sortedHand = sortHand();
        
        int firstCard = getCardValue(0, sortedHand);
        if (getCardValue(4, sortedHand) == 14 && firstCard == 2){
            int difference = 1;
            int j = 0;
            do {
                int otherCard = getCardValue(j, sortedHand);
                int comparedCard = getCardValue(j + 1, sortedHand);
                difference = comparedCard - otherCard;
                j++;
            } while (difference == 1);
            return j == 4;
        } else {
            int difference = 1;
            int j = 0;
            do {
                int previousCard = getCardValue(j, sortedHand);
                int comparedCard = getCardValue(j + 1, sortedHand);
                difference = comparedCard - previousCard;
                j++;
            } while (difference == 1 && j < 4);
            
            return (j == 4 && getCardValue(4, sortedHand) - getCardValue(3, sortedHand) == 1);  
        }  
    }  
}
