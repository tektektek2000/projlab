package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class Sun {
    private static Sun instance=null;
    private ArrayList<Ship> affecteds = new ArrayList<>();
    private static boolean StormNow=false;
    private ArrayList<Ship> ToBeDeleted = new ArrayList<>();

    private Sun(){}

    // static instance getter
    public static Sun GetInstance(){
        if(instance == null){
            instance = new Sun();
        }
        return instance;
    }

    // adds ship to the affected list
    public void AddAffected(Ship s){
        Skeleton.AddAndPrintCallStack("Sun.AddAffected()");
        affecteds.add(s);
        Skeleton.RemoveFromCallStack("Sun.AddAffected()");
    }

    // removes ship from the affected list
    public void RemoveAffected(Ship s){
        Skeleton.AddAndPrintCallStack("Sun.RemoveAffected()");
        if(StormNow)
            ToBeDeleted.add(s);
        else
            affecteds.remove(s);
        Skeleton.RemoveFromCallStack("Sun.RemoveAffected()");
    }

    // calls sun storm if zero turns left until sun storm
    public void TurnOver(){
        Skeleton.AddAndPrintCallStack("Sun.TurnOver()");
        Scanner in = new Scanner(System.in);
        System.out.println("How many turns until a storm happens? [num]");
        if(in.nextInt() == 0){
            StormNow = true;
            for(Ship s : affecteds){
                s.SunStormNow();
            }
            for(Ship s : ToBeDeleted){
                affecteds.remove(s);
            }
            affecteds.clear();
            StormNow=false;
        }
        Skeleton.RemoveFromCallStack("Sun.TurnOver()");
    }

    // checks if the given asteroid is in sun close area
    public boolean isClose(Asteroid a){
        Scanner in = new Scanner(System.in);
        boolean yes;
        yes = Skeleton.AskPlayer("Is the given asteroid near to your current one?");
        return yes;
    }
    public static void Reset(){
        instance = null;
    }
}
