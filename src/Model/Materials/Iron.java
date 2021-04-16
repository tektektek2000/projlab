package Model.Materials;
import Controllers.FileController;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.lang.String;
import java.util.ArrayList;

public class Iron extends Material {

    Iron(){}

    public Iron(int uid) {super(uid);}

    // gives back type in string
    protected  String GetTypeUnique(){
        return "Iron";
    }

    @Override
    public String toString(){
        return "Iron";
    }

    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws RuntimeErrorException {
        super.Link(args,fc);
    }

    @Override
    public void Save(PrintStream os) {
        os.println("Iron{");
        super.Save(os);
        os.println("}");
    }
}
