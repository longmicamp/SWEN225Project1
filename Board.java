import java.io.*;

import java.util.ArrayList;

public class Board {

    public Position[][] board = new Position[24][25];
    private ArrayList<Location> PlayerPos = new ArrayList<Location>();


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
                            board[a][row] = new Position(loca, b[a], false);
                            break;
                        case "K":
                            board[a][row] = new Position(loca, b[a], false);
                            break;
                        case "H":
                            board[a][row] = new Position(loca, b[a], false);
                            break;
                        case "B":
                            board[a][row] = new Position(loca, b[a], false);
                            break;
                        case "C":
                            board[a][row] = new Position(loca, b[a], false);
                            break;
                        case "D":
                            board[a][row] = new Position(loca, b[a], false);
                            break;
                        case "R":
                            board[a][row] = new Position(loca, b[a], false);
                            break;
                        case "L":
                            board[a][row] = new Position(loca, b[a], false);
                            break;
                        case "U":
                            board[a][row] = new Position(loca, b[a], false);
                            break;
                        case "Z":
                            board[a][row] = new Position(loca, b[a], false);
                            break;
                        case "S":
                            board[a][row] = new Position(loca, b[a], false);
                            break;
                        case "1":
                            board[a][row] = new Position(loca, "H", false);
                            PlayerPos.add(new Location(a, row));
                            break;
                        case "2":
                            board[a][row] = new Position(loca, "H", false);
                            PlayerPos.add(new Location(a, row));
                            break;
                        case "3":
                            board[a][row] = new Position(loca, "H", false);
                            PlayerPos.add(new Location(a, row));
                            break;
                        case "4":
                            board[a][row] = new Position(loca, "H", false);
                            PlayerPos.add(new Location(a, row));
                            break;
                        case "5":
                            board[a][row] = new Position(loca, "H", false);
                            PlayerPos.add(new Location(a, row));
                            break;
                        case "6":
                            board[a][row] = new Position(loca, "H", false);
                            PlayerPos.add(new Location(a, row));
                            break;
                        case "V":
                            board[a][row] = new Position(loca, "H", false);

                            break;
                        case "O":

                            board[a][row] = new Position(loca, "H", false);
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


    public void setBoard(Location loc, String name, boolean Occupy) {
        board[loc.getX()][loc.getY()] = new Position(loc, name, Occupy);
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
    public void setOccupied(Location a){

        board[a.getX()][a.getY()].setOccupied(true);
    }







}
