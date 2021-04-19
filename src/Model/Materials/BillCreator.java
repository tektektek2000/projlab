package Model.Materials;

import Model.Map;

import java.util.ArrayList;
import java.util.Comparator;

public class BillCreator {
    private static BillCreator instance=null;

    private BillCreator(){}

    /**
     * @return Singleton class. Returns the only instance of BillCreator that exists
     */
    public static BillCreator GetInstance(){
        if(instance==null){
            instance = new BillCreator();
        }
        return instance;
    }

    /**
     * compares all materials in an inventory to a given reference material, and if they are of the same type adds them to the given BillOfMaterial
     * @param inventory the list of materials where we search for the needed material
     * @param Comparator the material what we are looking for
     * @param bill the BillOfMaterial, where we add the matching material if needed
     * @param Needed how many materials we need for the bill
     * @return true, if there was enough materials in the given inventory, and false if not
     */

    public boolean CountAndAdd(ArrayList<Material> inventory,Material Comparator,BillOfMaterial bill,int Needed){
        int Count=0;
        for(Material it : inventory){
            if(Comparator.isSameType(it)){
                if(Count < Needed)
                    bill.Add(it);
                Count++;
            }
        }
        return Count>=Needed;
    }

    /**
     * prepares a BillOfMaterial for teleport gates
     * @param inventory the list of materials where we search for the needed ones
     * @return the bill, if possible, null if there is not enough materials in the inventory
     */
    public BillOfMaterial CreateForTeleport(ArrayList<Material> inventory){
        BillOfMaterial bill = new BillOfMaterial();
        if(!CountAndAdd(inventory,new Uranium(new Map()),bill,1) || !CountAndAdd(inventory,new Ice(new Map()),bill,1) || !CountAndAdd(inventory,new Iron(new Map()),bill,2)){
            return null;
        }
        return bill;
    }

    /**
     * prepares a BillOfMaterial for a base foundation
     * @param inventory the list of materials where we search for the needed ones
     * @return the bill, if possible, null if there is not enough materials in the inventory
     */
    public BillOfMaterial CreateForBaseFoundation(ArrayList<Material> inventory){
        BillOfMaterial bill = new BillOfMaterial();
        if(!CountAndAdd(inventory,new Iron(new Map()),bill,3)){
            return null;
        }
        return bill;
    }

    /**
     * prepares a BillOfMaterial for a robot
     * @param inventory the list of materials where we search for the needed ones
     * @return the bill, if possible, null if there is not enough materials in the inventory
     */
    public BillOfMaterial CreateForRobot(ArrayList<Material> inventory){
        BillOfMaterial bill = new BillOfMaterial();
        if(!CountAndAdd(inventory,new Uranium(new Map()),bill,1) || !CountAndAdd(inventory,new Coal(new Map()),bill,1)  || !CountAndAdd(inventory,new Iron(new Map()),bill,1)){
            return null;
        }
        return bill;
    }

    /**
     * prepares a BillOfMaterial for a completed base
     * @param inventory the list of mterials where we search for the needed ones
     * @return the bill, if possible, null if there is not enough materials in the inventory
     */
    public BillOfMaterial CreateForBase(ArrayList<Material> inventory){
        BillOfMaterial bill = new BillOfMaterial();
        if(!CountAndAdd(inventory,new Uranium(new Map()),bill,3) || !CountAndAdd(inventory,new Coal(new Map()),bill,3) || !CountAndAdd(inventory,new Iron(new Map()),bill,3) || !CountAndAdd(inventory,new Ice(new Map()),bill,3)){
            return null;
        }
        return bill;
    }
}
