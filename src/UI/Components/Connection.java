package UI.Components;

import Model.Field;
import javafx.scene.shape.Line;

public class Connection {
    Field f1;
    Field f2;
    public Line line = null;

    public Connection(Field F1,Field F2){
        f1 = F1;
        f2 = F2;
    }

    public Field getF1(){
        return f1;
    }

    public Field getF2(){
        return f2;
    }

    public boolean isSame(Connection b){
        return (f1 == b.f1 && f2 == b.f2) || (f1 == b.f2 && f2 == b.f1);
    }
}
