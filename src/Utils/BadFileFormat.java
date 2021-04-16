package Utils;

public class BadFileFormat extends Exception{
    String Why;
    String Line;

    public BadFileFormat(String line, String why){Line=line;Why=why;}

    @Override
    public String getMessage(){
        return "Save File format is not acceptable: " + Line + " -> " + Why;
    }
}
