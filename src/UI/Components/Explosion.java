package UI.Components;

import Model.Asteroid;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Explosion {
    double Osize;
    double size;
    double x,y;
    ImageView image;
    Timeline timeline;
    double TimePassed = 0;
    double TimeEnd;
    double Growth;
    static Image ExplosionIMG =null;

    public Explosion(Asteroid a,double duration,double growth){
        x = a.getX();
        y = a.getY();
        Osize = MagicConstants.AsteroidSize;
        size = Osize;
        if(ExplosionIMG == null){
            try {
                ExplosionIMG = new Image(new FileInputStream(new File("").getAbsolutePath()+"\\img\\explosion.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(ExplosionIMG == null){
            System.out.println("Baj van");
        }
        image = new ImageView(ExplosionIMG);
        image.setPreserveRatio(true);
        TimeEnd = duration;
        Growth = growth;
        timeline = new Timeline(new KeyFrame(
                Duration.millis(10),
                ae -> Tick()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void Tick(){
        TimePassed += 10;
        if(TimePassed < TimeEnd){
            size = Osize * Growth * (TimePassed/TimeEnd + 0.5);
            image.setOpacity((1 - (TimePassed/TimeEnd)));
        }
        else{
            timeline.stop();
            timeline = null;
            image.setOpacity(0);
        }
    }

    public double getHeight(){
        return size;
    }

    public double getWidth(){
        return size * 1.63211839;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public ImageView getImage(){
        return image;
    }

    public boolean isDone(){
        return TimePassed >= TimeEnd;
    }
}
