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
import java.util.Comparator;

/**
 * It bases from the Ship class. The player controls the playership with it
 */
public class PlayerShip extends Ship {
    private ArrayList<Material> materials = new ArrayList<>();
    private ArrayList<TeleportGate> teleports = new ArrayList<>();


    public PlayerShip(int uid){
        super(uid);
        materials = new ArrayList<>();
        teleports = new ArrayList<>();
    }

    PlayerShip(Asteroid start){
        super(start);
        materials = new ArrayList<>();
        teleports = new ArrayList<>();
        asteroid = start;
        start.Add(this);
    }

    public ArrayList<Material> getMaterials(){return materials;}

    public ArrayList<TeleportGate> getTeleports(){return teleports;}

    /**
     * mines asteroid's core material
     */
    public void Mine(){
        // only mines if player ship has 9 material or less
        if(materials.size() < 10) {
            Material core;
            core = asteroid.GetMined();
            // only adds if asteroid is not empty
            if(core != null){
                materials.add(core);
            }
        }
    }

    /**
     * removes material from the inventory
     * @param b the material we want to be removed from playership's inventory
     */
    public void Remove(BillOfMaterial b){
        ArrayList<Material> removable;
        removable = b.GetMaterials();
        for(Material m : removable){
            materials.remove(m);
        }
    }

    /**
     * crafts teleport gate pair
     */
    public void CraftTeleportGates(){
        if(teleports.size() <= 1) {
            BillCreator bc = BillCreator.GetInstance();
            BillOfMaterial bill = bc.CreateForTeleport(materials);
            // checks whether player ship has enough material to craft
            if(bill != null)  {
                Remove(bill);
                TeleportGate t1 = new TeleportGate(asteroid.sector.map.GetNewUID());
                TeleportGate t2 = new TeleportGate(asteroid.sector.map.GetNewUID());
                t1.pair(t2);
                teleports.add(t1);
                teleports.add(t2);
            }
        }
    }

    /**
     * crafts robot
     */
    public void CraftRobot(){
        BillCreator bc = BillCreator.GetInstance();
        BillOfMaterial bill = bc.CreateForRobot(materials);
        // checks whether player ship has enough material to craft
        if(bill != null){
            Remove(bill);
            RobotShip rs = new RobotShip(asteroid);
            asteroid.Add(rs);
        }
    }

    /**
     * crafts base
     */
    public void CraftBase(){
        if(asteroid.GetBase() == null){
            BillCreator bc = BillCreator.GetInstance();
            BillOfMaterial bill = bc.CreateForBaseFoundation(materials);
            if(bill != null) {
                Remove(bill);
                Base newbase = new Base(asteroid.sector.map.GetNewUID());
                for(Material m : bill.GetMaterials()){
                    newbase.Accept(m);
                }
                asteroid.SetBase(newbase);
            }
        }
    }

    /**
     *  puts back material to the core
     * @param m the material we want to put back to the core
     */
    public void PutBack(Material m){
        if(asteroid.SetCore(m)){
            materials.remove(m);
        }
    }

    /**
     * builds base
     */
    public void BuildBase(){
        // checks whether is a base already on the asteroid
        if(asteroid.GetBase() != null){
            ArrayList<Material> removables = new ArrayList<>();
            if(asteroid.GetBase() == null){
                Base base = new Base(asteroid.sector.map.GetNewUID());
                asteroid.SetBase(base);
            }
            for(Material it : materials)
                if(asteroid.GetBase().Accept(it))
                    removables.add(it);
            for(Material it : removables)
                materials.remove(it);
            asteroid.GetBase().CheckComplete();
        }
    }

    /**
     * puts down the teleport gate
     * @param t the teleport gate we want to put down next to the current asteroid
     */
    public void PutDown(TeleportGate t){
        // checks whether is player ship has teleport
        if(teleports.size()>0){
            Remove(t);
            t.AddNeighbour(asteroid);
            t.SetSector(asteroid.sector);
            asteroid.sector.Add(t);
            asteroid.AddNeighbour(t);
        }
    }

    /**
     * player ship dies
     */
    public void Die(){
        // removes all materials
        for(Material m : materials){
            materials.remove(m);
        }
        // removes all teleports
        for(TeleportGate t : teleports){
            Remove(t);
        }
        asteroid.Remove(this);
    }

    /**
     * in case of asteroid exploding player ship dies
     */
    public void AsteroidExploding(){
        Die();
    }

    /**
     *  adds material
     * @param m the material which will be added to the player ship's inventory
     */
    public void Add(Material m){
        materials.add(m);
    }

    /**
     * removes teleport gate
     * @param t the teleport gate which will be removed from the player ship's inventory
     */

    public void Remove(TeleportGate t){
        teleports.remove(t);
    }


    @Override
    public String toString(){
        return "PlayerShip";
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
            if(it.first.equals("Materials")){
                String[] ids = it.second.split(",");
                for (String idIt : ids) {
                    materials.add((Material) fc.GetWithUID(Integer.parseInt(idIt)));
                }
            }
            else if(it.first.equals("Teleports")){
                String[] ids = it.second.split(",");
                for (String idIt : ids) {
                    teleports.add((TeleportGate) fc.GetWithUID(Integer.parseInt(idIt)));
                }
            }
        }
    }

    /**
     * @param os the stream, where the class will be written
     */
    @Override
    public void Save(PrintStream os,boolean CallChildren) {
        os.println("PlayerShip{");
        super.Save(os,CallChildren);
        if(materials.size()>0) {
            os.print("Materials: ");
            materials.sort(new Comparator<Material>() {
                @Override
                public int compare(Material o1, Material o2) {
                    return o1.GetUID()-o2.GetUID();
                }
            });
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
            teleports.sort(new Comparator<TeleportGate>() {
                @Override
                public int compare(TeleportGate o1, TeleportGate o2) {
                    return o1.GetUID()-o2.GetUID();
                }
            });
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
            s.Save(os,CallChildren);
        }
        for(Material s : materials){
            s.Save(os,CallChildren);
        }
    }

}
