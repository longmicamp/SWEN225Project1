

import java.util.Objects;


/*
A class used to determine what position on the board belongs to which type.

When constructing a board a position can either be a room, a valid position, or non-valid

 */
public class Position {

    private Location loc;
    private String type;
    private String name;
    private boolean occupied;




    public Position(Location Loc, String type, boolean occupy) {
        this.loc = Loc;
        this.type = type;
        this.occupied = occupy;
        this.name = "";
    }

    public boolean isOccupied(){
        return occupied;

    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Location getlocation() {
        return loc;
    }

    public void setX(Location x) {
        this.loc =x;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return occupied == position.occupied &&
                Objects.equals(loc, position.loc) &&
                Objects.equals(type, position.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loc, type, occupied);
    }
}

