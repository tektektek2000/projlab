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
    public void SetBase(Base b){
        Skeleton.AddAndPrintCallStack("Asteroid.SetBase()");
        base=b;
        Skeleton.RemoveFromCallStack("Asteroid.SetBase()");
    }

    // getter for base
    public Base GetBase(){return base;}

    // setter for core
    public boolean SetCore(Material m){
        Skeleton.AddAndPrintCallStack("Asteroid.SetCore()");
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
        Skeleton.RemoveFromCallStack("Asteroid.SetCore()");
        return false;
    }

    // removes ship from the list
    public void Remove(Ship s){
        Skeleton.AddAndPrintCallStack("Asteroid.Remove()");
        ships.remove(s);
        Skeleton.RemoveFromCallStack("Asteroid.Remove()");
    }

    // adds ship to the list
    public void Add(Ship s){
        Skeleton.AddAndPrintCallStack("Asteroid.Add()");
        ships.add(s);
        Skeleton.RemoveFromCallStack("Asteroid.Add()");
    }

    // getting drilled by a ship
    public void GetDrilled(){
        Skeleton.AddAndPrintCallStack("Asteroid.GetDrilled()");

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
            if (Skeleton.AskPlayer("Is the asteroid close to the sun?")) {
                core.DrilledThroughSunClose(this);
            }
        }
        else{
            System.out.println("Drilling successful, the asteroid shell is reduced.");
        }
        Skeleton.RemoveFromCallStack("Asteroid.GetDrilled()");
    }

    // getting mined by a ship
    public Material GetMined(){
        Skeleton.AddAndPrintCallStack("Asteroid.GetMined()");

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
        Skeleton.RemoveFromCallStack("Asteroid.GetMined()");
        return core;
    }

    // ship moves to the asteroid
    public Asteroid MovedTo(Ship s){
        Skeleton.AddAndPrintCallStack("Asteroid.MovedTo()");
        Add(s);
        Skeleton.RemoveFromCallStack("Asteroid.MovedTo()");
        return this;
    }

    // asteroid explodes
    public void Explode(){
        Skeleton.AddAndPrintCallStack("Asteroid.Explode()");

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
        Skeleton.RemoveFromCallStack("Asteroid.Explode()");
    }

    // evaporates core material
    public void Evaporate(){
        Skeleton.AddAndPrintCallStack("Asteroid.Evaporate()");
        core = null;
        Skeleton.RemoveFromCallStack("Asteroid.Evaporate()");
    }

    @Override
    public  String toString(){
        return "Asteroid";
    }
}
