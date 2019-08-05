    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    import java.lang.reflect.Array;
    import java.util.ArrayList;
    import java.util.Scanner;
    import java.util.*;

    public class Game {

        enum CharacterEnum implements Card {
            MrsWhite, MrGreen, MrsPeacock, ProfessorPlum, ColonelMustard, MissScarlett;
        }

        enum WeaponEnum implements Card {
            Candlestick, Dagger, LeadPipe, Revolver, Rope, Spanner;
        }

        enum RoomEnum implements Card {
            KITCHEN, HALL, STUDY, LOUNGE, CONSERVATORY, BILLIARDROOM, LIBRARY, DININGROOM,BALLROOM;
        }


        ArrayList<Card> deck = new ArrayList<Card>();
        ArrayList<Player> players = new ArrayList<Player>();
        boolean gamewon = false;
        ArrayList<Card> Solution = new ArrayList<Card>();
        Board board = new Board();


        public void playClue() {




            board.makeBoard();

    //        Scanner reader = new Scanner(System.in);
    //        System.out.println("Welcome to Cluedo, please enter the number of players you would like to play with (between 3 and 6): ");
    //        int n = reader.nextInt();
    //        while (n<3 || n>6){
    //            System.out.println("You did not enter a number between 3 and 6, please try again:");
    //            n = reader.nextInt();
    //        }
            int m = 4;
            while (m - 1 >= 0) {
                Location loc = board.getPlayerPos(m - 1);           //Put players on the board
                String name = CharacterEnum.values()[m - 1].toString();
                Position pos = board.getPos(loc);
                board.setPosName(loc, name);
                board.setOccupied(loc);


                Player play = new Player(loc, name, new ArrayList<Card>(), false); // Creates new player
                players.add(play);


                //  n--;
                m--;
            }

            board.printBoard();
            //reader.close();

            MakeMurderer();
            Makedeck();
            DrawCards();
          //  players.get(1).setmovesLeft(3);
            makeAccusation(players.get(1));

    //        while(gamewon == false) {
    //            for (int a = 0; a < players.size(); a++) {
    //                PlayersTurns(players.get(a));
    //
    //            }
    //        }

        }


        public void PlayersTurns(Player player) {

            JFrame frame = new JFrame("Key Listener");

            Container contentPane = frame.getContentPane();

            KeyListener listener = new KeyListener() {


                @Override
                public void keyPressed(KeyEvent event) {


                }

                @Override
                public void keyReleased(KeyEvent event) {


                    int key = event.getKeyCode();

                    Location loc = player.getloc();
                    Location newloc = null;

                    if (key == 37) {
                        int x = loc.getX() - 1;
                        newloc = new Location(x, loc.getY());
                    } else if (key == 39) {
                        int x = loc.getX() + 1;
                        newloc = new Location(x, loc.getY());
                    } else if (key == 40) {
                        int x = loc.getY() + 1;
                        newloc = new Location(loc.getX(), x);
                    } else if (key == 38) {
                        int x = loc.getY() - 1;
                        newloc = new Location(loc.getX(), x);
                    }
                    if (canMove(player, newloc)) {

                        board.setPosName(loc, "");
                        board.setNotoccupied(loc);
                        board.setPosName(newloc, player.getName());
                        board.setOccupied(newloc);
                        player.setLoc(newloc);
                        board.printBoard();

                    }


                }



                @Override
                public void keyTyped(KeyEvent event) {

                }
            };


            JTextField textField = new JTextField();
            textField.addKeyListener(listener);
            contentPane.add(textField, BorderLayout.NORTH);
            frame.pack();
            frame.setVisible(true);


        }

        public void makeSuggestion(Player player){
            Location loc = player.getloc();
            Location newloc =new Location(23,3);
            board.setPosName(loc, "");
            board.setNotoccupied(loc);
            board.setPosName(newloc, player.getName());
            board.setOccupied(newloc);
            player.setLoc(newloc);
            board.printBoard();


            Scanner reader = new Scanner(System.in);

            for(int a = 0; a<WeaponEnum.values().length;a++){
                System.out.println(a+": "+WeaponEnum.values()[a].toString());
            }
            System.out.println("What Weapon do you think the Murder used? Select the number corresponding to weapon on screen");
            int n = reader.nextInt();
            while(n > WeaponEnum.values().length-1 || n<0){
                System.out.println("You have entered an invalid number, please try again: ");
                n = reader.nextInt();
            }
            Card Weapon = WeaponEnum.values()[n];

            System.out.println("Who do you think did the murder? please select from the people on the screen");
            for(int a = 0; a<WeaponEnum.values().length;a++){
                System.out.println(a+": "+CharacterEnum.values()[a].toString());
            }
            int m = reader.nextInt();

            while(m > CharacterEnum.values().length-1 || m<0){
                System.out.println("You have entered an invalid number, please try again: ");
                m = reader.nextInt();
            }
            Card Player = CharacterEnum.values()[m];
            Card Room = null;

            String currentRoom = board.getPos(player.getloc()).getRoomName();
            for(int b = 0;b<RoomEnum.values().length;b++){
                if(RoomEnum.values()[b].toString().equals(currentRoom)){
                    Room = RoomEnum.values()[b];
                }
            }

            System.out.println(Player.toString()+"  "+Room.toString()+ "   "+Weapon.toString());

            for(Player a: players){

                System.out.println(a.getName());
                a.printHand();
                System.out.println("");

                if(a.getHand().contains(Player)){
                    System.out.println(a.getName() + " has card " + Player.toString());
                }
                if(a.getHand().contains(Room)){
                    System.out.println(a.getName() + " has card " + Room.toString());
                }
                if(a.getHand().contains(Weapon)){
                    System.out.println(a.getName() + " has card " + Weapon.toString());
                }
            }

        }

        public void makeAccusation(Player player){

            Location loc = player.getloc();
            Location newloc =new Location(23,3);
            board.setPosName(loc, "");
            board.setNotoccupied(loc);
            board.setPosName(newloc, player.getName());
            board.setOccupied(newloc);
            player.setLoc(newloc);
            board.printBoard();

            Scanner reader = new Scanner(System.in);

            for(int a = 0; a<WeaponEnum.values().length;a++){
                System.out.println(a+": "+WeaponEnum.values()[a].toString());
            }
            System.out.println("What Weapon do you think the Murder used? Select the number corresponding to weapon on screen");
            int n = reader.nextInt();
            while(n > WeaponEnum.values().length-1 || n<0){
                System.out.println("You have entered an invalid number, please try again: ");
                n = reader.nextInt();
            }
            Card Weapon = WeaponEnum.values()[n];

            System.out.println("Who do you think did the murder? please select from the people on the screen");
            for(int a = 0; a<WeaponEnum.values().length;a++){
                System.out.println(a+": "+CharacterEnum.values()[a].toString());
            }
            int m = reader.nextInt();

            while(m > CharacterEnum.values().length-1 || m<0){
                System.out.println("You have entered an invalid number, please try again: ");
                m = reader.nextInt();
            }
            Card Player = CharacterEnum.values()[m];
            Card Room = null;

            String currentRoom = board.getPos(player.getloc()).getRoomName();
            for(int b = 0;b<RoomEnum.values().length;b++){
                if(RoomEnum.values()[b].toString().equals(currentRoom)){
                    Room = RoomEnum.values()[b];
                }
            }

            System.out.println(Player.toString()+"  "+Room.toString()+ "   "+Weapon.toString());




                if(Solution.contains(Player)&& Solution.contains(Room)&&Solution.contains(Weapon)){
                    System.out.println("You Win : "+player.getName());

            }
                else{
                    System.out.println("God, this is lame, but you are incorrect, you are no longer in the game: "+ player.getName());

                    board.setNotoccupied(player.getloc());
                    board.setPosName(player.getloc(), "");
                    board.printBoard();
                }

        }





        public boolean canMove(Player player, Location loc) {
            Location loc1 = player.getloc();

            if(board.getPos(loc).getType().equals("/")) return false;


            if(board.getPos(loc1).getType().equals("O")||board.getPos(loc).getType().equals("O")) return true;

          if(loc.getX() == 24 ||loc.getX()==-1||loc.getY()==25||loc.getY()==-1){
              return false;
          }

            if ((board.getPos(loc).getType().equals(board.getPos(loc1).getType()))&&(!board.getPos(loc).isOccupied())) {
                return true;
            }
            return false;
        }


        public void DrawCards() {

            for (int b = 0; b < Solution.size(); b++) {
                deck.remove(Solution.get(b));
            }

            int player = 0;
            while (deck.size() > 0) {
                int random = (int) (Math.random() * deck.size() + 1) - 1;
                players.get(player).addCard(deck.get(random));
                deck.remove(random);
                if (player == players.size() - 1) {
                    player = 0;
                } else {
                    player++;
                }


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
            Card MurderRoom = RoomEnum.values()[randomRoom];
            Solution.add(MurderRoom);


        }


        public void Makedeck() {

            deck.addAll(Arrays.asList(CharacterEnum.values()));
            deck.addAll(Arrays.asList(WeaponEnum.values()));
            deck.addAll(Arrays.asList(RoomEnum.values()));


        }


        public static void main(String Args[]) {
            Game a = new Game();
            a.playClue();
        }
    }

