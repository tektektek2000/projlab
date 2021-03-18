package Model.Materials;

import java.util.ArrayList;

public abstract class BillCreator {
    private BillCreator instance=null;

    private BillCreator(){}
    public abstract BillCreator GetInstance();

    public abstract int CountAndAdd(ArrayList<Material> inventory,Material Comparator,BillOfMaterial bill);
    public abstract BillOfMaterial CreateForTeleport(ArrayList<Material> inventory);
}
