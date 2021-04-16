package Model;

import Controllers.FileController;
import Model.Materials.Material;
import Utils.LinkerException;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.util.ArrayList;

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

    public void Move(Field f){

    }

    public void TurnOver(){

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
    public void Save(PrintStream os) {
        os.println("TeleportGate{");
        super.Save(os);
        os.println("WasHitBySunStorm: " + WashHitByStorm);
        if(pair!=null)
            os.println("Pair:" + pair.GetUID());
        os.println("}");
    }
}
