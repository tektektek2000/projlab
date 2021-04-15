package Utils;

public class BadFileFormat extends Exception{

    @Override
    public String getMessage(){
        return "Save File format is not acceptable";
    }
}
