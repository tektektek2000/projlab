package Model.Materials;

import Model.Skeleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
    public int CountAndAdd(ArrayList<Material> inventory,Material Comparator,BillOfMaterial bill){
        Skeleton.AddAndPrintCallStack("BillCreator.CountAndAdd()");
        System.out.println("How many " + Comparator.GetTypeUnique() + " does the current entity have?");
        int Count;
        Scanner in = new Scanner(System.in);
        Count = in.nextInt();
        Skeleton.RemoveFromCallStack("BillCreator.CountAndAdd()");
        return Count;
    }

    // Prepares a BillOfMaterial for a pair of TeleportGate-s. If not enough resources are found in the inventory than it returns null.
    public BillOfMaterial CreateForTeleport(ArrayList<Material> inventory){
        Skeleton.AddAndPrintCallStack("BillCreator.CreateForTeleport()");
        BillOfMaterial bill = new BillOfMaterial();
        int UraniumNum = CountAndAdd(inventory,new Uranium(),bill);
        int CoalNum = CountAndAdd(inventory,new Coal(),bill);
        int IronNum = CountAndAdd(inventory,new Iron(),bill);

        Skeleton.RemoveFromCallStack("BillCreator.CreateForTeleport()");
        if(UraniumNum<1 || CoalNum < 1 || IronNum < 2){
            return null;
        }
        return bill;
    }

    // Prepares a BillOfMaterial for a BaseFoundation. If not enough resources are found in the inventory than it returns null.
    public BillOfMaterial CreateForBaseFoundation(ArrayList<Material> inventory){
        Skeleton.AddAndPrintCallStack("BillCreator.CreateForBaseFoundation()");
        BillOfMaterial bill = new BillOfMaterial();
        int IronNum = CountAndAdd(inventory,new Iron(),bill);

        Skeleton.RemoveFromCallStack("BillCreator.CreateForBaseFoundation()");
        if(IronNum < 3){
            return null;
        }
        return bill;
    }

    // Prepares a BillOfMaterial for a Robot. If not enough resources are found in the inventory than it returns null.
    public BillOfMaterial CreateForRobot(ArrayList<Material> inventory){
        Skeleton.AddAndPrintCallStack("BillCreator.CreateForRobot()");
        BillOfMaterial bill = new BillOfMaterial();
        int UraniumNum = CountAndAdd(inventory,new Uranium(),bill);
        int CoalNum = CountAndAdd(inventory,new Coal(),bill);
        int IronNum = CountAndAdd(inventory,new Iron(),bill);

        Skeleton.RemoveFromCallStack("BillCreator.CreateForRobot()");
        if(UraniumNum<1 || CoalNum < 1 || IronNum < 1){
            return null;
        }
        return bill;
    }

    // Prepares a BillOfMaterial for a completed Base. If not enough resources are found in the inventory than it returns null.
    public BillOfMaterial CreateForBase(ArrayList<Material> inventory){
        Skeleton.AddAndPrintCallStack("BillCreator.CreateForBase()");
        BillOfMaterial bill = new BillOfMaterial();
        int UraniumNum = CountAndAdd(inventory,new Uranium(),bill);
        int CoalNum = CountAndAdd(inventory,new Coal(),bill);
        int IronNum = CountAndAdd(inventory,new Iron(),bill);
        int IceNum = CountAndAdd(inventory,new Ice(),bill);

        Skeleton.RemoveFromCallStack("BillCreator.CreateForBase()");
        if(UraniumNum<3 || CoalNum < 3 || IronNum < 3 || IceNum <3){
            return null;
        }
        return bill;
    }
}
