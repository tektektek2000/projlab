package Model;

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
}
