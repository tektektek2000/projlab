package Model;

import Controllers.FileController;
import Model.Materials.BillCreator;
import Model.Materials.BillOfMaterial;
import Model.Materials.Material;
import Utils.LinkerException;
import Utils.StringPair;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;

public class Base extends Saveable{
    ArrayList<Material> materials = new ArrayList<>();

    public Base(int uid) {
        super(uid);
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
    public void Link(ArrayList<StringPair> args, FileController fc) throws LinkerException {
        for(StringPair it : args) {
            if(it.first.equals("Materials")){
                String[] ids = it.second.split(",");
                for(String idIt : ids){
                    materials.add((Material)fc.GetWithUID(Integer.parseInt(idIt)));
                }
            }
        }
    }

    @Override
    public void Save(PrintStream os, boolean CallChildren) {
        os.println("Base{");
        os.println("UID: " + GetUID());
        if(materials.size()>0) {
            os.print("\tMaterials: ");
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
        os.println("}");
        if(CallChildren) {
            for (Material it : materials) {
                it.Save(os, CallChildren);
            }
        }
    }
}
