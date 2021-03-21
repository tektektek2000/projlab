package Model.Materials;
import java.lang.String;

public class Ice extends Material {
    protected  String GetTypeUnique(){
        return "Ice";
    }
    @Override
    public void DrilledThroughSunClose(Asteroid asteroid){
        asteroid.Evaporate();
    }
}
