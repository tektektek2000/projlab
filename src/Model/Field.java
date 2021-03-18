package Model;

public abstract class Field {
    public abstract Asteroid MovedTo(Ship s);
    public abstract void RemoveNeighbour(Field f);
}
