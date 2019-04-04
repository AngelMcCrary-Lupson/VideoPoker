
public class PlayerAccount {
    private int accountBalance;
    private String hand;
    public static final int DEFAULT_BALANCE = 250;
    
    // Constructs a player account with the default balance
    public PlayerAccount(){
        accountBalance = DEFAULT_BALANCE;
    }
    
    // Takes in the poker hand the player currently has
    public void recieveHand(String detectedHand){
        hand = detectedHand;
    }
    
    // Returns the appropriate payout multiplyer based off of the credits bet
    // and the hand currently held.
    public int multiplyer(int bet){
        int multiplyer = 1;
        int payout = 0;
        switch(hand){
            case "Pair of Jacks" : 
                multiplyer = 1;
                payout = multiplyer * bet;
                break;
            case "Two Pairs" :
                multiplyer = 2;
                payout = multiplyer * bet;
                break;
            case "Three of a Kind" :
                multiplyer = 3;
                payout = multiplyer * bet;
                break;
            case "Straight" :
                multiplyer = 4;
                payout = multiplyer * bet;
                break;
            case "Flush" :
                multiplyer = 6;
                payout = multiplyer * bet;
                break;
            case "Full House" :
                multiplyer = 9;
                payout = multiplyer * bet;
                break;
            case "Four of a Kind" :
                multiplyer = 25;
                payout = multiplyer * bet;
                break;
            case "Straight Flush" :
                multiplyer = 50;
                payout = multiplyer * bet;
                break;
            case "Royal Flush" :
                if (bet == 5){
                   multiplyer = 4000; 
                   break;
                } else {
                   multiplyer = 250;
                   payout = multiplyer * bet;
                   break;
                }
        }
        return payout;
    }
    
    // Returns the player's credit balance
    public int getBalance(){
        return accountBalance;
    }
    
    // Adds to the player's credit balance
    public void addBalance(int credit){
        accountBalance += credit;
    }
    
    // Subtracts from the player's credit balance
    public void subtractBalance(int credit){
        accountBalance -= credit;
    }   
}
