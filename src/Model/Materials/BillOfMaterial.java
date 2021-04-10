package Model.Materials;

import Model.Skeleton;

import java.util.ArrayList;

public class BillOfMaterial {
    ArrayList<Material> materials = new ArrayList<>();

    // Adds the argument material to the stored materials of the bill
    public void Add(Material m){
        Skeleton.AddAndPrintCallStack("BillOfMaterial.Add()");
        materials.add(m);
        Skeleton.RemoveFromCallStack("BillOfMaterial.Add()");
    }
    public final ArrayList<Material> GetMaterials(){
        return materials;
    }
}
