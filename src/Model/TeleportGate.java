package Model;

public class TeleportGate extends Field{
    TeleportGate pair;

    public Asteroid MovedTo(Ship s){
        if(!Skeleton.AskPlayer("Is the Teleport gate active?"))
            return s.getAsteroid();
        return pair.Neighbours.get(0).MovedTo(s);
    }

    boolean isActive(){
        boolean ret = true;
        if(pair==null){
            return false;
        }
        else if(Neighbours.size()>0 && pair.Neighbours.size()>0){
            return true;
        }
        return false;
    }

    void pair(TeleportGate t){
        pair = t;
        t.pair = this;
    }
}
