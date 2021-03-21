package Model;

import Model.Materials.BillCreator;
import Model.Materials.BillOfMaterial;
import Model.Materials.Material;

import java.util.ArrayList;

public class Base{
    ArrayList<Material> materials;

    /*Handles if the base will accept an offered material. Returns true if it added the material,false if not.*/
    public boolean Accept(Material m){
        int count=0; //Counts the number of materials that are the same type and are already stored
        boolean added=false; //Stores whether or not the material was added

        for(Material it : materials){
            if(it.isSameType(m)) //Checking if they are the same type
                count++; //Counting if yes
        }
        if(count<3){
            Add(m);
            added = true;
            CheckComplete(); //Checking if the base is finished with this material
        }
        return added;
    }

    /*Checks whether or not the base has enough resources to be considered complete*/
    private void CheckComplete() {
        BillCreator bc = BillCreator.GetInstance();
        BillOfMaterial Bill = bc.CreateForBase(materials); //Creating a bill to see if the base is complete
        if(Bill != null){ //If the bill is not null than the base has enough materials
            System.out.println("The base is completed, players won.");
        }

    }
    /*Adds the argument material to the stored materials of the base*/
    private void Add(Material material){
        materials.add(material); //Adding material
    }

}
