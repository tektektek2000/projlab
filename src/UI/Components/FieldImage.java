package UI.Components;

import Model.Field;
import Model.Ship;
import Utils.Pair;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class FieldImage extends ImageView {
    Field field;
    ArrayList<ShipImage> ships = new ArrayList<>();
    public double x=0;
    public double y=0;

    public FieldImage(Field f){
        ImageVisitor IV= new ImageVisitor(f);
        Image im = IV.getImage();
        setImage(im);
        this.setFitWidth(MagicConstants.Selected);
        this.setFitHeight(MagicConstants.Selected);
        x=f.getX();
        y=f.getY();
        this.setPreserveRatio(true);
        ArrayList<Ship> Ships = IV.getShips();
        if(ships != null){
            for(Ship s : Ships){
                ships.add(new ShipImage(s));
            }
        }
        //TODO
    }
}
