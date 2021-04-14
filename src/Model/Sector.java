package Model;

import java.util.ArrayList;

public class Sector {
    private ArrayList<Field> fields;
    private boolean SunClose;

    Sector(){
        SunClose = false;
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
