package Model;

public class TeleportGate extends Field{
    private TeleportGate pair;
    private boolean WashHitByStorm;

    TeleportGate(Sector s){
        super(s);
        WashHitByStorm = false;
    }

    public void SetWashHitByStorm(boolean washHitByStorm){
        WashHitByStorm = washHitByStorm;
    }

    public void SetSector(Sector s){
        sector = s;
    }

    // ship moves to the asteroid
    public Asteroid MovedTo(){
        if(!isActive()) {
            return Neighbours.get(0).MovedTo();
        }
        Asteroid ret = pair.Neighbours.get(0).MovedTo();
        return ret;
    }

    // check whether the teleport gate is active or not
    boolean isActive(){
        return pair != null && pair.Neighbours.size() != 0;
    }

    // sets the pair of teleportgate
    void pair(TeleportGate t){
        pair = t;
        t.pair = this;
    }

    @Override
    public  String toString(){
        return "TeleportGate";
    }

    @Override
    public void RemoveNeighbour(Field f){
        Neighbours.remove(f);
        sector.Remove(this);
    }

    @Override
    public void SunStorm() {
        WashHitByStorm = true;
    }

    public void EndTurn(){
        if(WashHitByStorm){

        }
    }
}
