package Model.Materials;

import java.util.ArrayList;

public class BillOfMaterial {
    ArrayList<Material> materials = new ArrayList<>();

    /**
     * adds the argument material to the stored materials of the bill
     * @param m the material, which we want to add to the bill
     */
    public void Add(Material m){
        materials.add(m);
    }

    /**
     * basically a getter for the bill's materials
     * @return with the materials of the bill
     */
    public final ArrayList<Material> GetMaterials(){
        return materials;
    }
}
