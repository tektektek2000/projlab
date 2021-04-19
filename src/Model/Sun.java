package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * It represents the Sun in the game.
 * It controls the SunStorms
 */
public class Sun {
    private static Sun instance=null;
    private static boolean StormNow=false;
    private Map map;
    private int RoundsUntillStorm;
    private Sector target;

    private Sun(){
        RoundsUntillStorm = new Random().nextInt(6)+3;
    }

    public static Sun GetInstance(){
        if(instance == null){
            instance = new Sun();
        }
        return instance;
    }

    public void SetMap(Map m){
        map = m;
        target = m.getSectors().get(new Random().nextInt(m.getSectors().size()));
        RoundsUntillStorm = new Random().nextInt(6)+3;
    }

    /**
     * @param s the sector where the sun storm will happen
     */
    public void SunStorm(Sector s){
        s.SunStorm();
        RoundsUntillStorm = new Random().nextInt(6)+3;
    }

    /**
     * calls sun storm if zero turns left until sun storm
     */
    public void TurnOver(){
        RoundsUntillStorm--;
        if(RoundsUntillStorm==0){
            SunStorm(target);
            ArrayList<Sector> sectors = map.getSectors();
            Random rand = new Random();
            target = sectors.get(rand.nextInt(sectors.size()));
        }
    }

    public Sector getTarget(){
        return target;
    }

    public void setTarget(Sector sector){
        target = sector;
    }

    public void setRoundsUntillStorm(int val){
        RoundsUntillStorm = val;
    }

    public int getRoundsUntillStorm(){
        return RoundsUntillStorm;
    }

    /**
     * checks if the given asteroid is in sun close area
     * @param a the asteroid which can be close or not close to the sun
     * @return
     */
    public boolean isClose(Asteroid a){
        return false;
    }

    /**
     * reset the instance
     */
    public static void Reset(){
        instance = null;
    }
}
