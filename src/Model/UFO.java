package Model;

import Controllers.FileController;
import Model.Materials.Material;
import Utils.LinkerException;
import Utils.StringPair;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;

public class UFO extends Ship{
    ArrayList<Material> materials;

    UFO(Asteroid a){
        super(a);
        materials = new ArrayList<>();
    }

    public UFO(int uid){
        super(uid);
        materials = new ArrayList<>();
    }

    /**
     * UFO dies
     */
    @Override
    public void Die() {
        asteroid.Remove(this);
        asteroid = null;
    }

    /**
     * special action if the asteroid explodes UFO dies
     */
    @Override
    public void AsteroidExploding() {
        Die();
    }

    /**
     * @param args
     * @param fc
     * @throws LinkerException
     */
    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws LinkerException {
        super.Link(args,fc);
        for(StringPair it : args) {
            if(it.first.equals("Materials")){
                String[] ids = it.second.split(",");
                for (String idIt : ids) {
                    materials.add((Material) fc.GetWithUID(Integer.getInteger(idIt)));
                }
            }
        }
    }

    /**
     * @param os the stream, where the class will be written
     * @param CallChildren
     */
    @Override
    public void Save(PrintStream os, boolean CallChildren) {
        os.println("UFO{");
        super.Save(os, CallChildren);
        if(materials.size()>0) {
            os.print("Materials: ");
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
            for (Material s : materials) {
                s.Save(os, CallChildren);
            }
        }
    }

    // mines asteroid's core material
    public void Mine(){
        // only mines if player ship has 9 material or less
        if(materials.size() < 10) {
            Material core;
            core = asteroid.GetMined();
            // only adds if asteroid is not empty
            if(core != null){
                materials.add(core);
            }
        }
    }
}
