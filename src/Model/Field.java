package Model;

import Controllers.FileController;
import Model.Materials.Material;
import Utils.LinkerException;
import Utils.StringPair;

import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;

public abstract class Field extends Saveable {
    ArrayList<Field> Neighbours = new ArrayList<>();
    Sector sector;

    Field(Map m){
        super(m);
    }

    Field(Sector s){
        super(s.map);
        sector = s;
        Neighbours = new ArrayList<>();
    }

    public Field(int UID) {
        super(UID);
        Neighbours = new ArrayList<>();
    }

    // ship moves to the asteroid
    public abstract Asteroid MovedTo();

    // removes a neighbour
    public void RemoveNeighbour(Field f){
        Neighbours.remove(f);
    }

    // adds a neighbour
    public void AddNeighbour(Field f){
        Neighbours.add(f);
    }

    // getting neighbours list
    public ArrayList<Field> getNeighbours() {
        return Neighbours;
    }

    public abstract void SunStorm();

    @Override
    public void Link(ArrayList<StringPair> args, FileController fc) throws LinkerException {
        for(StringPair it : args) {
            if(it.first.equals("Neighbours")){
                String[] ids = it.second.split(",");
                for(String idIt : ids){
                    Neighbours.add((Field)fc.GetWithUID(Integer.parseInt(idIt)));
                }
            }
            else if(it.first.equals("Sector")){
                sector = (Sector) fc.GetWithUID(Integer.parseInt(it.second));
            }
        }
    }

    @Override
    public void Save(PrintStream os) {
        os.println("UID: " + GetUID());
        if(sector!=null) {
            os.println("Sector: " + sector.GetUID());
        }
        if(Neighbours.size()>0) {
            os.print("Neighbours: ");
            Neighbours.sort(new Comparator<Field>() {
                @Override
                public int compare(Field o1, Field o2) {
                    return o1.GetUID()-o2.GetUID();
                }
            });
            for (Field it : Neighbours) {
                os.print(it.GetUID());
                if (it != Neighbours.get(Neighbours.size() - 1)) {
                    os.print(",");
                } else {
                    os.println();
                }
            }
        }
    }
}
