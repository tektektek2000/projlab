package Model;

import Controllers.FileController;

import java.io.PrintStream;
import java.util.ArrayList;

public class Map {
    static int UIDMax=1;

    private ArrayList<Sector> sectors;

    public void AddSector(Sector s){
        sectors.add(s);
    }

    public ArrayList<Sector> getSectors(){return sectors;}

    static int GetNewUID(){
        return UIDMax++;
    }

    public void Save(PrintStream ps){
        for(Sector it : sectors){
            it.Save(ps);
        }
    }
}
