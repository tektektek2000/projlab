package Controllers;

import Utils.Pair;

import java.util.ArrayList;

/**
 * A class for handling the notifications.
 */
public class NotificationManager {
    /**
     * List of messages.
     */
    static ArrayList<String> messages = new ArrayList<>();
    /**
     * List of errors.
     */
    static ArrayList<String> errors = new ArrayList<>();
    /**
     * True, if the last command ran successful, false if not.
     */
    static boolean LastCommandSuccess = true;
    /**
     * True, if the game is over, false if not.
     */
    static boolean GameOver = false;

    // Setter for LastCommandSuccess
    public static void setLastCommandSuccess(boolean val){
        LastCommandSuccess = val;
    }
    // Getter for LastCommandSuccess
    public static boolean getLastCommandSuccess(){
        return LastCommandSuccess;
    }

    /**
     * Adds a message to the list.
     * @param m The message we want to add.
     */
    public static void AddMessage(String m){
        messages.add(m);
    }

    /**
     * Gets a message and removes it from the list.
     * @return The first message from the list.
     */
    public static String getMessage(){
        String ret = null;
        if(messages.size() != 0)
            ret = messages.get(0);
        if(ret != null){
            messages.remove(ret);
        }
        return ret;
    }
    /**
     * Adds an error to the list.
     * @param m The error we want to add.
     */
    public static void AddError(String m){
        errors.add(m);
    }

    /**
     * Gets a error and removes it from the list.
     * @return The first error from the list.
     */
    public static String getError(){
        String ret = null;
        if(errors.size() != 0)
            ret = errors.get(0);
        if(ret != null){
            errors.remove(ret);
        }
        return ret;
    }

    /**
     * Called when players won, notifies them.
     */
    public static void PlayersWon(){
        GameOver = true;
        AddMessage("Players have successfully built the base and won.");
    }

    /**
     * Called when players lost, notifies them.
     */
    public static void PlayersLost(){
        GameOver = true;
        AddMessage("All players have died and so the game is lost.");
    }
}
