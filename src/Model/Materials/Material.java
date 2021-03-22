package Model.Materials;
import java.lang.String;
import Model.Asteroid;
public abstract class Material {

    // checks if the material given as parameter is same type
    public boolean isSameType(Material m){
        return m.GetTypeUnique().equals(this.GetTypeUnique());
    }

    // gives back type in string
    protected abstract String GetTypeUnique();

    // special action if the sun is close
    public void DrilledThroughSunClose(Asteroid asteroid){}
}
