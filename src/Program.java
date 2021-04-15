import Controllers.FileController;
import Controllers.GameController;
import Model.Map;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        int chosen=0;
        FileController fc = new FileController();
        GameController gc = new GameController();
        try {
            Map m = fc.Load(new File("teszt.txt"),gc);
            m.Save(System.out);
        } catch (Exception e){
            System.out.println(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }


    }
}
