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

        int teleportCnt = gameUIController.getGameController().getCurrentPlayer().getTeleports().size();
        if(teleportCnt >= 1)
            Teleport1Button.setText("X");
        if(teleportCnt >= 2)
            Teleport2Button.setText("X");
        if (teleportCnt >= 3)
            Teleport3Button.setText("X");
    }



    @FXML
    public void Teleport1(){
        try {
            if(gameUIController.getGameController().getCurrentPlayer().getTeleports().size() >= 1){
                Teleport1Button.setText("");
                gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " put_down " + gameUIController.getGameController().getCurrentPlayer().getTeleports().get(0).GetUID());
                gameUIController.SwitchToInventory();
                gameUIController.SwitchToActionSidePanel();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Teleport2(){
        try {
            if(gameUIController.getGameController().getCurrentPlayer().getTeleports().size() >= 2) {
                Teleport2Button.setText("");
                gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " put_down " + gameUIController.getGameController().getCurrentPlayer().getTeleports().get(1).GetUID());
                gameUIController.SwitchToInventory();
                gameUIController.SwitchToActionSidePanel();
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    @FXML
    public void Teleport3(){
        try {
            if (gameUIController.getGameController().getCurrentPlayer().getTeleports().size() >= 3){
                Teleport3Button.setText("");
                gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " put_down " + gameUIController.getGameController().getCurrentPlayer().getTeleports().get(2).GetUID());
                gameUIController.SwitchToInventory();
                gameUIController.SwitchToActionSidePanel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Cancel() {
        gameUIController.SwitchToActionSidePanel();
    }
}
