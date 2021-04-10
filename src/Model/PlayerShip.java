package Model;

import Model.Materials.BillCreator;
import Model.Materials.BillOfMaterial;
import Model.Materials.Material;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.StringJoiner;

public class PlayerShip extends Ship {

    private ArrayList<Material> materials = new ArrayList<>();
    private ArrayList<TeleportGate> teleports = new ArrayList<>();


    PlayerShip(Asteroid start){
        materials = new ArrayList<>();
        teleports = new ArrayList<>();
        asteroid = start;
        start.Add(this);
    }

    // mines asteroid's core material
    public void Mine(){
        Skeleton.AddAndPrintCallStack("PlayerShip.Mine()");
        int ans;
        ans = Skeleton.AskPlayerForInt("How many materials do you have? [num]");
        // only mines if player ship has 9 material or less
        if(ans < 10) {
            Material core;
            System.out.println("asteroid.GetMined()");
            core = asteroid.GetMined();
            // only adds if asteroid is not empty
            if(core != null){
                Add(core);
            }
        } else System.out.println("You have too many materials.");
        Skeleton.RemoveFromCallStack("PlayerShip.Mine()");
    }

    // removes material from the inventory
    public void Remove(BillOfMaterial b){
        Skeleton.AddAndPrintCallStack("PlayerShip.RemoveBill()");
        ArrayList<Material> removable;
        removable = b.GetMaterials();
        for(Material m : removable){
            Remove(m);
        }
        Skeleton.RemoveFromCallStack("PlayerShip.RemoveBill()");
    }

    // crafts teleport gate pair
    public void CraftTeleportGates(){
        Skeleton.AddAndPrintCallStack("PlayerShip.CraftTeleportGates()");
        int ans;
        ans = Skeleton.AskPlayerForInt("How many teleports do you have? [num]");
        if(ans > 0){
            System.out.println("You have too many teleports.");
        }
        else {
            BillCreator bc = BillCreator.GetInstance();
            BillOfMaterial bill = bc.CreateForTeleport(materials);
            // checks whether player ship has enough material to craft
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
        Skeleton.RemoveFromCallStack("PlayerShip.CraftTeleportGates()");
    }

    // crafts robot
    public void CraftRobot(){
        Skeleton.AddAndPrintCallStack("PlayerShip.CraftRobot()");
        BillCreator bc = BillCreator.GetInstance();
        BillOfMaterial bill = bc.CreateForRobot(materials);
        // checks whether player ship has enough material to craft
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
        Skeleton.RemoveFromCallStack("PlayerShip.CraftRobot()");
    }

    // crafts base
    public void CraftBase(){
        Skeleton.AddAndPrintCallStack("PlayerShip.CraftBase()");
        boolean yes;
        yes = Skeleton.AskPlayer("Is there a base on your current asteroid? [Y/N]");
        // checks whether player ship has enough material to craft
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
        Skeleton.RemoveFromCallStack("PlayerShip.CraftBase()");
    }

    // puts back material to the core
    public void PutBack(Material m){
        Skeleton.AddAndPrintCallStack("PlayerShip.PutBack()");
        if(asteroid.SetCore(m)){
            Remove(m);
        }
        Skeleton.RemoveFromCallStack("PlayerShip.PutBack()");
    }

    // builds base
    public void BuildBase(){
        Skeleton.AddAndPrintCallStack("PlayerShip.BuildBase()");
        boolean yes;
        yes = Skeleton.AskPlayer("Is there a base on your current asteroid?");
        // checks whether is a base already on the asteroid
        if(yes){
            if(asteroid.GetBase() == null){
                Base base = new Base();
                asteroid.SetBase(base);
            }
            int InventoryNum=-1;
            while(InventoryNum < 0 || InventoryNum>10){
                InventoryNum = Skeleton.AskPlayerForInt("How many materials does the player have?");
                if(InventoryNum < 0 || InventoryNum>10){
                    System.out.println("Invalid number");
                }
            }
            for(int i = 0; i < InventoryNum; i++){
                asteroid.GetBase().Accept(Skeleton.AskPlayerForMaterial("What type is the " + (i+1) + ". material?"));
            }
            asteroid.GetBase().CheckComplete();
        }
        else{
            System.out.println("You can't build a non-existing base.");
        }
        Skeleton.RemoveFromCallStack("PlayerShip.BuildBase()");
    }

    // puts down the teleport gate
    public void PutDown(TeleportGate t){
        Skeleton.AddAndPrintCallStack("PlayerShip.PutDown()");
        boolean yes;
        yes = Skeleton.AskPlayer("Do you have any teleports? ");
        // checks whether is player ship has teleport
        if(yes){
            Remove(t);
            t.AddNeighbour(asteroid);
            asteroid.AddNeighbour(t);
            System.out.println("Teleport placed successfully.");
        }
        Skeleton.RemoveFromCallStack("PlayerShip.PutDown()");
    }

    // player ship dies
    public void Die(){
        Skeleton.AddAndPrintCallStack("PlayerShip.Die()");
        // removes all materials
        for(Material m : materials){
            Remove(m);
        }
        // removes all teleports
        for(TeleportGate t : teleports){
            Remove(t);
        }
        asteroid.Remove(this);
        Sun.GetInstance().RemoveAffected(this);
        System.out.println("Player died.");
        Skeleton.RemoveFromCallStack("PlayerShip.Die()");
    }

    // in case of asteroid exploding player ship dies
    public void AsteroidExploding(){
        Skeleton.AddAndPrintCallStack("PlayerShip.AsteroidExploding()");
        Die();
        Skeleton.RemoveFromCallStack("PlayerShip.AsteroidExploding()");
    }

    // adds material
    public void Add(Material m){
        Skeleton.AddAndPrintCallStack("PlayerShip.Add()");
        materials.add(m);
        System.out.println("Added " + m + " to the players inventory.");
        Skeleton.RemoveFromCallStack("PlayerShip.Add()");
    }

    // removes material
    public void Remove(Material m){
        Skeleton.AddAndPrintCallStack("PlayerShip.Remove()");
        materials.remove(m);
        Skeleton.RemoveFromCallStack("PlayerShip.Remove()");
    }

    // adds teleport gate
    public void Add(TeleportGate t){
        Skeleton.AddAndPrintCallStack("PlayerShip.Add()");
        teleports.add(t);
        Skeleton.RemoveFromCallStack("PlayerShip.Add()");
    }

    // removes teleport gate
    public void Remove(TeleportGate t){
        Skeleton.AddAndPrintCallStack("PlayerShip.Remove()");
        teleports.remove(t);
        Skeleton.RemoveFromCallStack("PlayerShip.Remove()");
    }

    @Override
    public String toString(){
        return "PlayerShip";
    }
}
