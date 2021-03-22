package Model;

public class TeleportGate extends Field{
    TeleportGate pair;

    // ship moves to the asteroid
    public Asteroid MovedTo(Ship s){
        Skeleton.AddAndPrintCallStack("TeleportGate.MovedTo()");
        if(!isActive()) {
            System.out.println("Can't use teleport because it is not active");
            Skeleton.RemoveFromCallStack("TeleportGate.MovedTo()");
            return s.getAsteroid();
        }
        Asteroid ret = pair.Neighbours.get(0).MovedTo(s);
        Skeleton.RemoveFromCallStack("TeleportGate.MovedTo()");
        return ret;
    }

    // check whether the teleport gate is active or not
    boolean isActive(){
        Skeleton.AddAndPrintCallStack("TeleportGate.isActive()");
        Skeleton.RemoveFromCallStack("TeleportGate.isActive()");
        return Skeleton.AskPlayer("Is the Teleport gate active?");
    }

    // sets the pair of teleportgate
    void pair(TeleportGate t){
        Skeleton.AddAndPrintCallStack("TeleportGate.pair()");
        pair = t;
        t.pair = this;
        Skeleton.RemoveFromCallStack("TeleportGate.pair()");
    }

    @Override
    public  String toString(){
        return "TeleportGate";
    }
}
