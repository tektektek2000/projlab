package UI.Components;

import Model.Field;
import Model.Ship;
import Utils.Pair;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FieldImage extends ImageView {
    Field field;

    FieldImage(Field f, double ratio){
        ImageVisitor IV= new ImageVisitor(f);
        Image im = IV.getImage();
        setImage(im);
        this.setFitHeight(im.getHeight()*ratio);
        this.setFitHeight(im.getHeight()*ratio);
        //TODO
    }
}
