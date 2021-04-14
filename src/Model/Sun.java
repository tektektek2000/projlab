package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Sun {
    private static Sun instance=null;
    private static boolean StormNow=false;
    private ArrayList<Ship> ToBeDeleted = new ArrayList<>();
    private Map map;
    private int RoundsUntillStorm;

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

    // calls sun storm if zero turns left until sun storm
    public void TurnOver(){
        RoundsUntillStorm--;
        if(RoundsUntillStorm==0){
            for(Sector s : map.getSectors()){
                s.SunStorm();
            }
            RoundsUntillStorm = new Random().nextInt(6)+3;
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
