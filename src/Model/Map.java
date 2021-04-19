package Model;

import java.io.PrintStream;
import java.util.ArrayList;

public class Map {
    int UIDMax=1;

    private ArrayList<Sector> sectors=new ArrayList<>();

    public void AddSector(Sector s){
        sectors.add(s);
    }

    public ArrayList<Sector> getSectors(){return sectors;}

    public int GetNewUID(){
        return UIDMax++;
    }

    public int GetUIDMax() {return  UIDMax; }

    public void SetMaxId(int newmax) {UIDMax = newmax;}

    public void Save(PrintStream ps){
        for(Sector it : sectors){
            it.Save(ps, true);
        }
    }
}
