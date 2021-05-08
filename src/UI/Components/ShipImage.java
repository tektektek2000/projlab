package UI.Components;

import Model.Ship;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShipImage extends ImageView {
    Ship ship;
    public double size;

    ShipImage(Ship s){
        ImageVisitor IV= new ImageVisitor(s);
        Image im = IV.getImage();
        setImage(im);
        this.setFitWidth(MagicConstants.ShipSize);
        this.setFitHeight(MagicConstants.ShipSize);
        size = MagicConstants.ShipSize;
        this.setPreserveRatio(true);

        //TODO
    }

    public Ship getShip(){
        return ship;
    }
}
