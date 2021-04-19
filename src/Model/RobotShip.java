package Model;

import Controllers.FileController;
import Utils.LinkerException;
import Utils.StringPair;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * It represents the robots, the controller is the AIController
 * It bases from the Ship class
 */
public class RobotShip extends Ship {

    RobotShip(Asteroid a){
        super(a);
    }

    public RobotShip(int uid){
        super(uid);
    }

    /**
     * in case of asteroid exploding robot ship flies over to a close asteroid
     */

    public void AsteroidExploding(){
        ArrayList<Field> neighbours = asteroid.getNeighbours();
        Move(neighbours.get(new Random().nextInt(neighbours.size())));
    }

    /**
     * robot ship dies
     */
    public void Die(){
        asteroid.Remove(this);
    }

    @Override
    public String toString(){
        return "RobotShip";
    }

    /**
     * @param args
     * @param fc
     * @throws LinkerException
     */
    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws LinkerException {
        super.Link(args,fc);
    }

    /**
     * @param os the stream, where the class will be written
     * @param CallChildren
     */
    @Override
    public void Save(PrintStream os, boolean CallChildren) {
        os.println("RobotShip{");
        super.Save(os, CallChildren);
        os.println("}");
    }
}
