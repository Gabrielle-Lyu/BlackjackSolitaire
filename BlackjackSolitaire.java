import java.util.Arrays;
import java.util.Scanner;

public class BlackjackSolitaire {
    private final Discard discardDeck;
    private final Grid grid;
    private final Deck deck;
    public BlackjackSolitaire(){
        this.discardDeck = new Discard();
        this.grid = new Grid();
        this.deck = new Deck();
    }

    public void play(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("-----GAME START-----");
        System.out.println("You want to build a hand with a value as close to 21 as possible without exceeding 21");
        while(!grid.gameHasEnd()){
            Card currentCard = deck.drawCard();
            grid.printGrid();
            discardDeck.printDiscardDeck();
            System.out.println("You just drew a card of "+currentCard.toString());
            while(true){
                System.out.println("Select a spot (1-16) to place card, type 'd' to discard, or type 'q' to quit the game");
                String userInput = scnr.next();

//                When user wants to discard
                if (userInput.equals("d")){
                    if (!discardDeck.isDiscardFull()){
                        discardDeck.discardCard(currentCard);
                        discardDeck.printDiscardDeck();
                        break;
                    }
                    System.out.println("Discard deck is full. You must enter a spot to place the card.");
                }

//                When user enters 0-16
                else if (isInt(userInput) && Integer.parseInt(userInput) <= 16 && Integer.parseInt(userInput) > 0){
                    Integer[] negativeRes = {-1,-1};
                    if (!Arrays.equals(grid.getIndex(userInput),negativeRes)){
                        grid.placeCard(userInput,currentCard);
                        break;
                    }
                    System.out.println("This spot is already filled (max 4). Try another spot.");
                }

//                When user enters q to quit
                else if (userInput.equals("q")){
                    System.out.println("You're a quitter, bye");
                    System.out.println("-----GAME OVER-----");
                    return;
                }

//                When user enters something else
                else{
                    System.out.println("Invalid input. Try again.");
                }
            }

        }
        System.out.println("Thanks for playing! Your final board looks like:");
        grid.printGrid();
        System.out.println("Your final score is: " + grid.calculateScore());
        System.out.println("-----GAME OVER-----");
    }

//    Check if user input can be parseInt
    private boolean isInt(String str){
        if (str == null){
            return false;
        }
        try{
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
