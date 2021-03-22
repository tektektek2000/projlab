package Model;

import java.util.Scanner;

public class Skeleton {

    public static boolean AskPlayer(String question) {
        Scanner in = new Scanner(System.in);
        char c = 'x';
        do {
            System.out.println(question);
            c = in.next().toLowerCase().charAt(0);
        } while (c != 'y' && c != 'n');
        return c == 'Y';
    }

    public static int AskPlayerForInt(String question){
        Scanner in = new Scanner(System.in);
        System.out.println(question);
        return in.nextInt();
    }
}
