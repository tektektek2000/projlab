package Model;

import Controllers.FileController;
import Utils.LinkerException;
import Utils.StringPair;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * It represents the teleport gates of the game
 * Every teleport gate has a pair and it can be hit by the storm when it gets insane.
 */
public class TeleportGate extends Field{
    private TeleportGate pair;
    private boolean WashHitByStorm;


    TeleportGate(Sector s){
        super(s);
        WashHitByStorm = false;
        pair = null;
    }

    public TeleportGate(int UID){
        super(UID);
        pair = null;
    }

    /**
     * basically a setter for the teleport gate's sector
     * @param s the sector, we want to set for the teleport gate
     */
    public void SetSector(Sector s){
        sector = s;
    }

    /**
     * called when a ship moves to a teleport gate
     * @return with the neighbour of the teleport gate's pair if it's possible(active)
     * and with the teleport gate's neighbour if not
     */
    public Asteroid MovedTo(){
        if(!isActive()) {
            return Neighbours.get(0).MovedTo();
        }
        Asteroid ret = pair.Neighbours.get(0).MovedTo();
        return ret;
    }

    /**
     * check whether the teleport gate is active or not
     * @return true if it's active (it's pair is placed and has a neighbour), false if not
     */
    boolean isActive(){
        return pair != null && pair.Neighbours.size() != 0;
    }

    /**
     * sets the pair of teleport gate
     * @param t the teleport gate, which we want to pair the teleport
     */
    void pair(TeleportGate t){
        pair = t;
        t.pair = this;
    }

    /**
     * @param f 
     */
    public void Move(Field f){
        Asteroid dest = f.MovedTo();
        Neighbours.get(0).RemoveNeighbour(this);
        Neighbours.remove(Neighbours.get(0));
        Neighbours.add(dest);
        dest.AddNeighbour(this);
    }

    public void TurnOver(){
        Random rand = new Random();
        if(WashHitByStorm && rand.nextInt(3)<2){
            ArrayList<Field> possibilities = Neighbours.get(0).getNeighbours();
            Field dest = possibilities.get(rand.nextInt(possibilities.size()));
            Move(dest);
        }
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

    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws LinkerException {
        super.Link(args,fc);
        for(StringPair it : args) {
            if(it.first.equals("WasHitBySunStorm")){
                WashHitByStorm = Boolean.parseBoolean(it.second);
            }
            else if(it.first.equals("Pair")){
                pair = (TeleportGate) fc.GetWithUID(Integer.parseInt(it.second));
            }
        }
    }

    @Override
    public void Save(PrintStream os, boolean CallChildren) {
        os.println("TeleportGate{");
        super.Save(os, CallChildren);
        os.println("WasHitBySunStorm: " + WashHitByStorm);
        if(pair!=null)
            os.println("Pair:" + pair.GetUID());
        os.println("}");
    }
}
