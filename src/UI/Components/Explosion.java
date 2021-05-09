package UI.Components;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Explosion {
    double size;
    double x,y;
    ImageView image;
    Timeline timeline;
    ArrayList<Explosion> parent;
    static Image Explosion=null;

    public Explosion(){

    }
}
