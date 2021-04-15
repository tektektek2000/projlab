package Controllers;

import Model.Asteroid;
import Model.Map;
import Model.Saveable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import Utils.BadFileFormat;
import Utils.Pair;
import Utils.StringPair;

public class FileController {
    ArrayList<Pair<Saveable, ArrayList<StringPair>>> Linkeables = new ArrayList<>();;

    public Object GetWithUID(int UID){
        return new Object();
    }

    private int NextLineShouldBeUID(Scanner FScanner) throws BadFileFormat {
        String data = FScanner.nextLine().replaceAll("//s","");
        if(data.contains(":")){
            String[] args= data.split(":");
            if(args[0].equals("UID")){
                return Integer.parseInt(args[1]);
            }
        }
        throw(new BadFileFormat());
    }
    //To be vastly expanded
    Map Load(File file, GameController GC) throws FileNotFoundException, BadFileFormat {
        Map map = new Map();
        Scanner FScanner = new Scanner(file);
        while (FScanner.hasNextLine()) {
            Linkeables = new ArrayList<>();
            String data = FScanner.nextLine();
            if(data.contains("{")){
                String ClassType = (data.substring(0,data.indexOf('{')).replaceAll("//s",""));
                Pair<Saveable, ArrayList<StringPair>> current = null;
                if(ClassType.equals("Asteroid")){
                    current = new Pair<Saveable, ArrayList<StringPair>>(new Asteroid(NextLineShouldBeUID(FScanner)),new ArrayList<>());
                }
                do{
                    data = FScanner.nextLine().replaceAll("//s","");
                    if(!data.contains("}")){
                        current.second.add(new StringPair(data.split(":")));
                    }
                }while(!data.contains("}"));
            }
        }
        for(Pair<Saveable, ArrayList<StringPair>> it : Linkeables){
            it.first.Link(it.second,this);
        }
        return map;
    }

    void Save(File f,Map map) throws FileNotFoundException {
        PrintStream ps = new PrintStream(
                new FileOutputStream(f, true));
        map.Save(ps);
    }
}
