package UI.Components;

import Model.Ship;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShipImage extends ImageView {
    Ship ship;

    ShipImage(Ship s){
        ImageVisitor IV= new ImageVisitor(s);
        Image im = IV.getImage();
        setImage(im);
        this.setFitWidth(MagicConstants.Selected);
        this.setFitHeight(MagicConstants.Selected);
        this.setPreserveRatio(true);

        //TODO
    }
}
