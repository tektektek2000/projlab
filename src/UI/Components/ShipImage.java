package UI.Components;

import Model.Ship;
import javafx.fxml.FXML;
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
        ship = s;

        //TODO
    }

    public Ship getShip(){
        return ship;
    }

    @Override
    public String toString(){
        return "ShipImage";
    }
}
