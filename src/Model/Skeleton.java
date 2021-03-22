package Model;

import Model.Materials.*;

import java.util.Scanner;

public class Skeleton {

    public static boolean AskPlayer(String question) {
        Scanner in = new Scanner(System.in);
        char c = 'x';
        do {
            System.out.println(question + " [Y/N]");
            c = in.next().toLowerCase().charAt(0);
        } while (c != 'y' && c != 'n');
        return c == 'Y';
    }

    public static int AskPlayerForInt(String question){
        Scanner in = new Scanner(System.in);
        System.out.println(question);
        return in.nextInt();
    }

    public void PlayerDrillsIce(){
        Ice ice = new Ice();
        Asteroid a = new Asteroid(ice);
        PlayerShip ps = new PlayerShip(a);
        ps.Drill();
    }

    public void PlayerDrillsCoal(){
        Coal coal = new Coal();
        Asteroid a = new Asteroid(coal);
        PlayerShip ps = new PlayerShip(a);
        ps.Drill();
    }

    public void PlayerDrillsIron(){
        Iron iron = new Iron();
        Asteroid a = new Asteroid(iron);
        PlayerShip ps = new PlayerShip(a);
        ps.Drill();
    }

    public void PlayerDrillsUranium(){
        Uranium uranium = new Uranium();
        Asteroid a = new Asteroid(uranium);
        PlayerShip ps = new PlayerShip(a);
        ps.Drill();
    }


}
