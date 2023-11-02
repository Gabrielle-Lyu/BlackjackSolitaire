import java.util.ArrayList;

public class Discard {
    private final ArrayList<Card> discardDeck;
    public Discard(){
        this.discardDeck = new ArrayList<>();
    }

    public void discardCard(Card card){
        discardDeck.add(card);
    }

    public void printDiscardDeck(){
        System.out.println("Your discard deck is: " + discardDeck + "; " + (4-discardDeck.size()) + " discard slots remaining.");
    }

    public boolean isDiscardFull(){
        return discardDeck.size() >= 4;
    }
}
