package Model.Materials;
import java.lang.String;
public class Uranium extends Material{
    protected String GetTypeUnique(){
        return "Uranium";
    }
    public void DrilledThroughSunClose(Asteroid asteroid){
        asteroid.Explode();
    }
}
