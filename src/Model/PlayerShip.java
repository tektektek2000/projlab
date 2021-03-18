package Model;

import Model.Materials.BillOfMaterial;
import Model.Materials.Material;

public abstract class PlayerShip extends Ship {
    public abstract void Mine();
    public abstract void Remove(BillOfMaterial b);
    public abstract void CraftTeleportGates();
    public abstract void CraftRobot();
    public abstract void CraftBase();
    public abstract void PutBack(Material m);
    public abstract void BuildBase();
    public abstract void PutDown(TeleportGate t);
    public abstract void Die();
    public abstract void AsteroidExploding();
    public abstract void Add(Material m);
    public abstract void Remove(Material m);
    public abstract void Add(TeleportGate t);
    public abstract void Remove(TeleportGate t);
}
