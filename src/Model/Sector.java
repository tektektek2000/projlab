package Model;

import Controllers.FileController;
import Model.Materials.Material;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Sector extends Saveable{
    private ArrayList<Field> fields;
    private boolean SunClose;

    public Sector(int UID) {
        super(UID);
        SunClose = false;
        fields = new ArrayList<>();
    }


    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws RuntimeErrorException {
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

    Sector(boolean sunclose){
        SunClose = sunclose;
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

    public boolean getSunClose(){
        return SunClose;
    }
}
