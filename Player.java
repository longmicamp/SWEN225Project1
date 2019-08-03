import java.util.ArrayList;

public class Player implements Piece {

    private Location loc;
    private String name;

    // the cards in the players hand
    private ArrayList<Card> hand;

    // Keeps track of whether the player has made a suggestion or not
    private boolean suggestion;


    public Player(Location loc, String name, ArrayList<Card> hand, boolean suggestion) {
        this.loc = loc;
        this.name = name;
        this.hand = hand;
        this.suggestion = suggestion;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void addCard(Card card){this.hand.add(card);}


    public boolean isSuggestion() {
        return suggestion;
    }

    public void setSuggestion(boolean suggestion) {
        this.suggestion = suggestion;
    }

    public void printHand(){

        for(int a = 0; a< hand.size();a++){
            System.out.println(hand.get(a));
        }
    }


    @Override
    public Location getloc() {
        return loc;
    }

    @Override
    public void setLoc(Location loc) {

        this.loc = loc;
    }
}
