package Model;

import Controllers.FileController;
import Model.Materials.BillCreator;
import Model.Materials.BillOfMaterial;
import Model.Materials.Material;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Base extends Saveable{
    final int UID;
    ArrayList<Material> materials = new ArrayList<>();

    public Base(int uid) {
        UID = uid;
    }

    // Handles if the base will accept an offered material. Returns true if it added the material,false if not
    public boolean Accept(Material m){
        int count=0; // Counts the number of materials that are the same type and are already stored
        boolean added=false; // Stores whether or not the material was added

        for(Material it : materials){
            if(it.isSameType(m)) //Checking if they are the same type
                count++; // Counting if yes
        }
        if(count<3){
            Add(m);
            added = true;
            //CheckComplete(); //Checking if the base is finished with this material;
        }
        return added;
    }

    // Checks whether or not the base has enough resources to be considered complete
    public void CheckComplete() {
        BillCreator bc = BillCreator.GetInstance();
        BillOfMaterial Bill = bc.CreateForBase(materials); // Creating a bill to see if the base is complete
        if(Bill != null){ // If the bill is not null than the base has enough materials
            //System.out.println("The base is completed, players won.");
        }
    }
    // Adds the argument material to the stored materials of the base
    private void Add(Material material){
        materials.add(material); // Adding material
    }

    @Override
    public int GetUID() {
        return UID;
    }


    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws RuntimeErrorException {
        for(StringPair it : args) {
            if(it.key.equals("Materials")){
                String[] ids = it.value.split(",");
                for(String idIt : ids){
                    materials.add((Material)fc.GetWithUID(Integer.getInteger(idIt)));
                }
            }
        }
    }

    @Override
    public void Save(PrintStream os) {
        os.println("Base{");
        os.print("\tMaterials: ");
        for(Material it : materials){
            os.print(it.getUID());
            if(it != materials.get(materials.size()-1)){
                os.print(",");
            }
        }
        for(Material it : materials){
            it.Save();
        }
    }
}
