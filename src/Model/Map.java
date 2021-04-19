package Model;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Represents the map of the game.
 */
public class Map {
    /**
     * The current maximum of the UID.
     */
    int UIDMax=1;
    /**
     * The sectors in the map.
     */
    private ArrayList<Sector> sectors=new ArrayList<>();

    /**
     * Adds a sector to the map.
     * @param s The sector we want to add to the map.
     */
    public void AddSector(Sector s){
        sectors.add(s);
    }

    /**
     * The getter of the sectors.
     * @return With the sectors of the map.
     */
    public ArrayList<Sector> getSectors(){return sectors;}

    /**
     * Gives back a new UID, based on the current maximum.
     * @return With the new UID.
     */
    public int GetNewUID(){
        return UIDMax++;
    }

    /**
     * The getter of the UIDMax.
     * @return With the current value of the UIDMax.
     */
    public int GetUIDMax() {return  UIDMax; }

    /**
     * The setter of the UIDMax.
     * @param newmax The new value we want to set for the UIDMax.
     */
    public void SetMaxId(int newmax) {UIDMax = newmax;}

    /**
     * The save method for the Map class.
     * @param ps The stream, where the class will be written.
     */
    public void Save(PrintStream ps){
        for(Sector it : sectors){
            it.Save(ps, true);
        }
    }
}
