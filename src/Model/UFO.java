package Model;

import Model.Materials.Material;

import java.util.ArrayList;

public class UFO extends Ship{
    ArrayList<Material> materials;

    @Override
    public void Die() {
        asteroid.Remove(this);
        asteroid = null;
    }

    @Override
    public void AsteroidExploding() {
        Die();
    }
}
