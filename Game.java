    import com.sun.corba.se.impl.ior.iiop.MaxStreamFormatVersionComponentImpl;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    import java.lang.reflect.Array;
    import java.sql.SQLOutput;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    import java.util.*;
    import java.util.stream.Collectors;
    import java.util.stream.Stream;

    public class Game{


        enum CharacterEnum implements Card {
            MrsWhite, MrGreen, MrsPeacock, ProfessorPlum, ColonelMustard, MissScarlett;
        }

        enum WeaponEnum implements Card {
            Candlestick, Dagger, LeadPipe, Revolver, Rope, Spanner;
        }

        enum RoomEnum implements Card {
            KITCHEN, HALL, STUDY, LOUNGE, CONSERVATORY, BILLIARDROOM, LIBRARY, DININGROOM,BALLROOM;
        }

        public List<Enum> roomList = Arrays.asList(RoomEnum.values());
        List<String> roomNames = Stream.of(RoomEnum.values()).map(Enum::name).collect(Collectors.toList());

        public List<Enum> weaponList = Arrays.asList(WeaponEnum.values());
        List<String> weaponNames = Stream.of(WeaponEnum.values()).map(Enum::name).collect(Collectors.toList());

        public ArrayList<Room> rooms = new ArrayList<Room>();


        ArrayList<Card> deck = new ArrayList<Card>();
        ArrayList<Player> players = new ArrayList<Player>();
        Player winningplayer = null;
        boolean gamewon = false;
        ArrayList<Card> Solution = new ArrayList<Card>();
        Board board = new Board();

        //randomly put weapons in rooms at beginning of game
        public void roomWeapon() {
            for (int a = 0; a < roomNames.size(); a++) {
                if (roomNames.get(a) != "HALL") {
                    Room room = new Room(roomNames.get(a));
                    rooms.add(room);
                }
            }
            for (int a = 0; a < weaponNames.size(); a++) {
                rooms.get(a).setWeapon(weaponNames.get(a));
            }

        }

        public void printTitle(){
            System.out.println("WELCOME TO ");
            System.out.println(" ▄████▄      ██▓        █    ██    ▓█████    ▓█████▄     ▒█████  ");
            System.out.println("▒██▀ ▀█     ▓██▒        ██  ▓██▒   ▓█   ▀    ▒██▀ ██▌   ▒██▒  ██▒");
            System.out.println("▒▓█    ▄    ▒██░       ▓██  ▒██░   ▒███      ░██   █▌   ▒██░  ██▒");
            System.out.println("▒▓▓▄ ▄██▒   ▒██░       ▓▓█  ░██░   ▒▓█  ▄    ░▓█▄   ▌   ▒██   ██░");
            System.out.println("▒ ▓███▀ ░   ░██████▒   ▒▒█████▓    ░▒████▒   ░▒████▓    ░ ████▓▒░");
            System.out.println("░ ░▒ ▒  ░   ░ ▒░▓  ░   ░▒▓▒ ▒ ▒    ░░ ▒░ ░    ▒▒▓  ▒    ░ ▒░▒░▒░ ");
            System.out.println("  ░  ▒      ░ ░ ▒  ░   ░░▒░ ░ ░     ░ ░  ░    ░ ▒  ▒      ░ ▒ ▒░ ");
            System.out.println("░             ░ ░       ░░░ ░ ░       ░       ░ ░  ░    ░ ░ ░ ▒  ");
            System.out.println("░                                             ░                  ");
            System.out.println("BY CAMPBELL 'LILNUT' LONGMIRE & NIKHIL 'BIGNUT' REDDY \n");
        }



        public void playClue() {



            printTitle();
            board.makeBoard();

            Scanner reader = new Scanner(System.in);
            System.out.println("Welcome to Cluedo, please enter the number of players you would like to play with (between 3 and 6): ");
            int n = reader.nextInt();
            while (n<3 || n>6){
                System.out.println("You did not enter a number between 3 and 6, please try again:");
                n = reader.nextInt();
            }

            while (n - 1 >= 0) {
                Location loc = board.getPlayerPos(n);         //Put players on the board
                String name = CharacterEnum.values()[n - 1].toString();
                Position pos = board.getPos(loc);
                board.setPosName(loc, name);
                board.setOccupied(loc);


                Player play = new Player(loc, name, new ArrayList<Card>(), false); // Creates new player
                players.add(play);


                n--;

            }

            board.printBoard();
            roomWeapon();


            MakeMurderer();
            Makedeck();
            DrawCards();

            while(gamewon == false) {
                for (Player player : players) {
                    if(player.isMadeaccusation() == false) {
                        printWeapons();
                        PlayersTurns(player);
                    }
                }
            }
        System.out.println("Congrats! "+ winningplayer+" has won the game, you the champ!");

        }

        public void printWeapons(){

            System.out.println("----------------------------------");
            System.out.printf("%-15S %-25S ","   | ROOM |", "   | WEAPON |");
            System.out.println();
            System.out.println("----------------------------------");
            for(int a = 0; a<rooms.size();a++) {
                if(rooms.get(a).getWeapon()!= null) {
                    System.out.printf("%-15S %-25S \n", "   " +rooms.get(a).getRoom(),"   " +rooms.get(a).getWeapon());

                }

                else {
                    System.out.printf("%-15S %-25S \n", "   " +rooms.get(a).getRoom(),"   Currently Empty");
                }
            }
            System.out.println("----------------------------------");
        }


        public void PlayersTurns(Player player) {

            int roledice = (int) (Math.random() * 10) + 2;


            while (roledice > 0) {

                System.out.println("You have "+roledice+ " moves left "+ player);
                Location loc = player.getloc();
                Location newloc = null;
                Scanner reader = new Scanner(System.in);
                System.out.println("What direction would you like to move? select from w,a,s,d then click enter ");
                String n = reader.next();
                while(!n.equals("a")&&!n.equals("w")&&!n.equals("s")&&!n.equals("d")){
                    System.out.println("That is not a valid input, please try again...");
                    n = reader.next();
                }

                if (n.equals("a")) {
                    System.out.println("Left");
                    int x = loc.getX() - 1;
                    newloc = new Location(x, loc.getY());
                } else if (n.equals("d")) {
                    System.out.println("Right");
                    int x = loc.getX() + 1;
                    newloc = new Location(x, loc.getY());
                } else if (n.equals("s")) {
                    System.out.println("Down");
                    int x = loc.getY() + 1;
                    newloc = new Location(loc.getX(), x);
                } else if (n.equals("w")) {
                    System.out.println("Up");
                    int x = loc.getY() - 1;
                    newloc = new Location(loc.getX(), x);
                }
                System.out.println(canMove(player,newloc));
                if (canMove(player, newloc) ) {


                    board.setPosName(loc, "");
                    board.setNotoccupied(loc);
                    board.setPosName(newloc, player.getName());
                    board.setOccupied(newloc);
                    player.setLoc(newloc);
                    board.printBoard();
                    if(board.getPos(player.getloc()).getRoomName()!=null){
                        System.out.println("You are in a room, would you like to make a suggestion of accusation? y/n");
                        String m = reader.next();
                        while (!m.equals("y") && !m.equals("n")){
                            System.out.println("Please select a valid input. y or n");
                            m = reader.next();
                        }
                        if(m.equals("y")){
                            System.out.println("Select 'a' for accusation, 's' for suggestion, or 'c' for cancel:");
                             String z = reader.next();
                            while (!z.equals("c") && !z.equals("a") && !z.equals("s")) {
                                System.out.println("Please select a valid input. a or s or a");
                                z = reader.next();
                            }
                            if(z.equals("c")){
                                System.out.println("You canceled your suggestion and accusation, continuing game...");
                            }
                            else if(z.equals("a")){
                                makeAccusation(player);
                                if(player.isMadeaccusation() == true){
                                    roledice = 0;
                                    break;
                                }
                                else if(gamewon){
                                    break;
                                }
                            }
                            else if(z.equals("s")){
                                makeSuggestion(player);
                            }

                        }
                        else if(m.equals("n")){
                            System.out.println("You have decided not to make an accusation or suggestion, you may continue");
                        }
                    }




                }

                roledice--;
            }


        }




        public void makeSuggestion(Player player) {
            Location loc = player.getloc();
            Location newloc = new Location(23, 3);
            board.setPosName(loc, "");
            board.setNotoccupied(loc);
            board.setPosName(newloc, player.getName());
            board.setOccupied(newloc);
            player.setLoc(newloc);
            board.printBoard();


            Scanner reader = new Scanner(System.in);

            for (int a = 0; a < WeaponEnum.values().length; a++) {
                System.out.println(a + ": " + WeaponEnum.values()[a].toString());
            }
            System.out.println("What Weapon do you think the Murder used? Select the number corresponding to weapon on screen");
            int n = reader.nextInt();
            while (n > WeaponEnum.values().length - 1 || n < 0) {
                System.out.println("You have entered an invalid number, please try again: ");
                n = reader.nextInt();
            }
            Card Weapon = WeaponEnum.values()[n];


            System.out.println("Who do you think did the murder? please select from the people on the screen");
            for (int a = 0; a < WeaponEnum.values().length; a++) {
                System.out.println(a + ": " + CharacterEnum.values()[a].toString());
            }
            int m = reader.nextInt();

            while (m > CharacterEnum.values().length - 1 || m < 0) {
                System.out.println("You have entered an invalid number, please try again: ");
                m = reader.nextInt();
            }
            Card Player = CharacterEnum.values()[m];
            Card Room = null;

            String currentRoom = board.getPos(player.getloc()).getRoomName();
            for (int b = 0; b < RoomEnum.values().length; b++) {
                if (RoomEnum.values()[b].toString().equals(currentRoom)) {
                    Room = RoomEnum.values()[b];
                }
               
            }

            System.out.println("You selected these three things as your suggestion "+Player.toString() + "  " + Room.toString() + "   " + Weapon.toString());

            for (Player a : players) {



                if (a.getHand().contains(Player)) {
                    System.out.println(a.getName() + " has card " + Player.toString());
                }
                if (a.getHand().contains(Room)) {
                    System.out.println(a.getName() + " has card " + Room.toString());
                }
                if (a.getHand().contains(Weapon)) {
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
                    gamewon = true;
                    winningplayer = player;

            }
                else{
                    System.out.println("God, this is lame, but you are incorrect, you are no longer in the game: "+ player.getName());

                    board.setNotoccupied(player.getloc());
                    board.setPosName(player.getloc(), "");
                    board.printBoard();
                    player.setMadeaccusation(true);
                }

        }





        public boolean canMove(Player player, Location loc) {
            Location loc1 = player.getloc();
            if(loc.getX() == 24 ||loc.getX()==-1||loc.getY()==25||loc.getY()==-1){
                return false;
            }
            if(board.getPos(loc).getType().equals("/")) return false;


            if(board.getPos(loc1).getType().equals("O")||board.getPos(loc).getType().equals("O")) return true;



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

