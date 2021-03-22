package Model;

import Model.Materials.Material;

import java.util.ArrayList;

public class Asteroid extends Field {
    ArrayList<Ship> ships;
    Material core;
    Base base;


    public Asteroid(){
        ships = new ArrayList<>();
        base=null;
    }

    public Asteroid(Material Core){
        ships = new ArrayList<>();
        core = Core;
        base=null;
    }

    // setter for base
    public void SetBase(Base b){base=b;}

    // getter for base
    public Base GetBase(){return base;}

    // setter for core
    public boolean SetCore(Material m){
        // asking for shell size
        if(Skeleton.AskPlayerForInt("How thick is the shell of the Asteroid?")>0){
            System.out.println("You can't put the material back, because the asteroid is not drilled through.");
        }
        else{
            // asking if the asteroid is empty
            if(Skeleton.AskPlayer("Is the asteroid empty? [Y/N]")){
                System.out.println("You put the material back successfully.");
                core = m;
                return true;
            }
            else{
                System.out.println("You can't put the material back, because the asteroid is not empty.");
            }
        }
        return false;
    }

    // removes ship from the list
    public void Remove(Ship s){
        ships.remove(s);
    }

    // adds ship to the list
    public void Add(Ship s){
        ships.add(s);
    }

    // getting drilled by a ship
    public void GetDrilled(){
        // asking for shell size
        int shell = Skeleton.AskPlayerForInt("How thick is the shell of the Asteroid?");
        if(shell<=0){
            System.out.println("Drilling unsuccessful, the asteroid is already drilled through.");
            return;
        }
        shell--;
        // if shell size is zero then checks whether it is in sun close area
        if(shell==0){
            System.out.println("Asteroid is drilled through.");
            if(Skeleton.AskPlayer("Is the asteroid close to the sun?")){
                core.DrilledThroughSunClose(this);
            }
        }
        else{
            System.out.println("Drilling successful, the asteroid shell is reduced.");
        }

    }

    // getting mined by a ship
    public Material GetMined(){
        // asking for shell size
        int shell = Skeleton.AskPlayerForInt("How thick is the shell of the Asteroid?");
        // if shell size is not zero cannot get mined
        if(shell>0){
            System.out.println("Can't mine asteroid, because it is not drilled through.");
            return null;
        }
        // if core is null then asteroid has note material
        if(core == null){
            System.out.println("Asteroid is empty.");
        }
        return core;
    }

    // ship moves to the asteroid
    public Asteroid MovedTo(Ship s){
        ships.add(s);
        return this;
    }

    // asteroid explodes
    public void Explode(){
        System.out.println("Asteroid exploded.");
        // calls for each ship exploding action
        for (Ship ship : ships) {
            ship.AsteroidExploding();
        }
        // removes asteroid from all neighbours
        for(Field f : Neighbours){
            f.RemoveNeighbour(this);
        }
    }

    // evaporates core material
    public void Evaporate(){
        System.out.println("Ice inside the core evaporated.");
        core = null;
    }
}
