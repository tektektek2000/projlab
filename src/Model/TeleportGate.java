package Model;

public class TeleportGate extends Field{
    TeleportGate pair;

    // ship moves to the asteroid
    public Asteroid MovedTo(Ship s){
        if(!Skeleton.AskPlayer("Is the Teleport gate active?"))
            return s.getAsteroid();
        return pair.Neighbours.get(0).MovedTo(s);
    }

    // check whether the teleport gate is active or not
    boolean isActive(){
        // if pair is not init gives back false
        if(pair==null){
            return false;
        }
        // if pair is exists fives back true
        else if(Neighbours.size()>0 && pair.Neighbours.size()>0){
            return true;
        }
        return false;
    }

    // sets the pair of teleportgate
    void pair(TeleportGate t){
        pair = t;
        t.pair = this;
    }
}
