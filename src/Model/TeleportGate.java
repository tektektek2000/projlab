package Model;

public abstract class TeleportGate {
    public abstract Asteroid MovedTo(Ship s);
    public abstract void RemoveNeighbour(Field f);
}
