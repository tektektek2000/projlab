package Model.Materials;
import java.io.PrintStream;
import java.lang.String;
import java.util.ArrayList;

import Controllers.FileController;
import Model.Asteroid;
import Model.Map;
import Model.Saveable;
import Utils.StringPair;

import javax.management.RuntimeErrorException;

public abstract class Material extends Saveable {

    Material(Map m){super(m);}

    Material(int uid){
        super(uid);
    }

    /**
     * checks if the material given as parameter is same type
     * @param m the material, which we want to compare to
     * @return true, if they are the same type and false if not
     */
    public boolean isSameType(Material m){
        return m.GetTypeUnique().equals(this.GetTypeUnique());
    }

    /**
     * gives back type in string
     * @return with the type of the material
     */
    protected abstract String GetTypeUnique();

    /**
     * special action if the sun is close
     * @param asteroid the asteroid where it happened
     */
    public void DrilledThroughSunClose(Asteroid asteroid){}

    /**
     * called if someone mined the material from an asteroid
     */
    public void PickedUp(){}

    /**
     * @param args
     * @param fc
     * @throws RuntimeErrorException
     */
    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws RuntimeErrorException {

    }

    /**
     * the save method for the Material class
     * @param os the stream, where the class will be written
     */
    @Override
    public void Save(PrintStream os, boolean CallChildren) {
        os.println("UID: " + GetUID());
    }
}
