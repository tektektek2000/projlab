package Model.Materials;

import java.util.ArrayList;

public class BillOfMaterial {
    ArrayList<Material> materials;

    /*Adds the argument material to the stored materials of the bill*/
    public void Add(Material m){
        materials.add(m);
    }
    public final ArrayList<Material> GetMaterials(){
        return materials;
    }
}
