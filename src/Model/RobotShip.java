package Model;

import java.util.ArrayList;
import java.util.Random;

public class RobotShip extends Ship {

    // in case of asteroid exploding robot ship flies over to a close asteroid
    public void AsteroidExploding(){
        Skeleton.AddAndPrintCallStack("RobotShip.AsteroidExploding()");
        ArrayList<Field> neighbours = asteroid.getNeighbours();
        neighbours.get(new Random().nextInt(neighbours.size())).MovedTo(this);
        Skeleton.RemoveFromCallStack("RobotShip.AsteroidExploding()");
    }

    // robot ship dies
    public void Die(){
        Skeleton.AddAndPrintCallStack("RobotShip.Die()");
        System.out.println("Robot died");
        asteroid.Remove(this);
        Skeleton.RemoveFromCallStack("RobotShip.Die()");
    }

    @Override
    public String toString(){
        return "RobotShip";
    }
}
