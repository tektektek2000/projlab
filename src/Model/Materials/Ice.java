package Model.Materials;
import Controllers.FileController;
import Model.Asteroid;
import Model.Map;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.lang.String;
import java.util.ArrayList;

public class Ice extends Material {

    Ice(Map m){super(m);}

    public Ice(int uid) {super(uid);}

    /**
     * gives back type in string
     * @return with the type of the material, in this case "Ice"
     */
    protected  String GetTypeUnique(){
        return "Ice";
    }

    /**
     * special action if the sun is close and the asteroid's shell becomes 0
     * in Ice case, it is evaporating
     * @param asteroid the asteroid where the drilling happened
     */
    @Override
    public void DrilledThroughSunClose(Asteroid asteroid){
        asteroid.Evaporate();
    }

    /**
     * @param args
     * @param fc
     * @throws RuntimeErrorException
     */
    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws RuntimeErrorException {
        super.Link(args,fc);
    }

    /**
     * the save method for the Ice class
     * @param os the stream, where the class will be written
     */
    @Override
    public void Save(PrintStream os, boolean CallChildren) {
        os.println("Ice{");
        super.Save(os, CallChildren);
        os.println("}");
    }
}
