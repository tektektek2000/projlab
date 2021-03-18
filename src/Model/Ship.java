package Model;

public abstract class Ship {
    public abstract void Move(Field f);
    public abstract void Die();
    public abstract void Hide();
    public abstract void SunStormNow();
    public abstract void AsteroidExploding();
    public abstract void Drill();
}
