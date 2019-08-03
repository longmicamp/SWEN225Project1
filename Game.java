import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Game {

    enum CharacterEnum implements Card{
        MrsWhite,MrGreen,MrsPeacock,ProfessorPlum,ColonelMustard,MissScarlett;
    }
    enum WeaponEnum implements Card{
        Candlestick,Dagger,LeadPipe,Revolver,Rope,Spanner;
    }
    enum RoomEnum implements Card{
        KITCHEN,HALL,STUDY,LOUNGE,CONSERVATORY,BILLIARDROOM,LIBRARY,DININGROOM;
    }





    ArrayList<Card> deck = new ArrayList<Card>();
    ArrayList<Player> players = new ArrayList<Player>();
    boolean gamewon = false;
    ArrayList<Card> Solution = new ArrayList<Card>();


    public void playClue(){

        Board board = new Board();
        board.makeBoard();

        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to Cluedo, please enter the number of players you would like to play with (between 3 and 6): ");
        int n = reader.nextInt();
        while (n<3 || n>6){
            System.out.println("You did not enter a number between 3 and 6, please try again:");
            n = reader.nextInt();
        }

        while (n-1>=0){
            Location loc = board.getPlayerPos(n-1);           //Put players on the board
            String name = CharacterEnum.values()[n-1].toString();
            Position pos  = board.getPos(loc);
            board.setPosName(loc,name);
            board.setOccupied(loc);


            Player play = new Player(loc, name, new ArrayList<Card>(), false ); // Creates new player
            players.add(play);


            n--;
        }


        board.printBoard();
        reader.close();

        MakeMurderer();
        DrawCards();



         


        }






        public void DrawCards() {

            for (int a = 0; a < players.size(); a++) {
                players.get(a).addCard();

            }

        }




    public void MakeMurderer() {

        int randomPlayer = (int) (Math.random() * CharacterEnum.values().length + 1) - 1;
        Card MurderCharacter = CharacterEnum.values()[randomPlayer];
        Solution.add(MurderCharacter);

        int randomWeapon = (int) (Math.random() * WeaponEnum.values().length + 1) - 1;
        Card MurderWeapon = WeaponEnum.values()[randomWeapon];
        Solution.add(MurderWeapon);

        int randomRoom = (int) (Math.random() * RoomEnum.values().length + 1) - 1;
        Card MurderRoom= RoomEnum.values()[randomWeapon];
        Solution.add(MurderRoom);


    }


    public void Makedeck() {

        deck.addAll(Arrays.asList(CharacterEnum.values()));
        deck.addAll(Arrays.asList(WeaponEnum.values()));
        deck.addAll(Arrays.asList(RoomEnum.values()));


    }







//    public void keyPressed(KeyEvent e) {
//
//        int key = e.getKeyCode();
//
//        if (key == KeyEvent.VK_LEFT) {
//            dx = -2;
//        }
//
//        if (key == KeyEvent.VK_RIGHT) {
//            dx = 2;
//        }
//
//        if (key == KeyEvent.VK_UP) {
//            dy = -2;
//        }
//
//        if (key == KeyEvent.VK_DOWN) {
//            dy = 2;
//        }
//    }


    public static void main (String Args[]){
        Game a = new Game();
        a.playClue();
    }
}
