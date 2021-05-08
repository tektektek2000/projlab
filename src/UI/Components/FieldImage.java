package UI.Components;

import Model.Field;
import Model.Ship;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class FieldImage extends ImageView {
    Field field;
    HBox ships = null;
    public double x=0;
    public double y=0;
    public double size;
    public FieldImage(Field f){
        field = f;
        ImageVisitor IV= new ImageVisitor(f);
        Image im = IV.getImage();
        setImage(im);
        this.setFitWidth(MagicConstants.AsteroidSize);
        this.setFitHeight(MagicConstants.AsteroidSize);
        size = MagicConstants.AsteroidSize;
        x=f.getX();
        y=f.getY();
        this.setPreserveRatio(true);
        ArrayList<Ship> Ships = IV.getShips();
        if(Ships != null && Ships.size() != 0){
            ships = new HBox();
            for(Ship s : Ships){
                ships.getChildren().add(new ShipImage(s));
            }
        }
        //TODO

        // magic coloring
        // !!!!!!!!!!!!!!!!!!!!
        // BASED ON THE KNOWING OF SECTOR COUNT
        /*ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(f.getSector().GetUID()*0.12-0.12);
        colorAdjust.setSaturation(1.0);
        colorAdjust.setBrightness(f.getSector().GetUID()*0.12-0.12);
        this.setEffect(colorAdjust);*/

    }

    public HBox getShips(){
        return ships;
    }

    public Field getField(){
        return field;
    }
}
