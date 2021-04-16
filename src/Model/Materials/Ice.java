package Model.Materials;
import Controllers.FileController;
import Model.Asteroid;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.lang.String;
import java.util.ArrayList;

public class Ice extends Material {

    Ice(){}

    public Ice(int uid) {super(uid);}

    // gives back type in string
    protected  String GetTypeUnique(){
        return "Ice";
    }

    // special action if the sun is close
    // in Ice case it is evaporating
    @Override
    public void DrilledThroughSunClose(Asteroid asteroid){
        asteroid.Evaporate();
    }

    @Override
    public String toString(){
        return "Ice";
    }

    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws RuntimeErrorException {
        super.Link(args,fc);
    }

    @Override
    public void Save(PrintStream os) {
        os.println("Ice{");
        super.Save(os);
        os.println("}");
    }
}
