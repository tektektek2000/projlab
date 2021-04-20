package Model;

import Controllers.FileController;
import Controllers.NotificationManager;
import Utils.LinkerException;
import Utils.StringPair;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class RobotShip extends Ship {

    public RobotShip(Asteroid a){
        super(a);
    }

    public RobotShip(int uid){
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
        NotificationManager.AddMessage("Robot" + GetUID() + " died");
        asteroid = null;
    }

    // drills on the asteroid
    public void Drill(){
        asteroid.GetDrilled();
        NotificationManager.AddMessage("Robot" + GetUID() + " drilled Asteroid" + asteroid.GetUID());
    }

    @Override
    public String toString(){
        return "RobotShip";
    }

    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws LinkerException {
        super.Link(args,fc);
    }

    @Override
    public void Save(PrintStream os, boolean CallChildren) {
        os.println("RobotShip{");
        super.Save(os, CallChildren);
        os.println("}");
    }
}
