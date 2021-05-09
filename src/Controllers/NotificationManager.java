package Controllers;

import Model.Asteroid;
import Model.TeleportGate;
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
     * List of warnings.
     */
    static ArrayList<String> warnings = new ArrayList<>();
    /**
     * List of Recently exploded asteroids.
     */
    static ArrayList<Asteroid> ExplodedAsteroids = new ArrayList<>();
    /**
     * List of Recently exploded asteroids.
     */
    static ArrayList<TeleportGate> NewTeleports = new ArrayList<>();
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
     * Adds an warning to the list.
     * @param m The warning we want to add.
     */
    public static void AddWarning(String m){
        warnings.add(m);
    }

    /**
     * Gets a warning and removes it from the list.
     * @return The first warning from the list.
     */
    public static String getWarning(){
        String ret = null;
        if(warnings.size() != 0)
            ret = warnings.get(0);
        if(ret != null){
            warnings.remove(ret);
        }
        return ret;
    }

    /**
     * Adds an asteroid to the list.
     * @param a The asteroid we want to add.
     */
    public static void AddExplodedAsteroid(Asteroid a){
        ExplodedAsteroids.add(a);
    }

    /**
     * Gets an asteroid and removes it from the list.
     * @return The first Asteroid from the list.
     */
    public static Asteroid getExplodedAsteroid(){
        Asteroid ret = null;
        if(ExplodedAsteroids.size() != 0)
            ret = ExplodedAsteroids.get(0);
        if(ret != null){
            ExplodedAsteroids.remove(ret);
        }
        return ret;
    }

    /**
     * Adds an asteroid to the list.
     * @param a The asteroid we want to add.
     */
    public static void AddNewTeleport(TeleportGate a){
        NewTeleports.add(a);
    }

    /**
     * Gets an asteroid and removes it from the list.
     * @return The first Asteroid from the list.
     */
    public static TeleportGate getNewTeleport(){
        TeleportGate ret = null;
        if(NewTeleports.size() != 0)
            ret = NewTeleports.get(0);
        if(ret != null){
            NewTeleports.remove(ret);
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
