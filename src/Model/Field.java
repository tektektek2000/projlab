package Model;

import java.util.ArrayList;

public abstract class Field {
    ArrayList<Field> Neighbours;

    public abstract Asteroid MovedTo(Ship s);
    public void RemoveNeighbour(Field f){
        Neighbours.remove(f);
        System.out.println("Removed Neighbour: "  + f);
    }
    public void AddNeighbour(Field f){
        Neighbours.remove(f);
        System.out.println("Removed Neighbour: "  + f);
    }

    public ArrayList<Field> getNeighbours() {
        return Neighbours;
    }
}
