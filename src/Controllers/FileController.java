package Controllers;

import Model.*;
import Model.Materials.*;

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
        for(Pair<Saveable, ArrayList<StringPair>> it : Linkeables){
            if(it.first.GetUID() == UID){
                return it.first;
            }
        }
        return null;
    }

    static String Trim(String s){
        s = s.replaceAll("\t","");
        return s.replaceAll(" ","");
    }

    private int NextLineShouldBeUID(Scanner FScanner) throws BadFileFormat {
        String data = Trim(FScanner.nextLine());
        if(data.contains(":")){
            String[] args= data.split(":");
            if(args[0].equals("UID")){
                return Integer.parseInt(args[1]);
            }
        }
        System.out.println("Failed with:" + data);
        throw(new BadFileFormat());
    }
    //To be vastly expanded
    public Map Load(File file, GameController GC) throws FileNotFoundException, BadFileFormat {
        Map map = new Map();
        Scanner FScanner = new Scanner(file);
        Linkeables = new ArrayList<>();
        while (FScanner.hasNextLine()) {
            String data = FScanner.nextLine();
            if(data.contains("{")){
                String ClassType = Trim((data.substring(0,data.indexOf('{'))));
                Pair<Saveable, ArrayList<StringPair>> current = null;
                switch (ClassType) {
                    case "Asteroid":
                        current = new Pair<Saveable, ArrayList<StringPair>>(new Asteroid(NextLineShouldBeUID(FScanner)), new ArrayList<>());
                        break;
                    case "Base":
                        current = new Pair<Saveable, ArrayList<StringPair>>(new Base(NextLineShouldBeUID(FScanner)), new ArrayList<>());
                        break;
                    case "Coal":
                        current = new Pair<Saveable, ArrayList<StringPair>>(new Coal(NextLineShouldBeUID(FScanner)), new ArrayList<>());
                        break;
                    case "Iron":
                        current = new Pair<Saveable, ArrayList<StringPair>>(new Iron(NextLineShouldBeUID(FScanner)), new ArrayList<>());
                        break;
                    case "Ice":
                        current = new Pair<Saveable, ArrayList<StringPair>>(new Ice(NextLineShouldBeUID(FScanner)), new ArrayList<>());
                        break;
                    case "PlayerShip":
                        PlayerShip ps = new PlayerShip(NextLineShouldBeUID(FScanner));
                        current = new Pair<Saveable, ArrayList<StringPair>>(ps, new ArrayList<>());
                        GC.ps.add(ps);
                        break;
                    case "RobotShip":
                        RobotShip rs = new RobotShip(NextLineShouldBeUID(FScanner));
                        current = new Pair<Saveable, ArrayList<StringPair>>(rs, new ArrayList<>());
                        GC.rs.add(rs);
                        break;
                    case "Sector":
                        Sector s = new Sector(NextLineShouldBeUID(FScanner));
                        current = new Pair<Saveable, ArrayList<StringPair>>(s, new ArrayList<>());
                        map.AddSector(s);
                        break;
                    case "TeleportGate":
                        TeleportGate t = new TeleportGate(NextLineShouldBeUID(FScanner));
                        current = new Pair<Saveable, ArrayList<StringPair>>(t, new ArrayList<>());
                        GC.tgs.add(t);
                        break;
                    case "UFO":
                        UFO u = new UFO(NextLineShouldBeUID(FScanner));
                        current = new Pair<Saveable, ArrayList<StringPair>>(u, new ArrayList<>());
                        GC.ufos.add(u);
                        break;
                    default:
                        throw(new BadFileFormat());
                }
                do{
                    data = Trim(FScanner.nextLine());
                    if(!data.contains("}")){
                        current.second.add(new StringPair(data.split(":")));
                    }
                }while(!data.contains("}"));
                Linkeables.add(current);
            }
        }
        for(Pair<Saveable, ArrayList<StringPair>> it : Linkeables){
            it.first.Link(it.second,this);
        }
        return map;
    }

    public void Save(File f,Map map) throws FileNotFoundException {
        PrintStream ps = new PrintStream(
                new FileOutputStream(f, true));
        map.Save(ps);
    }
}
