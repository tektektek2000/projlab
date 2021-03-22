package Model;

import java.util.ArrayList;

public abstract class Field {
    ArrayList<Field> Neighbours = new ArrayList<>();

    // ship moves to the asteroid
    public abstract Asteroid MovedTo(Ship s);

    // removes a neighbour
    public void RemoveNeighbour(Field f){
        Skeleton.AddAndPrintCallStack(this.toString() + ".RemoveNeighbour()");
        Neighbours.remove(f);
        if(Skeleton.getPrintStack())
            System.out.println("Removed Neighbour: "  + f);
        Skeleton.RemoveFromCallStack(this.toString() + ".RemoveNeighbour()");
    }

    // adds a neighbour
    public void AddNeighbour(Field f){
        Skeleton.AddAndPrintCallStack(this.toString() + ".AddNeighbour()");
        Neighbours.add(f);
        if(Skeleton.getPrintStack())
            System.out.println("Added Neighbour: "  + f);
        Skeleton.RemoveFromCallStack(this.toString() + ".AddNeighbour()");
    }

    // getting neighbours list
    public ArrayList<Field> getNeighbours() {
        return Neighbours;
    }
}
