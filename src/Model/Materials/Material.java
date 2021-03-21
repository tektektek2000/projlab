package Model.Materials;
import java.lang.String;
import Model.Asteroid;
public abstract class Material {
    public boolean isSameType(Material m){
        return m.GetTypeUnique().equals(this.GetTypeUnique());
    }
    protected abstract String GetTypeUnique();
    public abstract void DrilledThroughSunClose(Asteroid asteroid);
}
