package Model.Materials;
import java.lang.String;

public abstract class Ice extends Material {
    protected  String GetTypeUnique(){
        return "Ice";
    }
    public void DrilledThroughSunClose(Asteroid asteroid){
        asteroid.Evaporate();
    }
}
