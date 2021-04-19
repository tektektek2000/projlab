package Model;

import Controllers.FileController;
import Model.Materials.Material;
import Utils.LinkerException;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;

public class Sector extends Saveable{
    private ArrayList<Field> fields;
    private boolean SunClose;
    Map map = null;

    public Sector(int UID,Map m) {
        super(UID);
        SunClose = false;
        fields = new ArrayList<>();
        map = m;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws LinkerException {
        for(StringPair it : args) {
            if(it.first.equals("Fields")){
                String[] ids = it.second.split(",");
                for (String idIt : ids) {
                    fields.add((Field) fc.GetWithUID(Integer.parseInt(idIt)));
                }
            }
            else if(it.first.equals("isClose")){
                SunClose = Boolean.parseBoolean(it.second);
            }
        }
    }

    @Override
    public void Save(PrintStream os) {
        os.println("Sector{");
        os.println("UID: " + GetUID());
        if(fields.size()>0) {
            os.print("Fields: ");
            fields.sort(new Comparator<Field>() {
                @Override
                public int compare(Field o1, Field o2) {
                    return o1.GetUID()-o2.GetUID();
                }
            });
            for (Field it : fields) {
                os.print(it.GetUID());
                if (it != fields.get(fields.size() - 1)) {
                    os.print(",");
                } else {
                    os.println();
                }
            }
        }
        os.println("isClose: " + SunClose);
        os.println("}");
        for(Field it : fields) {
            it.Save(os);
        }
    }

    public void Add(Field f){
        fields.add(f);
    }

    public void Remove(Field f){
        fields.remove(f);
    }

    public void SunStorm(){
        for(Field it : fields){
            it.SunStorm();
        }
    }

    public ArrayList<Field> getFields(){return fields;}

    public boolean getSunClose(){
        return SunClose;
    }
}
