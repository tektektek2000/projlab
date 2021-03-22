package Model;

import Model.Materials.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Skeleton {
    static ArrayList<String> CallStack = new ArrayList<>();
    static boolean PrintStack=false;


    public static boolean getPrintStack(){
        return PrintStack;
    }

    public static void AddAndPrintCallStack(String s){
        CallStack.add(s);
        if(PrintStack) {
            for (String func : CallStack) {
                System.out.print(" -> ");
                System.out.print(func);
            }
            System.out.println();
        }
    }

    public static void RemoveFromCallStack(String s){
        CallStack.remove(s);
    }

    public static void ResetCallStack(){
        CallStack = new ArrayList<>();
    }

    public static boolean AskPlayer(String question) {
        Scanner in = new Scanner(System.in);
        char c = 'x';
        do {
            System.out.println(question + " [Y/N]");
            c = in.next().toLowerCase().charAt(0);
        } while (c != 'y' && c != 'n');
        return c == 'y';
    }

    public static int AskPlayerForInt(String question){
        Scanner in = new Scanner(System.in);
        System.out.println(question);
        return in.nextInt();
    }


    public static Material AskPlayerForMaterial(String question){
        Scanner in = new Scanner(System.in);
        int choice = 0;
        while(choice < 1 || choice > 4){
            System.out.println(question);
            System.out.println("1 : Ice");
            System.out.println("2 : Coal");
            System.out.println("3 : Iron");
            System.out.println("4 : Uranium");
            choice = in.nextInt();
            if(choice < 1 || choice > 4){
                System.out.println("Invalid option");
            }
        }
        Material m = new Iron();
        switch (choice){
            case 1:
                m = new Ice();
                break;
            case 2:
                m = new Coal();
                break;
            case 3:
                m = new Iron();
                break;
            case 4:
                m = new Uranium();
                break;
        }
        return m;
    }

    public void PlayerDrillsIce(){
        ResetCallStack();
        Ice ice = new Ice();
        Asteroid a = new Asteroid(ice);
        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.Drill();
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerDrillsCoal(){
        Coal coal = new Coal();
        Asteroid a = new Asteroid(coal);
        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.Drill();
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerDrillsIron(){
        Iron iron = new Iron();
        Asteroid a = new Asteroid(iron);
        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.Drill();
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerDrillsUranium(){
        Uranium uranium = new Uranium();
        Asteroid a = new Asteroid(uranium);
        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.Drill();
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerMinesIce(){
        Ice ice = new Ice();
        Asteroid a = new Asteroid(ice);
        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.Mine();
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerMinesCoal(){
        Coal coal = new Coal();
        Asteroid a = new Asteroid(coal);
        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.Mine();
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerMinesIron(){
        Iron iron = new Iron();
        Asteroid a = new Asteroid(iron);
        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.Mine();
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerMinesUranium(){
        Uranium uranium = new Uranium();
        Asteroid a = new Asteroid(uranium);
        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.Mine();
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerShipBuildsBase(){
        Asteroid a = new Asteroid();
        PlayerShip ps = new PlayerShip(a);
        a.SetBase(new Base());
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.BuildBase();
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerPutsDownTeleport(){
        Asteroid a = new Asteroid();
        PlayerShip ps = new PlayerShip(a);
        TeleportGate t = new TeleportGate();
        ps.Add(t);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.PutDown(t);
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerCraftRobot(){
        Asteroid a = new Asteroid();
        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.CraftRobot();
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerCraftTeleports(){
        Asteroid a = new Asteroid();
        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.CraftTeleportGates();
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerCraftBaseFoundation(){
        Asteroid a = new Asteroid();
        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.CraftBase();
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerMovesToAsteroid(){
        Asteroid a = new Asteroid();
        Asteroid b = new Asteroid();
        a.AddNeighbour(b);
        b.AddNeighbour(a);
        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.Move(b);
        PrintStack = false;
        Sun.Reset();
    }

    public void PlayerMovesThroughTeleport(){
        Asteroid a = new Asteroid();
        Asteroid b = new Asteroid();
        TeleportGate t1 = new TeleportGate();
        TeleportGate t2 = new TeleportGate();
        t1.pair(t2);

        a.AddNeighbour(t1);
        t1.AddNeighbour(a);

        b.AddNeighbour(t2);
        t2.AddNeighbour(b);

        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        ps.Move(t1);
        PrintStack = false;
        Sun.Reset();
    }

    public void SunTakesTurn(){
        Asteroid a = new Asteroid();

        PlayerShip ps = new PlayerShip(a);
        Sun.GetInstance().AddAffected(ps);
        PrintStack = true;
        Sun.GetInstance().TurnOver();
        PrintStack = false;
        Sun.Reset();
    }


}
