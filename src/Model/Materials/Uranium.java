package Model.Materials;
import Model.Asteroid;

import java.lang.String;
public class Uranium extends Material{

    // gives back type in string
    protected String GetTypeUnique(){
        return "Uranium";
    }

    // special action if the sun is close
    // in Uranium case it is exploding
    @Override
    public void DrilledThroughSunClose(Asteroid asteroid){
        asteroid.Explode();
    }
    @Override
    public String toString(){
        return "Uranium";
    }
}
