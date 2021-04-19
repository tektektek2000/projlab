package Model;

import Controllers.FileController;
import Utils.LinkerException;
import Utils.StringPair;

import java.io.PrintStream;
import java.util.ArrayList;

public abstract class Saveable {
    final int UID;

    public Saveable(Map map){
        UID = map.GetNewUID();
    }

    public Saveable(int uid) {
        UID = uid;
    }

    public int GetUID(){
        return UID;
    }
    public abstract void Link(ArrayList<StringPair> args, FileController fc) throws LinkerException;
    public abstract void Save(PrintStream os, boolean CallChildren);
}