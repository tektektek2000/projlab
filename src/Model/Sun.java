package Model;

public abstract class Sun {
    private Sun instance=null;

    private Sun(){}
    public abstract Sun GetInstance();
    public abstract void AddAffected(Ship s);
    public abstract void RemoveAffected(Ship s);
    public abstract void TurnOver();
    public abstract boolean isClose(Asteroid a);
}
