package Model;

import Controllers.FileController;
import Utils.LinkerException;
import Utils.StringPair;

import java.io.PrintStream;
import java.util.ArrayList;

public abstract class Saveable {
    final int UID;

    /**
     * @param map
     */
    public Saveable(Map map){
        UID = map.GetNewUID();
    }

    public Saveable(int uid) {
        UID = uid;
    }

    public int GetUID(){
        return UID;
    }

    /**
     * @param args
     * @param fc
     * @throws LinkerException
     */
    public abstract void Link(ArrayList<StringPair> args, FileController fc) throws LinkerException;

    /**
     * @param os the stream, where the class will be written
     * @param CallChildren
     */
    public abstract void Save(PrintStream os, boolean CallChildren);
}