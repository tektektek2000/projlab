package Controllers;

import Utils.Pair;

import java.util.ArrayList;

public class NotificationManager {
    static ArrayList<String> messages = new ArrayList<>();
    static ArrayList<String> errors = new ArrayList<>();
    static boolean LastCommandSuccess = true;

    public void setLastCommandSuccess(boolean val){
        LastCommandSuccess = val;
    }

    public boolean getLastCommandSuccess(){
        return LastCommandSuccess;
    }

    public static void AddMessage(String m){
        messages.add(m);
    }

    public static String getMessage(){
        String ret = messages.get(0);
        if(ret != null){
            messages.remove(ret);
        }
        return ret;
    }

    public static void AddError(String m){
        errors.add(m);
    }

    public static String getError(){
        String ret = errors.get(0);
        if(ret != null){
            errors.remove(ret);
        }
        return ret;
    }
}
