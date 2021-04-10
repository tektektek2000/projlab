package Model;

import Model.Materials.BillCreator;
import Model.Materials.BillOfMaterial;
import Model.Materials.Material;

import java.util.ArrayList;

public class Base{
    ArrayList<Material> materials = new ArrayList<>();

    // Handles if the base will accept an offered material. Returns true if it added the material,false if not
    public boolean Accept(Material m){
        Skeleton.AddAndPrintCallStack("Base.Accept()");
        int count=0; // Counts the number of materials that are the same type and are already stored
        boolean added=false; // Stores whether or not the material was added

        for(Material it : materials){
            if(it.isSameType(m)) //Checking if they are the same type
                count++; // Counting if yes
        }
        if(count<3){
            Add(m);
            added = true;
            //CheckComplete(); //Checking if the base is finished with this material
            System.out.println("Base accepted material.");
        }
        else{
            System.out.println("Base rejected material.");
        }
        Skeleton.RemoveFromCallStack("Base.Accept()");
        return added;
    }

    // Checks whether or not the base has enough resources to be considered complete
    public void CheckComplete() {
        Skeleton.AddAndPrintCallStack("Base.CheckComplete()");
        BillCreator bc = BillCreator.GetInstance();
        System.out.println("Checking if base has enough materials.");
        BillOfMaterial Bill = bc.CreateForBase(materials); // Creating a bill to see if the base is complete
        if(Bill != null){ // If the bill is not null than the base has enough materials
            System.out.println("The base is completed, players won.");
        }
        Skeleton.RemoveFromCallStack("Base.CheckComplete()");
    }
    // Adds the argument material to the stored materials of the base
    private void Add(Material material){
        Skeleton.AddAndPrintCallStack("Base.Add()");
        materials.add(material); // Adding material
        Skeleton.RemoveFromCallStack("Base.Add()");
    }

}
