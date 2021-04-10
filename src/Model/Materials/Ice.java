package Model.Materials;
import Model.Asteroid;
import Model.Skeleton;

import java.lang.String;

public class Ice extends Material {

    // gives back type in string
    protected  String GetTypeUnique(){
        return "Ice";
    }

    // special action if the sun is close
    // in Ice case it is evaporating
    @Override
    public void DrilledThroughSunClose(Asteroid asteroid){
        Skeleton.AddAndPrintCallStack("Ice.DrilledThroughSunClose()");
        asteroid.Evaporate();
        Skeleton.RemoveFromCallStack("Ice.DrilledThroughSunClose()");
    }

    @Override
    public String toString(){
        return "Ice";
    }
}
