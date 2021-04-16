package Model.Materials;

import Model.Map;

import java.util.ArrayList;
import java.util.Comparator;

public class BillCreator {
    private static BillCreator instance=null;

    private BillCreator(){}

    // Singleton class. Returns the only instance of BillCreator that exists
    public static BillCreator GetInstance(){
        if(instance==null){
            instance = new BillCreator();
        }
        return instance;
    }

    // Compares all materials in an inventory to a given reference material, and if they are of the same type adds them to the given BillOfMaterial
    // Returns the number of materials added
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

    // Prepares a BillOfMaterial for a pair of TeleportGate-s. If not enough resources are found in the inventory than it returns null.
    public BillOfMaterial CreateForTeleport(ArrayList<Material> inventory){
        BillOfMaterial bill = new BillOfMaterial();
        if(!CountAndAdd(inventory,new Uranium(new Map()),bill,1) || !CountAndAdd(inventory,new Ice(new Map()),bill,1) || !CountAndAdd(inventory,new Iron(new Map()),bill,2)){
            return null;
        }
        return bill;
    }

    // Prepares a BillOfMaterial for a BaseFoundation. If not enough resources are found in the inventory than it returns null.
    public BillOfMaterial CreateForBaseFoundation(ArrayList<Material> inventory){
        BillOfMaterial bill = new BillOfMaterial();
        if(!CountAndAdd(inventory,new Iron(new Map()),bill,3)){
            return null;
        }
        return bill;
    }

    // Prepares a BillOfMaterial for a Robot. If not enough resources are found in the inventory than it returns null.
    public BillOfMaterial CreateForRobot(ArrayList<Material> inventory){
        BillOfMaterial bill = new BillOfMaterial();
        if(!CountAndAdd(inventory,new Uranium(new Map()),bill,1) || !CountAndAdd(inventory,new Coal(new Map()),bill,1)  || !CountAndAdd(inventory,new Iron(new Map()),bill,1)){
            return null;
        }
        return bill;
    }

    // Prepares a BillOfMaterial for a completed Base. If not enough resources are found in the inventory than it returns null.
    public BillOfMaterial CreateForBase(ArrayList<Material> inventory){
        BillOfMaterial bill = new BillOfMaterial();
        if(!CountAndAdd(inventory,new Uranium(new Map()),bill,3) || !CountAndAdd(inventory,new Coal(new Map()),bill,3) || !CountAndAdd(inventory,new Iron(new Map()),bill,3) || !CountAndAdd(inventory,new Ice(new Map()),bill,3)){
            return null;
        }
        return bill;
    }
}
