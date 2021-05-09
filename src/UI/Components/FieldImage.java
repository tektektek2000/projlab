package UI.Components;

import Model.Base;
import Model.Field;
import Model.Ship;
import Model.TeleportGate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class FieldImage extends ImageView {
    Field field;
    GridPane ships = null;
    ImageView base = null;
    TeleportGate pair = null;
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
            ships = new GridPane();
            int i =0;
            for(Ship s : Ships){
                ships.add(new ShipImage(s),i,0);
                i++;
            }
        }
        Base b = IV.getBase();
        if(b != null){
            ImageVisitor baseVisit = new ImageVisitor(b);
            base = new ImageView(baseVisit.image);
            base.setPreserveRatio(true);
            base.setFitWidth(MagicConstants.BaseImageSize);
            base.setFitHeight(MagicConstants.BaseImageSize);
        }
        pair = IV.getPair();
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

    public GridPane getShips(){
        return ships;
    }

    public Field getField(){
        return field;
    }

    public ImageView getBase(){return base;}

    public TeleportGate getPair(){return pair;}
}
