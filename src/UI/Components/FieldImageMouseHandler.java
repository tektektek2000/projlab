package UI.Components;

import Model.Field;
import UI.Layout.Game.GameUIController;
import javafx.event.EventHandler;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;

public class FieldImageMouseHandler implements EventHandler<MouseEvent> {
    GameUIController gameUIController;
    FieldImage fieldImage;

    public FieldImageMouseHandler(FieldImage f, GameUIController guc){
        gameUIController = guc;
        fieldImage = f;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
            gameUIController.Move(fieldImage);
        }
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
            gameUIController.Select(fieldImage);
        }
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
            gameUIController.Deselect(fieldImage,false, mouseEvent.getSceneX(),mouseEvent.getSceneY());
        }
    }
}
