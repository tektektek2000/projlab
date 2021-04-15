package Model;

import Controllers.FileController;

import javax.management.RuntimeErrorException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public abstract class Saveable {
    final int UID;

    Saveable(){
        UID = Map.GetNewUID();
    }
    int GetUID(){
        return UID;
    }
    public abstract void Link(ArrayList<StringPair> args, FileController fc) throws RuntimeErrorException;
    public abstract void Save(PrintStream os);
}