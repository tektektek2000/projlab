package Model;

import Controllers.FileController;
import Model.Materials.BillCreator;
import Model.Materials.BillOfMaterial;
import Model.Materials.Material;
import Utils.LinkerException;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.util.ArrayList;

public class PlayerShip extends Ship {
    private ArrayList<Material> materials = new ArrayList<>();
    private ArrayList<TeleportGate> teleports = new ArrayList<>();


    PlayerShip(){}

    public PlayerShip(int uid){
        super(uid);
        materials = new ArrayList<>();
        teleports = new ArrayList<>();
    }

    PlayerShip(Asteroid start){
        materials = new ArrayList<>();
        teleports = new ArrayList<>();
        asteroid = start;
        start.Add(this);
    }

    public ArrayList<Material> getMaterials(){return materials;}

    public ArrayList<TeleportGate> getTeleports(){return teleports;}

    // mines asteroid's core material
    public void Mine(){
        // only mines if player ship has 9 material or less
        if(materials.size() < 10) {
            Material core;
            core = asteroid.GetMined();
            // only adds if asteroid is not empty
            if(core != null){
                Add(core);
            }
        }
    }

    // removes material from the inventory
    public void Remove(BillOfMaterial b){
        ArrayList<Material> removable;
        removable = b.GetMaterials();
        for(Material m : removable){
            Remove(m);
        }
    }

    // crafts teleport gate pair
    public void CraftTeleportGates(){
        if(teleports.size() > 1) {
            BillCreator bc = BillCreator.GetInstance();
            BillOfMaterial bill = bc.CreateForTeleport(materials);
            // checks whether player ship has enough material to craft
            if(bill != null)  {
                Remove(bill);
                TeleportGate t1 = new TeleportGate(null);
                TeleportGate t2 = new TeleportGate(null);
                t1.pair(t2);
                Add(t1);
                Add(t2);
            }
        }
    }

    // crafts robot
    public void CraftRobot(){
        BillCreator bc = BillCreator.GetInstance();
        BillOfMaterial bill = bc.CreateForRobot(materials);
        // checks whether player ship has enough material to craft
        if(bill != null){
            Remove(bill);
            RobotShip rs = new RobotShip();
            asteroid.Add(rs);
        }
    }

    // crafts base
    public void CraftBase(){
        if(asteroid.GetBase() == null){
            BillCreator bc = BillCreator.GetInstance();
            BillOfMaterial bill = bc.CreateForBaseFoundation(materials);
            if(bill != null) {
                Remove(bill);
                Base newbase = new Base(Map.GetNewUID());
                for(Material m : bill.GetMaterials()){
                    newbase.Accept(m);
                }
                asteroid.SetBase(newbase);
            }
        }
    }

    // puts back material to the core
    public void PutBack(Material m){
        if(asteroid.SetCore(m)){
            Remove(m);
        }
    }

    // builds base
    public void BuildBase(){
        // checks whether is a base already on the asteroid
        if(asteroid.GetBase() != null){
            if(asteroid.GetBase() == null){
                Base base = new Base(Map.GetNewUID());
                asteroid.SetBase(base);
            }
            for(Material it : materials)
                asteroid.GetBase().Accept(it);
            asteroid.GetBase().CheckComplete();
        }
    }

    // puts down the teleport gate
    public void PutDown(TeleportGate t){
        // checks whether is player ship has teleport
        if(teleports.size()>0){
            Remove(t);
            t.AddNeighbour(asteroid);
            t.SetSector(asteroid.sector);
            asteroid.AddNeighbour(t);
        }
    }

    // player ship dies
    public void Die(){
        // removes all materials
        for(Material m : materials){
            Remove(m);
        }
        // removes all teleports
        for(TeleportGate t : teleports){
            Remove(t);
        }
        asteroid.Remove(this);
    }

    // in case of asteroid exploding player ship dies
    public void AsteroidExploding(){
        Die();
    }

    // adds material
    public void Add(Material m){
        materials.add(m);
    }

    // removes material
    public void Remove(Material m){
        materials.remove(m);
    }

    // adds teleport gate
    public void Add(TeleportGate t){
        teleports.add(t);
    }

    // removes teleport gate
    public void Remove(TeleportGate t){
        teleports.remove(t);
    }

    @Override
    public String toString(){
        return "PlayerShip";
    }

    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws LinkerException {
        super.Link(args,fc);
        for(StringPair it : args) {
            if(it.first.equals("Materials")){
                String[] ids = it.second.split(",");
                for (String idIt : ids) {
                    materials.add((Material) fc.GetWithUID(Integer.getInteger(idIt)));
                }
            }
            else if(it.first.equals("Teleports")){
                String[] ids = it.second.split(",");
                for (String idIt : ids) {
                    teleports.add((TeleportGate) fc.GetWithUID(Integer.getInteger(idIt)));
                }
            }
        }
    }

    @Override
    public void Save(PrintStream os) {
        os.println("PlayerShip{");
        super.Save(os);
        if(materials.size()>0) {
            os.print("Materials: ");
            for (Material it : materials) {
                os.print(it.GetUID());
                if (it != materials.get(materials.size() - 1)) {
                    os.print(",");
                } else {
                    os.println();
                }
            }
        }
        if(teleports.size()>0) {
            os.print("Teleports: ");
            for (TeleportGate it : teleports) {
                os.print(it.GetUID());
                if (it != teleports.get(teleports.size() - 1)) {
                    os.print(",");
                } else {
                    os.println();
                }
            }
        }
        os.println("}");
        for(TeleportGate s : teleports){
            s.Save(os);
        }
        for(Material s : materials){
            s.Save(os);
        }
    }

}
