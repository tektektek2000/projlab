package UI.Components;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
        b.addEventHandler(MouseEvent.ANY,this);
        b.getStyleClass().add("transparent");
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
            cs.getStyleClass().removeAll("transparent");
            cs.getStyleClass().removeAll("pressed");
            cs.getStyleClass().add("selected");
        }
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
            cs.getStyleClass().removeAll("selected");
            cs.getStyleClass().removeAll("pressed");
            cs.getStyleClass().add("transparent");
        }
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
            cs.getStyleClass().removeAll("selected");
            cs.getStyleClass().removeAll("transparent");
            cs.getStyleClass().add("pressed");
        }
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
            cs.getStyleClass().removeAll("pressed");
            if(!cs.getStyleClass().contains("transparent"))
                cs.getStyleClass().add("selected");
        }
    }

}