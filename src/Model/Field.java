package Model;

import java.util.ArrayList;

public abstract class Field {
    ArrayList<Field> Neighbours;

    // ship moves to the asteroid
    public abstract Asteroid MovedTo(Ship s);

    // removes a neighbour
    public void RemoveNeighbour(Field f){
        Neighbours.remove(f);
        System.out.println("Removed Neighbour: "  + f);
    }

    // adds a neighbour
    public void AddNeighbour(Field f){
        Neighbours.remove(f);
        System.out.println("Removed Neighbour: "  + f);
    }

    // getting neighbours list
    public ArrayList<Field> getNeighbours() {
        return Neighbours;
    }
}
