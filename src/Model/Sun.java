package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Sun {
    private static Sun instance=null;
    private static boolean StormNow=false;
    private Map map;
    private int RoundsUntillStorm;
    private Sector target;

    private Sun(){
        RoundsUntillStorm = new Random().nextInt(6)+3;
    }

    // static instance getter
    public static Sun GetInstance(){
        if(instance == null){
            instance = new Sun();
        }
        return instance;
    }

    public void SetMap(Map m){
        map = m;
    }

    public void SunStorm(Sector s){
        //To do
        RoundsUntillStorm = new Random().nextInt(6)+3;
    }

    // calls sun storm if zero turns left until sun storm
    public void TurnOver(Map m){
        RoundsUntillStorm--;
        if(RoundsUntillStorm==0){
            ArrayList<Sector> sectors = m.getSectors();
        }
    }

    // checks if the given asteroid is in sun close area
    public boolean isClose(Asteroid a){
        return false;
    }

    public static void Reset(){
        instance = null;
    }
}
