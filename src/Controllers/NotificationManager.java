package Controllers;

import Utils.Pair;

import java.util.ArrayList;

public class NotificationManager {
    static ArrayList<String> messages = new ArrayList<>();
    static ArrayList<String> errors = new ArrayList<>();
    static boolean LastCommandSuccess = true;
    static boolean GameOver = false;

    public static void setLastCommandSuccess(boolean val){
        LastCommandSuccess = val;
    }

    public static boolean getLastCommandSuccess(){
        return LastCommandSuccess;
    }

    public static void AddMessage(String m){
        messages.add(m);
    }

    public static String getMessage(){
        String ret = null;
        if(messages.size() != 0)
            ret = messages.get(0);
        if(ret != null){
            messages.remove(ret);
        }
        return ret;
    }

    public static void AddError(String m){
        errors.add(m);
    }

    public static String getError(){
        String ret = null;
        if(errors.size() != 0)
            ret = errors.get(0);
        if(ret != null){
            errors.remove(ret);
        }
        return ret;
    }

    public static void PlayersWon(){
        GameOver = true;
        AddMessage("Players have successfully built the base and won.");
    }

    public static void PlayersLost(){
        GameOver = true;
        AddMessage("All players have died and so the game is lost.");
    }
}
