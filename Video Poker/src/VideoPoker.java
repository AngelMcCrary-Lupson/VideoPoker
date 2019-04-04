import java.io.*;
import java.util.*;

public class VideoPoker {
    // Fix allowing to bet 0 coins
    // Fix reshuffling when running out of cards in the deck
    
    public static void main(String[] args) throws FileNotFoundException{       
        System.out.println("Welcome to the game!");
        System.out.println("I'll be your virtual dealer today.");
        System.out.println("Let's play some video poker.");
        System.out.println("We'll be playing Jacks or Better.");
        System.out.println();
        System.out.println("Shuffling deck....");
        System.out.println();
        
        PlayerAccount account = new PlayerAccount();
        Deck newDeck = new Deck();
        newDeck.shuffleDeck(3);
        Hand newHand = new Hand(newDeck);
        Scanner input = new Scanner(System.in);
        
        boolean isPlaying = true;
        int timesPlayed = 1;
        do {
            boolean isBetting = true;
            int bet = 0;
            while(isBetting){
                System.out.println("Your balance is " + account.getBalance());
                System.out.print("How many credits would you like to bet? (1-5) ");
                bet = input.nextInt();
                if (account.getBalance() - bet < 0){
                    System.out.println("You don't have enough credits, please try again.");
                } else if (bet > 5){
                    System.out.println("You cannot bet that amount, please try again.");
                } else {
                    account.subtractBalance(bet);
                    isBetting = false;
                }
            }
            if (timesPlayed > 1){
                newHand.clearHand();
            }
            
            System.out.println("Here's your cards.");
            System.out.println(newHand.drawCard(5));
            String detectedHand;
            detectedHand = newHand.detectHand();
            System.out.println("Looks like you've got a " + detectedHand);

            Scanner console = new Scanner(System.in);
            boolean handFaze = true;
            int cardsDiscarded = 0;
            do {
              System.out.print("Would you like to discard any of your cards? Y/N ");
              boolean userResponse = discardCards(console);  
              if (userResponse) {
                System.out.print("Which card? ");
                newHand.discardCard(console.nextInt());
                cardsDiscarded++;
                System.out.println("Here's your hand now.");
                System.out.println(newHand.printHand());
                discardCards(console);
              } else {
                  handFaze = false;
              }
            } while(handFaze);
            System.out.println();
            if (cardsDiscarded > 0){
                System.out.println("I'll deal you your remaining cards now.");
                System.out.println(newHand.drawCard(cardsDiscarded));
                detectedHand = newHand.detectHand();
                System.out.println("Looks like you've got a " + detectedHand); 
                            System.out.println();
            }
            
            account.recieveHand(detectedHand);      
            int payout = account.multiplyer(bet);
            System.out.println("You won " + payout + " credits!");
            account.addBalance(payout);
            System.out.println("Your balance is " + account.getBalance());
            
            System.out.println();
            System.out.print("Would you like to play again? Y/N ");
            String userAnswer = console.nextLine();
            if (!userAnswer.equals("Y")){
                isPlaying = false;
            } else {
                timesPlayed++;
            }
            System.out.println();
        } while (isPlaying); 
    }
    
    public static boolean discardCards(Scanner console){
        String userResponse = console.nextLine(); 
        return userResponse.equals("Y");
    }  
}
