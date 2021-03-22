import Model.Ship;
import Model.Skeleton;

import Model.Skeleton;

import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        int chosen=0;
        Skeleton s = new Skeleton();
        Scanner in = new Scanner(System.in);

        while(chosen != 99) {
            System.out.println("1: Player drills asteroid, with ice inside it");
            System.out.println("2: Player drills asteroid, with coal inside it");
            System.out.println("3: Player drills asteroid, with iron inside it");
            System.out.println("4: Player drills asteroid, with uranium inside it");
            System.out.println("5: Player mines asteroid, with ice inside it");
            System.out.println("6: Player mines asteroid, with coal inside it");
            System.out.println("7: Player mines asteroid, with iron inside it");
            System.out.println("8: Player mines asteroid, with uranium inside it");
            System.out.println("9: Player builds an already existing base with some resources");
            System.out.println("10: Player puts down a teleport gate");
            System.out.println("11: Player crafts a robot");
            System.out.println("12: Player crafts a teleport gates");
            System.out.println("13: Player crafts a base foundation");
            System.out.println("14: Player moves to asteroid");
            System.out.println("15: Player moves through teleport");
            System.out.println("16: Sun takes its turn");
            System.out.println("99: Exit");


            chosen=in.nextInt();
            switch (chosen){
                case 1:
                    s.PlayerDrillsIce();
                    break;
                case 2:
                    s.PlayerDrillsCoal();
                    break;
                case 3:
                    s.PlayerDrillsIron();
                    break;
                case 4:
                    s.PlayerDrillsUranium();
                    break;
                case 5:
                    s.PlayerMinesIce();
                    break;
                case 6:
                    s.PlayerMinesCoal();
                    break;
                case 7:
                    s.PlayerMinesIron();
                    break;
                case 8:
                    s.PlayerMinesUranium();
                    break;
                case 9:
                    s.PlayerShipBuildsBase();
                    break;
                case 10:
                    s.PlayerPutsDownTeleport();
                    break;
                case 11:
                    s.PlayerCraftRobot();
                    break;
                case 12:
                    s.PlayerCraftTeleports();
                    break;
                case 13:
                    s.PlayerCraftBaseFoundation();
                    break;
                case 14:
                    s.PlayerMovesToAsteroid();
                    break;
                case 15:
                    s.PlayerMovesThroughTeleport();
                    break;
                case 16:
                    s.SunTakesTurn();
                    break;
            }

        }
    }
}
