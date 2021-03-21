package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class Sun {
    private static Sun instance=null;
    private ArrayList<Ship> affecteds;

    private Sun(){}
    public static Sun GetInstance(){
        if(instance == null){
            instance = new Sun();
        }
        return instance;
    }
    public void AddAffected(Ship s){
        affecteds.add(s);
    }
    public void RemoveAffected(Ship s){
        affecteds.remove(s);
    }
    public void TurnOver(){
        Scanner in = new Scanner(System.in);

        System.out.println("How many turns until a storm happens? [num]");
        if(in.nextInt() == 0){
            for(Ship s : affecteds){
                s.SunStormNow();
            }
        }
    }
    public boolean isClose(Asteroid a){
        Scanner in = new Scanner(System.in);
        boolean yes;

        yes = Skeleton.AskPlayer("Is the given asteroid near to your current one? [Y/N]");
        if(yes) return true;
        else return false;
    }
}
