package UI.Components;

import Model.Ship;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShipImage extends ImageView {
    Ship ship;

    ShipImage(Ship s,double ratio){
        ImageVisitor IV= new ImageVisitor(s);
        Image im = IV.getImage();
        setImage(im);
        this.setFitHeight(im.getHeight()*ratio);
        this.setFitHeight(im.getHeight()*ratio);

        //TODO
    }
}
