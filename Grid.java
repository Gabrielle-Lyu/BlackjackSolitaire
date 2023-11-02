import java.util.ArrayList;
import java.util.Objects;

public class Grid {
    private final Card[][] grid;

    public Grid(){
//        Construct and Initialize the grid
        this.grid = new Card[4][5];
        int counter = 1;
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 5; j ++){
                grid[i][j] = new Card (Integer.toString(counter), "");
                counter++;
            }
        }
        for (int i = 2; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (j==0 || j == 4){
                    grid[i][j] = new Card("", "");
                } else {
                    grid[i][j] = new Card(Integer.toString(counter), "");
                    counter++;
                }
            }
        }
    }

//    Print out the grid
    public void printGrid() {
        System.out.println("Here is your grid: ");
        for (Card[] cards : grid) {
            StringBuilder thisLine = new StringBuilder();
            for (Card card : cards) {
                thisLine.append(card.toString());
                thisLine.append("       ");
            }
            System.out.println(thisLine);
        }
    }

//    Place Card at the user's input position
    public void placeCard(String userInput, Card card){
        Integer[] index = getIndex(userInput);
        grid[index[0]][index[1]] = card;
    }

//    Get the index of the user's input value
    public Integer[] getIndex(String userInput){
        Integer[] index = {-1,-1};
        try {
            Integer.parseInt(userInput);
            for(int i = 0; i < grid.length;i++){
                for (int j = 0; j < grid[i].length;j++){
                    if (grid[i][j].toString().equals(userInput)){
                        index[0] = i;
                        index[1] = j;
                        return index;
                    }
                }
            }
            return index;
        }catch(NumberFormatException ignored){ }
        return index;
    }

    public int calculateScore(){
        return calculateRowScore()+calculateColScore();
    }

    private int calculateRowScore(){
        int sum = 0;
        int totalRowScores = 0;
        int cntA = 0;
        ArrayList<Integer> rowScoresArray= new ArrayList<>();
        for (Card[] cards : grid) {
            for (Card card : cards) {
                sum += card.getValue();
                if (Objects.equals(card.getRank(), "A")) {
                    cntA += 1;
                }
            }
//            Deal with A issue. A can be both 1 and 11, so try to be as close as 21
            if (cntA > 0) {
                int sumRedo = sum - cntA;
                int diff = 21 - sumRedo;
                while (cntA > 0) {
                    if (diff >= 11) {
                        diff -= 11;
                        sumRedo += 11;
                        cntA--;
                    } else {
                        diff -= 1;
                        sumRedo += 1;
                        cntA--;
                    }
                }
                sum = sumRedo;
            }
//            Convert sum to score and add to the arraylist
            rowScoresArray.add(toScore(sum));
            sum = 0;
        }
//        sum up the scores in the arraylist
        for (Integer integer : rowScoresArray) {
            totalRowScores += integer;
        }
        return totalRowScores;
    }

    private int calculateColScore(){
        int sum = 0;
        int totalColScores = 0;
        int cntA = 0;
        ArrayList<Integer> colScoresArray = new ArrayList<>();
        for (int i = 0; i < grid[0].length; i++) {
            for (Card[] cards : grid) {
                sum += cards[i].getValue();
                if (Objects.equals(cards[i].getRank(), "A")) {
                    cntA += 1;
                }
            }
//            Deal with A issue. A can be both 1 and 11, so try to be as close as 21
            if(cntA > 0){
                int sumRedo = sum - cntA;
                int diff = 21 - sumRedo;
                while(cntA > 0){
                    if (diff >= 11){
                        diff -= 11;
                        sumRedo += 11;
                        cntA --;
                    }else{
                        diff -= 1;
                        sumRedo += 1;
                        cntA --;
                    }
                }
                sum = sumRedo;
            }
            colScoresArray.add(toScore(sum));
            sum = 0;
        }

//        Deal with Blackjack issue. If the 0 or 4th value in the array is 7,
//        it means it's the case of 2 cards add up to 21, so the score should be 10
        if (colScoresArray.get(0) == 7){
            colScoresArray.set(0,10);
        }
        if (colScoresArray.get(4) == 7){
            colScoresArray.set(4,10);
        }
//        Add up all the scores in the colScoresArray
        for (Integer integer : colScoresArray) {
            totalColScores += integer;
        }

        return totalColScores;
    }

//    base case of sum converting to score, ignore the blackjack case
    private int toScore(int sum){
        return switch (sum) {
            case 21 -> 7;
            case 20 -> 5;
            case 19 -> 4;
            case 18 -> 3;
            case 17 -> 2;
            default -> (sum <= 16) ? 1 : 0;
        };
    }

//    Check if game has ended (all 16 spots are filled with cards)
    public boolean gameHasEnd(){
        int numFilled = 0;
        for (Card[] cards : grid) {
            for (Card card : cards) {
                if (!Objects.equals(card.getSuit(), "")) {
                    numFilled++;
                }
            }
        }
        return numFilled == 16;
    }
}
