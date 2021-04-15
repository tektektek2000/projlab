package Model.Materials;
import Controllers.FileController;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.lang.String;
import java.util.ArrayList;

public  class Coal extends Material{

    // gives back type in string
    protected String GetTypeUnique(){
        return "Coal";
    }

    @Override
    public String toString(){
        return "Coal";
    }

    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws RuntimeErrorException {
        super.Link(args,fc);
    }

    @Override
    public void Save(PrintStream os) {
        os.println("Coal{");
        super.Save(os);
        os.println("}");
    }
}
