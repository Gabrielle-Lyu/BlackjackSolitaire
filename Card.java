public class Card {
    private final String suit;
    private final String rank;
    public Card(String rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue(){
        try {
            int rankValue = Integer.parseInt(rank);
            if (rankValue <= 10 && rankValue >= 2) {
                return rankValue;
            }
        }catch(NumberFormatException ignored){ }

        if (rank.equals("J") | rank.equals("Q") | rank.equals("K")){
            return 10;
        }
        if (rank.equals("A")){
            return 1;
        }
        return 0;
    }

    public String toString(){
        return  rank+suit;
    }

    public String getSuit(){
        return suit;
    }

    public String getRank(){
        return rank;
    }
}


