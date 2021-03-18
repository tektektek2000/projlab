package Model;

import Model.Materials.Material;

public abstract class Asteroid extends Field {
    public abstract void Remove(Ship s);
    public abstract void Add(Ship s);
    public abstract void GetDrilled();
    public abstract Material GetMined();
    public abstract Asteroid MovedTo(Ship s);
    public abstract void Explode();
}
