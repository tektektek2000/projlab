package Model;

import Controllers.FileController;
import Model.Materials.Material;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class RobotShip extends Ship {

    RobotShip(){}

    RobotShip(int uid){
        super(uid);
    }

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

    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws RuntimeErrorException {
        super.Link(args,fc);
    }

    @Override
    public void Save(PrintStream os) {
        os.println("RobotShip{");
        super.Save(os);
        os.println("}");
    }
}
