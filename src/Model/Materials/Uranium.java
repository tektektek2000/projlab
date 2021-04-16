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
        asteroid.Explode();
    }
    @Override
    public String toString(){
        return "Uranium";
    }

    public void ForceExplode(){

    }

    public void TurnOver(){

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
    public void Save(PrintStream os) {
        os.println("Uranium{");
        super.Save(os);
        os.println("ExposedFor: " + ExposedFor);
        os.println("isExposed: " + isExposed);
        os.println("}");
    }
}
