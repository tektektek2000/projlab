package Model;

import Controllers.FileController;
import Utils.LinkerException;
import Utils.StringPair;

import java.io.PrintStream;
import java.util.ArrayList;

public abstract class Ship extends Saveable {
    protected Asteroid asteroid;

    public Ship(Asteroid a){
        super(a.sector.map);
        asteroid = a;
    }

    public Ship(int uid) {
        super(uid);
    }

    /**
     * moves the ship to the given field
     * @param f
     */
    public void Move(Field f){
        asteroid.Remove(this);
        Asteroid dest = f.MovedTo();
        dest.Add(this);
        setAsteroid(dest);
    }

    /**
     * ship dies
     */
    public abstract void Die();

    /**
     * hides ship
     */
    public void Hide(){

    }

    /**
     *  ship gets sun stormed
     */
    public void SunStormNow(){
        // whether ship can hide in empty asteroid or dies
        if(asteroid.GetShell() == 0){
            if(asteroid.GetCore() == null){
                Hide();
            }
            else{
                Die();
            }
        }
        else{
            Die();
        }
    }

    /**
     *  special action if the asteroid explodes
     */
    public abstract void AsteroidExploding();

    /**
     * drills on the asteroid
     */
    public void Drill(){
        asteroid.GetDrilled();
    }

    /**
     * gives back asteroid
     * @return
     */

    public Asteroid getAsteroid(){
        return asteroid;
    }

    /**
     * sets asteroid
     * @param a
     */
    public void setAsteroid(Asteroid a){
        asteroid = a;
    }

    /**
     * @param args
     * @param fc
     * @throws LinkerException
     */
    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws LinkerException {
        for(StringPair it : args) {
            if(it.first.equals("Asteroid")){
                asteroid = (Asteroid) fc.GetWithUID(Integer.parseInt(it.second));
            }
        }
    }

    /**
     * @param os the stream, where the class will be written
     * @param CallChildren
     */
    @Override
    public void Save(PrintStream os, boolean CallChildren) {
        os.println("UID: " + GetUID());
        if(asteroid!=null) {
            os.println("Asteroid: " + asteroid.GetUID());
        }
    }
}
