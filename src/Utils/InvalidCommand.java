package Utils;

import javax.sound.sampled.Line;
import java.util.ArrayList;

public class InvalidCommand extends Exception{
    String line;
    public  InvalidCommand(String Line){line = Line;}

    public  InvalidCommand(String Line, ArrayList<String> Args,String ErrorMsg){
        line = Line;
        for(String it : Args){
            line += " " + it;
        }
        line += ErrorMsg;
    }

    @Override
    public String getMessage(){
        return "An invalid command was entered: " + line;
    }
}
