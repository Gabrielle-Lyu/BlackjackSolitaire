import java.util.Collections;
import java.util.Stack;

public class Deck {
    private final Stack<Card> cardStack;
    public Deck(){
//        Create a deck of cards and assigned the 52 values to have the combination of suits and rank
        this.cardStack = new Stack<>();
        String[] suits = {"C","D","H","S"};
        String[] rank = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        for (String s : suits){
            for (String r : rank){
                cardStack.push(new Card(r,s));
            }
        }
        Collections.shuffle(cardStack);
    }

    public Card drawCard(){
        return cardStack.pop();
    }

    public String toString(){
        return cardStack.toString();
    }
}
