package Model;

import java.util.ArrayList;
import java.util.Random;

public class RobotShip extends Ship {
    public void AsteroidExploding(){
        ArrayList<Field> neighbours = asteroid.getNeighbours();
        neighbours.get(new Random().nextInt(neighbours.size())).MovedTo(this);
    }
    public void Die(){
        System.out.println("Robot died");
        asteroid.Remove(this);
    }
}
