import java.io.*;

import java.util.ArrayList;

public class Board {

    public Position[][] board = new Position[24][25];
    private ArrayList<Location> PlayerPos = new ArrayList<Location>();
    private ArrayList<Location> secretPath1 = new ArrayList<Location>();
    private ArrayList<Location> secretPath2 = new ArrayList<Location>();





    public void makeBoard() {

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new FileReader(new File("SWEN225Board")));
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] b = line.split("");
                for (int a = 0; a < b.length; a++) {
                    Location loca = new Location(a, row);

                    switch (b[a]) {
                        case "/":
                            board[a][row] = new Position(loca, b[a], false, null);

                            break;
                        case ">":
                            board[a][row] = new Position(loca, b[a], false, null);

                            secretPath2.add(new Location(a,row));
                            break;
                        case "?":
                            board[a][row] = new Position(loca, b[a], false, null);
                            secretPath1.add(new Location(a,row));
                            break;
                        case "K":
                            board[a][row] = new Position(loca, b[a], false, "KITCHEN");
                            break;
                        case "H":
                            board[a][row] = new Position(loca, b[a], false, null);
                            break;
                        case "B":
                            board[a][row] = new Position(loca, b[a], false,"BALLROOM");
                            break;
                        case "C":
                            board[a][row] = new Position(loca, b[a], false, "CONSERVATORY");
                            break;
                        case "D":
                            board[a][row] = new Position(loca, b[a], false, "DININGROOM");
                            break;
                        case "R":
                            board[a][row] = new Position(loca, b[a], false,"BILLIARDROOM");
                            break;
                        case "L":
                            board[a][row] = new Position(loca, b[a], false, "LIBRARY");
                            break;
                        case "U":
                            board[a][row] = new Position(loca, b[a], false, "LOUNGE");
                            break;
                        case "Z":
                            board[a][row] = new Position(loca, b[a], false,"HALL");
                            break;
                        case "S":
                            board[a][row] = new Position(loca, b[a], false,"STUDY");
                            break;
                        case "1":
                            board[a][row] = new Position(loca, "H", false,null);
                            PlayerPos.add(new Location(a, row));
                            break;
                        case "2":
                            board[a][row] = new Position(loca, "H", false,null);
                            PlayerPos.add(new Location(a, row));
                            break;
                        case "3":
                            board[a][row] = new Position(loca, "H", false,null);
                            PlayerPos.add(new Location(a, row));
                            break;
                        case "4":
                            board[a][row] = new Position(loca, "H", false,null);
                            PlayerPos.add(new Location(a, row));
                            break;
                        case "5":
                            board[a][row] = new Position(loca, "H", false,null);
                            PlayerPos.add(new Location(a, row));
                            break;
                        case "6":
                            board[a][row] = new Position(loca, "H", false,null);
                            PlayerPos.add(new Location(a, row));
                            break;
                        case "V":
                            board[a][row] = new Position(loca, "H", false,null);

                            break;
                        case "O":

                            board[a][row] = new Position(loca, "O", false,null);
                            break;
                    }
                }
                row++;
            }
            //System.out.println(PlayerPos.get(4));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }


    public ArrayList<Location> getSecretPath1() {
        return secretPath1;
    }

    public void setSecretPath1(ArrayList<Location> secretPath1) {
        this.secretPath1 = secretPath1;
    }

    public ArrayList<Location> getSecretPath2() {
        return secretPath2;
    }

    public void setSecretPath2(ArrayList<Location> secretPath2) {
        this.secretPath2 = secretPath2;
    }

    public void printBoard() {
        for (int a = 0; a < 25; a++) {
            for (int b = 0; b < 24; b++) {
                if(board[b][a].isOccupied()){
                    System.out.print(board[b][a].getName() + " ");
                }
                else {
                    System.out.print(board[b][a].getType()+ " ");
                }


            }
            System.out.println("");
        }
    }


    public void setBoard(Location loc, String name, boolean Occupy, String rn) {
        board[loc.getX()][loc.getY()] = new Position(loc, name, Occupy, rn);
    }

    public Position getBoardLocation(int a, int b) {
        return board[a][b];

    }

    public void setPosName(Location loc, String name){
        board[loc.getX()][loc.getY()].setName(name);
    }

    public Position getPos(Location loc) {
        return board[loc.getX()][loc.getY()];
    }





    public Location getPlayerPos(int a){
        return PlayerPos.get(a);
    }

    public ArrayList<Location> getPlayerLocations(){
        return PlayerPos;
    }
    public void setOccupied(Location a){

        board[a.getX()][a.getY()].setOccupied(true);
    }
    public void setNotoccupied(Location a){

        board[a.getX()][a.getY()].setOccupied(false);
    }







}
