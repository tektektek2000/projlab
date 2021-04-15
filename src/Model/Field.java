package Model;

import java.util.ArrayList;

public abstract class Field extends Saveable {
    ArrayList<Field> Neighbours = new ArrayList<>();
    Sector sector;

    Field(Sector s){
        sector = s;
    }

    public Field(int UID) {
        super(UID);
    }

    // ship moves to the asteroid
    public abstract Asteroid MovedTo();

    // removes a neighbour
    public void RemoveNeighbour(Field f){
        Neighbours.remove(f);
    }

    // adds a neighbour
    public void AddNeighbour(Field f){
        Neighbours.add(f);
    }

    // getting neighbours list
    public ArrayList<Field> getNeighbours() {
        return Neighbours;
    }

    public abstract void SunStorm();
}
