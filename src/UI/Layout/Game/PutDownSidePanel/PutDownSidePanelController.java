package UI.Layout.Game.PutDownSidePanel;

import Model.TeleportGate;
import UI.Components.SelectHandler;
import UI.Layout.Game.GameUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class PutDownSidePanelController {
    GameUIController gameUIController;
    @FXML
    Button Teleport1Button;
    @FXML
    Button Teleport2Button;
    @FXML
    Button Teleport3Button;
    @FXML
    Button CancelButton;

    public PutDownSidePanelController(GameUIController GUIC) {
            gameUIController = GUIC;
        }

    public void Init() {
        new SelectHandler(Teleport1Button);
        new SelectHandler(Teleport2Button);
        new SelectHandler(Teleport3Button);
        new SelectHandler(CancelButton);
    }

    @FXML
    public void Teleport1(){
        try {
            gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " put_down" + gameUIController.getGameController().getCurrentPlayer().getTeleports().get(0).GetUID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Teleport2(){
        try {
            gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " put_down" + gameUIController.getGameController().getCurrentPlayer().getTeleports().get(1).GetUID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Teleport3(){
        try {
            gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " put_down" + gameUIController.getGameController().getCurrentPlayer().getTeleports().get(2).GetUID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Cancel() {
        gameUIController.SwitchToActionSidePanel();
    }
}
