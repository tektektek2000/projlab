package Model.Materials;
import java.lang.String;
import Model.Asteroid;
import Model.Saveable;

public abstract class Material extends Saveable {

    // checks if the material given as parameter is same type
    public boolean isSameType(Material m){
        return m.GetTypeUnique().equals(this.GetTypeUnique());
    }

    // gives back type in string
    protected abstract String GetTypeUnique();

    // special action if the sun is close
    public void DrilledThroughSunClose(Asteroid asteroid){}
}
