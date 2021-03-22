import Model.Ship;
import Model.Skeleton;

import Model.Skeleton;

import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        int chosen=0;
        Skeleton s = new Skeleton();
        Scanner in = new Scanner(System.in);

        while(chosen != 15) {
            System.out.println("1: Player drills asteroid, with ice inside it");
            System.out.println("2: Player drills asteroid, with coal inside it");
            System.out.println("3: Player drills asteroid, with iron inside it");
            System.out.println("4: Player drills asteroid, with uranium inside it");
            System.out.println("1: Player drills asteroid, with ice inside it");
            System.out.println("1: Player drills asteroid, with ice inside it");
            System.out.println("1: Player drills asteroid, with ice inside it");

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
            }

        }
    }
}
