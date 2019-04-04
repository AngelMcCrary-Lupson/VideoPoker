import java.io.*;
import java.util.*;

public class Deck{
    private ArrayList<String> cards = new ArrayList<>();
    private ArrayList<String> cardsCopy1 = new ArrayList<>();
    private ArrayList<String> cardsCopy2 = new ArrayList<>();
    private ArrayList<String> fillDeck = new ArrayList<>();
    private Random r = new Random();
    private int cut;
    
    // Constructs a new deck from the file containing playing cards
    public Deck()throws FileNotFoundException {
        Scanner reader = new Scanner(new File("new_card_deck.txt")); 
        while(reader.hasNextLine()){
            cards.add(reader.nextLine());
        }
    }
    
    // Prints the entire deck of cards
    public ArrayList printDeck(){
        return cards;
    }
    
    // Shuffles the deck by emulating the riffle shuffle. Cuts the deck into
    // almost equal halves (depends on number generated) and the halves add
    // to the deck in an alternating manner. Returns the shuffled deck.
    public ArrayList shuffleDeck(int shuffle){
        int timesShuffled = 0;
        while(timesShuffled < shuffle){
            cut = r.nextInt(cards.size() / 2 - 7) + 10;
            cardsCopy1 = new ArrayList(cards);
            cardsCopy2 = new ArrayList(cards);
            for(int i = cut; i < cards.size(); i++){
                cardsCopy1.remove(cut);
            }
            cardsCopy1.trimToSize();
            for(int j = 0; j < cut; j++){
                cardsCopy2.remove(0);
            }
            cardsCopy2.trimToSize();
            int min = Math.min(cardsCopy1.size(), cardsCopy2.size());
            if (min == cardsCopy1.size()){
                fillDeck = new ArrayList(cardsCopy1);
            } else {
                fillDeck = new ArrayList(cardsCopy2);
            }
            int copy1count = 0;
            int copy2count = 0;
            for(int k = 0; k < min * 2; k++){
                if(k % 2 != 0){
                    cards.set(k, cardsCopy1.get(copy1count));
                    copy1count++;
                } else {
                    cards.set(k, cardsCopy2.get(copy2count));
                    copy2count++;
                }
            }
            for (int h = min + 1; h < fillDeck.size() - 1; h++){
                cards.set(min, fillDeck.get(h));
            }
            timesShuffled++;
        }
        return cards;
    }
    
    // Takes the top the deck's card out of the deck and returns it as a string
    public String getCard(){
        String drawnCard = cards.get(0);
        cards.remove(0);
        cards.trimToSize();
        return drawnCard;
    }
    
    // Adds the card to the bottom (end) of the deck
    public void addCard(String cardAdded){
        cards.add(cardAdded);
    }
}
