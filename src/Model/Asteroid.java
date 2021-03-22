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

    public void SetBase(Base b){base=b;}

    public Base GetBase(){return base;}

    public boolean SetCore(Material m){
        if(Skeleton.AskPlayerForInt("How thick is the shell of the Asteroid?")>0){
            System.out.println("You can't put the material back, because the asteroid is not drilled through.");
        }
        else{
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

    public void Remove(Ship s){
        ships.remove(s);
    }
    public void Add(Ship s){
        ships.add(s);
    }
    public void GetDrilled(){
        int shell = Skeleton.AskPlayerForInt("How thick is the shell of the Asteroid?");
        if(shell<=0){
            System.out.println("Drilling unsuccessful, the asteroid is already drilled through.");
            return;
        }
        shell--;
        if(shell==0) {
            System.out.println("Asteroid is drilled through.");
            if (Skeleton.AskPlayer("Is the asteroid close to the sun?")) {
                core.DrilledThroughSunClose(this);
            }
        }
        else{
            System.out.println("Drilling successful, the asteroid shell is reduced.");
        }

    }
    public Material GetMined(){
        int shell = Skeleton.AskPlayerForInt("How thick is the shell of the Asteroid?");
        if(shell>0){
            System.out.println("Can't mine asteroid, because it is not drilled through.");
            return null;
        }
        if(core == null){
            System.out.println("Asteroid is empty.");
        }
        return core;
    }
    public Asteroid MovedTo(Ship s){
        ships.add(s);
        return this;
    }
    public void Explode(){
        System.out.println("Asteroid exploded.");
        for (Ship ship : ships) {
            ship.AsteroidExploding();
        }
        for(Field f : Neighbours){
            f.RemoveNeighbour(this);
        }
    }
    public void Evaporate(){
        System.out.println("Ice inside the core evaporated.");
        core = null;
    }
}
