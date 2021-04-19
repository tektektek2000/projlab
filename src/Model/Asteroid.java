package Model;

import Controllers.FileController;
import Model.Materials.Material;
import Utils.LinkerException;
import Utils.StringPair;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Represents the asteroid in the game, the main field.
 */
public class Asteroid extends Field {
    /**
     * The ships on the asteroid.
     */
    private ArrayList<Ship> ships;
    /**
     * itt bizony feketemágia történik
     */
    private ArrayList<Ship> Removables;
    /**
     * The material in the asteroid.
     */
    private Material core;
    /**
     * The base on the asteroid, if there is no base, it's null.
     */
    private Base base;
    /**
     * The shell of the asteroid, ships can only mine if it's 0.
     */
    private int shell;
    /**
     * itt bizony feketemágia történik.
     */
    private boolean SunStorm = false;

    public Asteroid(Sector s){
        super(s);
        ships = new ArrayList<>();
        Removables = new ArrayList<>();
        base=null;
        shell = new Random().nextInt(6);
    }
    public Asteroid(Sector s,int Shell){
        super(s);
        ships = new ArrayList<>();
        Removables = new ArrayList<>();
        base=null;
        shell = Shell;
    }
    public Asteroid(Sector s,Material Core,int Shell){
        super(s);
        ships = new ArrayList<>();
        Removables = new ArrayList<>();
        core = Core;
        base=null;
        shell = Shell;
    }
    public Asteroid(int UID, Sector s, Material _core, int _shell) {
        super(UID, s);
        ships = new ArrayList<>();
        Removables = new ArrayList<>();
        core = _core;
        base = null;
        shell = _shell;
    }
    public Asteroid(int UID) {
        super(UID);
        ships = new ArrayList<>();
        Removables = new ArrayList<>();
        base=null;
        shell = new Random().nextInt(6);
    }

    /**
     * The setter of the shell.
     * @param Shell The value we want to set for the asteroid's shell.
     */
    public void SetShell(int Shell){
        shell = Shell;
    }

    /**
     * The getter of the shell.
     * @return With the value of the shell.
     */
    public int GetShell(){
        return shell;
    }

    /**
     * The setter of the base.
     * @param b The base we want to set for the asteroid's base.
     */
    public void SetBase(Base b){
        base=b;
    }

    /**
     * The getter of the base.
     * @return With the base on the asteroid.
     */
    public Base GetBase(){return base;}

    /**
     * The setter of the core.
     * @param m The material we want to set for the asteroid's core.
     * @return True, if the set was successful and false if not.
     */
    public boolean SetCore(Material m){
        if(shell == 0 && core == null){
            core = m;
            if(sector.getSunClose()){
                core.DrilledThroughSunClose(this);
            }
            return true;
        }
        return false;
    }

    /**
     * The getter of the core.
     * @return With the asteroid's core.
     */
    public Material GetCore(){
        return core;
    }

    /**
     * itt bizony feketemágia történik.
     * @param s
     */
    public void Remove(Ship s){
        if(!SunStorm)
            ships.remove(s);
        else
            Removables.add(s);
    }

    /**
     * Adds a ship to the asteroid.
     * @param s The ship we want to be added to the asteroid.
     */
    public void Add(Ship s){
        ships.add(s);
    }

    /**
     * The method which handles if a ship drills the asteroid.
     */
    public void GetDrilled(){
        if(shell<=0){
            return;
        }
        shell--;
        // if shell size is zero then checks whether it is in sun close area
        if(shell==0){
            if (sector.getSunClose()) {
                core.DrilledThroughSunClose(this);
            }
        }
    }

    /**
     * The method which handles if a ship mines on the asteroid.
     * @return With the core if the mining was successful and false if not.
     */
    public Material GetMined(){
        // if shell size is not zero cannot get mined
        if(shell>0 || core == null){
            return null;
        }
        Material ret = core;
        core = null;
        ret.PickedUp();
        return ret;
    }

    /**
     * Called when a ship moves to the asteroid.
     * @return With the asteroid.
     */
    public Asteroid MovedTo(){
        return this;
    }

    /**
     * The asteroid reacts to a sunstorm.
     */
    @Override
    public void SunStorm() {
        SunStorm = true;
        for(Ship it : ships){
            it.SunStormNow();
        }
        for(Ship it : Removables){
            ships.remove(it);
        }
        SunStorm = false;
    }

    /**
     * The asteroid explodes.
     */
    public void Explode(){
        // Calls for each ship exploding action.
        for (int i=0;i<ships.size();i++) {
            for (Ship ship : ships) {
                ship.AsteroidExploding();
                break;
            }
        }
        // Removes asteroid from all neighbours.
        for(Field f : Neighbours){
            f.RemoveNeighbour(this);
        }
        sector.Remove(this);
        Neighbours.clear();
    }

    /**
     * Evaporates the core material.
     */
    public void Evaporate(){
        core = null;
    }

    /**
     * @param args
     * @param fc
     * @throws LinkerException
     */
    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws LinkerException {
        super.Link(args,fc);
        for(StringPair it : args) {
            if(it.first.equals("Ships")){
                String[] ids = it.second.split(",");
                for(String idIt : ids){
                    ships.add((Ship) fc.GetWithUID(Integer.parseInt(idIt)));
                }
            }
            else if(it.first.equals("Core")){
                core = (Material) fc.GetWithUID(Integer.parseInt(it.second));
            }
            else if(it.first.equals("Shell")){
                shell = Integer.parseInt(it.second);
            }
            else if(it.first.equals("Base")){
                base = (Base) fc.GetWithUID(Integer.parseInt(it.second));
            }
        }
    }

    /**
     * The save method for the Asteroid class.
     * @param os The stream, where the class will be written.
     * @param CallChildren
     */
    @Override
    public void Save(PrintStream os, boolean CallChildren) {
        os.println("Asteroid{");
        super.Save(os, CallChildren);
        os.println("Shell: " + shell);
        if(ships.size()>0) {
            ships.sort(new Comparator<Ship>() {
                @Override
                public int compare(Ship o1, Ship o2) {
                    return o1.GetUID()-o2.GetUID();
                }
            });
            os.print("Ships: ");
            for (Ship it : ships) {
                os.print(it.GetUID());
                if (it != ships.get(ships.size() - 1)) {
                    os.print(",");
                } else {
                    os.println();
                }
            }
        }
        if(base!=null)
            os.println("Base:" + base.GetUID());
        if(core!=null)
            os.println("Core:" + core.GetUID());
        os.println("}");
        if(CallChildren) {
            if (core != null)
                core.Save(os, CallChildren);
            if (base != null)
                base.Save(os, CallChildren);
            for (Ship s : ships) {
                s.Save(os, CallChildren);
            }
        }
    }
}
