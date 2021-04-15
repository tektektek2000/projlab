package Model;

import Controllers.FileController;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.util.ArrayList;

public abstract class Saveable {
    final int UID;

    public Saveable(){
        UID = Map.GetNewUID();
    }

    public Saveable(int uid) {
        UID = uid;
    }

    public int GetUID(){
        return UID;
    }
    public abstract void Link(ArrayList<StringPair> args, FileController fc) throws RuntimeErrorException;
    public abstract void Save(PrintStream os);
}