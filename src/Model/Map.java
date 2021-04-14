package Model;

import java.util.ArrayList;

public class Map {
    private ArrayList<Sector> sectors;

    public void AddSector(Sector s){
        sectors.add(s);
    }

    public ArrayList<Sector> getSectors(){return sectors;}
}
