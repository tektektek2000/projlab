package Model;

import Controllers.FileController;
import Controllers.NotificationManager;
import Model.Materials.Material;
import Utils.LinkerException;
import Utils.StringPair;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Asteroid extends Field {
    private ArrayList<Ship> ships;
    private ArrayList<Ship> Removables;
    private Material core;
    private Base base;
    private int shell;
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

    public void SetShell(int Shell){
        shell = Shell;
    }

    public int GetShell(){
        return shell;
    }

    // setter for base
    public void SetBase(Base b){
        base=b;
    }

    // getter for base
    public Base GetBase(){return base;}

    // setter for core
    public boolean SetCore(Material m){
        if(shell != 0){
            NotificationManager.setLastCommandSuccess(false);
            NotificationManager.AddError("Asteroid is not drilled through, can't put back material.");
        }
        else if(core != null){
            NotificationManager.setLastCommandSuccess(false);
            NotificationManager.AddError("Asteroid is empty, can't put back material.");
        }
        if(shell == 0 && core == null){
            core = m;
            if(sector.getSunClose())
                core.DrilledThroughSunClose(this);
            return true;
        }
        return false;
    }

    // getter for core
    public Material GetCore(){
        return core;
    }

    // removes ship from the list
    public void Remove(Ship s){
        if(!SunStorm)
            ships.remove(s);
        else
            Removables.add(s);
    }

    // adds ship to the list
    public void Add(Ship s){
        ships.add(s);
    }

    // getting drilled by a ship
    public boolean GetDrilled(){
        if(shell<=0){
            return false;
        }
        shell--;
        // if shell size is zero then checks whether it is in sun close area
        if(shell==0){
            if (sector.getSunClose()) {
                core.DrilledThroughSunClose(this);
            }
        }
        return true;
    }

    // getting mined by a ship
    public Material GetMined(){
        // if shell size is not zero cannot get mined
        if(shell > 0){
            NotificationManager.setLastCommandSuccess(false);
            NotificationManager.AddError("Asteroid shell isn't drilled through, can't mine");
            return null;
        }
        if(core == null){
            NotificationManager.setLastCommandSuccess(false);
            NotificationManager.AddError("Asteroid is empty, can't mine");
            return null;
        }
        Material ret = core;
        core = null;
        ret.PickedUp();
        return ret;
    }

    // ship moves to the asteroid
    public Asteroid MovedTo(){
        return this;
    }

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

    // asteroid explodes
    public void Explode(){
        NotificationManager.AddMessage("Asteroid" + GetUID() + " exploded.");
        // calls for each ship exploding action
        for (int i=0;i<ships.size();i++) {
            for (Ship ship : ships) {
                ship.AsteroidExploding();
                break;
            }
        }
        // removes asteroid from all neighbours
        for(Field f : Neighbours){
            f.RemoveNeighbour(this);
        }
        sector.Remove(this);
        Neighbours.clear();
    }

    // evaporates core material
    public void Evaporate(){
        core = null;
    }

    @Override
    public  String toString(){
        return "Asteroid";
    }


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
