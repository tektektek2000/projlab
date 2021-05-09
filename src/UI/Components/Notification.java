package UI.Components;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class Notification {
    private Label msg;
    private VBox parent;
    private final double FadeStart;
    private final double FadeEnd;
    private double TimePassed;
    private Timeline timeline;
    private Color original;

    public Notification(Label Message, VBox Target, double fadeStart, double fadeEnd, Color c){
        msg = Message;
        parent = Target;
        FadeStart = fadeStart;
        FadeEnd = fadeEnd;
        TimePassed = 0;
        timeline = new Timeline(new KeyFrame(
                Duration.millis(10),
                ae -> Tick()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        Target.getChildren().add(msg);
        parent.setVisible(true);
        original = c;
    }

    private void Tick(){
        TimePassed += 10.0;
        if(TimePassed > FadeStart && TimePassed < FadeEnd){
            double alpha = ((FadeEnd - TimePassed) / (FadeEnd - FadeStart));
            Color NewColor = new Color(original.getRed(),original.getGreen(),original.getBlue(),alpha);
            msg.setTextFill(NewColor);
        }
        else if(TimePassed >= FadeEnd){
            timeline.stop();
            parent.getChildren().remove(msg);
            if(parent.getChildren().size()==0)
                parent.setVisible(false);
        }
    }

}
