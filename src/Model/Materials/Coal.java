package Model.Materials;

import Controllers.FileController;
import Model.Map;
import Utils.StringPair;
import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.lang.String;
import java.util.ArrayList;

/**
 * Represents the coal material in the game.
 */
public class Coal extends Material{

    Coal(Map m){super(m);}

    public Coal(int uid) {super(uid);}

    /**
     * Gives back type in string.
     * @return With the type of the material, in this case "Coal".
     */
    protected String GetTypeUnique(){
        return "Coal";
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
     * The save method for the Coal class.
     * @param os The stream, where the class will be written.
     * @param CallChildren
     */
    @Override
    public void Save(PrintStream os, boolean CallChildren) {
        os.println("Coal{");
        super.Save(os, CallChildren);
        os.println("}");
    }
}