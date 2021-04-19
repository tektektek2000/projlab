package Model.Materials;
import Controllers.FileController;
import Model.Map;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.lang.String;
import java.util.ArrayList;

public class Iron extends Material {

    Iron(Map m){ super(m);}

    public Iron(int uid) {super(uid);}

    /**
     * gives back type in string
     * @return with the type of the material, in this case "Iron"
     */
    protected  String GetTypeUnique(){
        return "Iron";
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
     * the save method for the Iron class
     * @param os the stream, where the class will be written
     */
    @Override
    public void Save(PrintStream os, boolean CallChildren) {
        os.println("Iron{");
        super.Save(os, CallChildren);
        os.println("}");
    }
}
