package Model;

import Model.Materials.BillCreator;
import Model.Materials.BillOfMaterial;
import Model.Materials.Material;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.StringJoiner;

public class PlayerShip extends Ship {
    private ArrayList<Material> materials;
    private ArrayList<TeleportGate> teleports;

    public void Mine(){
        int ans;
        ans = Skeleton.AskPlayerForInt("How many materials do you have? [num]");
        if(ans < 10) {
            Material core;
            System.out.println("asteroid.GetMined()");
            core = asteroid.GetMined();
            if(core != null){
                Add(core);
            }
        } else System.out.println("You have too many materials.");
    }

    public void Remove(BillOfMaterial b){
        ArrayList<Material> removable;
        removable = b.GetMaterials();
        for(Material m : removable){
            Remove(m);
        }
    }
    public void CraftTeleportGates(){
        int ans;
        ans = Skeleton.AskPlayerForInt("How many teleports do you have? [num]");
        if(ans > 0){
            System.out.println("You have too many teleports.");
        }
        else {
            BillCreator bc = BillCreator.GetInstance();
            BillOfMaterial bill = bc.CreateForTeleport(materials);
            if(bill == null) {
                System.out.println("You don't have enough materials.");
            }
            else {
                System.out.println("\tTeleports created and added.");
                Remove(bill);
                TeleportGate t1 = new TeleportGate();
                TeleportGate t2 = new TeleportGate();
                t1.pair(t2);
                Add(t1);
                Add(t2);
            }
        }
    }
    public void CraftRobot(){
        BillCreator bc = BillCreator.GetInstance();
        BillOfMaterial bill = bc.CreateForRobot(materials);
        if(bill == null) {
            System.out.println("You don't have enough materials.");
        }
        else{
            System.out.println("Robot crafted and placed.");
            Remove(bill);
            RobotShip rs = new RobotShip();
            asteroid.Add(rs);
            Sun.GetInstance().AddAffected(rs);
        }
    }

    public void CraftBase(){
        boolean yes;
        yes = Skeleton.AskPlayer("Is there a base on your current asteroid? [Y/N]");
        if(yes){
            System.out.println("You can't build a base here!");
        }
        else{
            BillCreator bc = BillCreator.GetInstance();
            BillOfMaterial bill = bc.CreateForBaseFoundation(materials);
            if(bill == null) {
                System.out.println("You don't have enough materials.");
            }
            else{
                System.out.println("Base foundation crafted and placed.");
                Remove(bill);
                Base newbase = new Base();
                for(Material m : bill.GetMaterials()){
                    newbase.Accept(m);
                }
                asteroid.SetBase(newbase);
            }
        }
    }

    public void PutBack(Material m){
        if(asteroid.SetCore(m)){
            Remove(m);
        }
    }

    public void BuildBase(){
        boolean yes;
        yes = Skeleton.AskPlayer("Is there a base on your current asteroid? [Y/N]");
        if(yes){
            System.out.println("Base built with needed materials..");
            if(asteroid.GetBase() == null){
                Base base = new Base();
                asteroid.SetBase(base);
            }
            for(Material m : materials){
                if(asteroid.GetBase().Accept(m)){
                    Remove(m);
                }
            }
        }
        else{
            System.out.println("You can't build a non-existing base.");
        }
    }
    public void PutDown(TeleportGate t){
        boolean yes;
        yes = Skeleton.AskPlayer("Do you have any teleports? [Y/N]");
        if(yes){
            System.out.println("Teleport placed successfully.");
            Remove(t);
            t.AddNeighbour(asteroid);
            asteroid.AddNeighbour(t);
        }
    }
    public void Die(){
        for(Material m : materials){
            Remove(m);
        }
        for(TeleportGate t : teleports){
            Remove(t);
        }
        asteroid.Remove(this);
        Sun.GetInstance().RemoveAffected(this);
    }
    public void AsteroidExploding(){
        Die();
    }
    public void Add(Material m){
        materials.add(m);
    }
    public void Remove(Material m){
        materials.remove(m);
    }
    public void Add(TeleportGate t){
        teleports.add(t);
    }
    public void Remove(TeleportGate t){
        teleports.remove(t);
    }
}
