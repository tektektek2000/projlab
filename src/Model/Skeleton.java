package Model;

import java.util.Locale;
import java.util.Scanner;

public class Skeleton {

    public static boolean AskPlayer(String PrompText){
        Scanner in = new Scanner(System.in);
        char c;
        do{
            System.out.println(PrompText);
            c = in.next().toLowerCase().charAt(0);
        } while(c != 'y' && c != 'n');
        return  c == 'y';
    }

    public static int AskPlayerForInt(String PromptText){
        Scanner in = new Scanner(System.in);
        System.out.println(PromptText);
        return in.nextInt();
    }
}
