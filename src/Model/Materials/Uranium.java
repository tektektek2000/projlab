package Model.Materials;
import Controllers.FileController;
import Model.Asteroid;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.lang.String;
import java.util.ArrayList;

public class Uranium extends Material{

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

    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws RuntimeErrorException {

    }

    @Override
    public void Save(PrintStream os) {

    }
}
