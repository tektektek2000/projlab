package UI.Components;

import Model.Field;
import Model.Ship;
import Utils.Pair;
import javafx.scene.effect.ColorAdjust;
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
        if(Ships != null){
            for(Ship s : Ships){
                ships.add(new ShipImage(s));
            }
        }
        //TODO

        // magic coloring
        // !!!!!!!!!!!!!!!!!!!!
        // BASED ON THE KNOWING OF SECTOR COUNT
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(f.getSector().GetUID()*0.12-0.12);
        colorAdjust.setSaturation(1.0);
        colorAdjust.setBrightness(f.getSector().GetUID()*0.12-0.12);
        this.setEffect(colorAdjust);

    }
}
