package Model;

import java.util.ArrayList;
import java.util.Random;

public class RobotShip extends Ship {

    // in case of asteroid exploding robot ship flies over to a close asteroid
    public void AsteroidExploding(){
        ArrayList<Field> neighbours = asteroid.getNeighbours();
        Move(neighbours.get(new Random().nextInt(neighbours.size())));
    }

    // robot ship dies
    public void Die(){
        asteroid.Remove(this);
    }

    @Override
    public String toString(){
        return "RobotShip";
    }
}
