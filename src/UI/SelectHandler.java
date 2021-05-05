package UI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;

import static com.sun.javafx.scene.control.skin.Utils.getResource;


public class SelectHandler implements EventHandler<MouseEvent> {
    Button cs;

    public SelectHandler(Button b){
        cs = b;
        b.getStylesheets().add(this.getClass().getResource("CustomButton.css").toExternalForm());
        b.setOnMouseEntered(this);
        b.setOnMouseExited(this);
        b.getStyleClass().add("transparent");
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
            cs.getStyleClass().remove("transparent");
            cs.getStyleClass().add("selected");
        }
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
            cs.getStyleClass().remove("selected");
            cs.getStyleClass().add("transparent");
        }
    }

}
