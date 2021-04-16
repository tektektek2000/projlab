package Utils;

public class InvalidCommand extends Exception{

    @Override
    public String getMessage(){
        return "An invalid command was entered";
    }
}
