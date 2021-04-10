package Model.Materials;
import java.lang.String;
public class Iron extends Material {

    // gives back type in string
    protected  String GetTypeUnique(){
        return "Iron";
    }

    @Override
    public String toString(){
        return "Iron";
    }
}
