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

    // checks if the material given as parameter is same type
    public boolean isSameType(Material m){
        return m.GetTypeUnique().equals(this.GetTypeUnique());
    }

    // gives back type in string
    protected abstract String GetTypeUnique();

    // special action if the sun is close
    public void DrilledThroughSunClose(Asteroid asteroid){}

    public void PickedUp(){}

    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws RuntimeErrorException {

    }

    @Override
    public void Save(PrintStream os) {
        os.println("UID: " + GetUID());
    }
}
