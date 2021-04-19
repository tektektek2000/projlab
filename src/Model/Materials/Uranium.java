package Model.Materials;
import Controllers.FileController;
import Model.Asteroid;
import Model.Map;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.lang.String;
import java.util.ArrayList;

public class Uranium extends Material{
    int ExposedFor;
    boolean isExposed;
    Asteroid LastAsteroid;

    Uranium(Map m){
        super(m);
    }

    public Uranium(int uid) {super(uid);}

    // gives back type in string
    protected String GetTypeUnique(){
        return "Uranium";
    }

    // special action if the sun is close
    // in Uranium case it is exploding
    @Override
    public void DrilledThroughSunClose(Asteroid asteroid){
        LastAsteroid = asteroid;
        isExposed = true;
    }
    @Override
    public String toString(){
        return "Uranium";
    }

    @Override
    public void PickedUp(){
        isExposed = false;
        LastAsteroid = null;
    }

    public void TurnOver(){
        if(isExposed){
            ExposedFor++;
            if(ExposedFor>=3){
                LastAsteroid.Explode();
            }
        }
    }

    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws RuntimeErrorException {
        super.Link(args,fc);
        for(StringPair it : args) {
            if(it.first.equals("ExposedFor")){
                ExposedFor = Integer.parseInt(it.second);
            }
            else if(it.first.equals("isExposed")){
                isExposed = Boolean.parseBoolean(it.second);
            }
        }
    }

    @Override
    public void Save(PrintStream os, boolean CallChildren) {
        os.println("Uranium{");
        super.Save(os, CallChildren);
        os.println("ExposedFor: " + ExposedFor);
        os.println("isExposed: " + isExposed);
        os.println("}");
    }
}
