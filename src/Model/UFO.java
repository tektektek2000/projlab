package Model;

import Controllers.FileController;
import Model.Materials.Material;
import Utils.LinkerException;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.util.ArrayList;

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

    @Override
    public void Die() {
        asteroid.Remove(this);
        asteroid = null;
    }

    @Override
    public void AsteroidExploding() {
        Die();
    }

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

    @Override
    public void Save(PrintStream os) {
        os.println("UFO{");
        super.Save(os);
        if(materials.size()>0) {
            os.print("Materials: ");
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
        for(Material s : materials){
            s.Save(os);
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
