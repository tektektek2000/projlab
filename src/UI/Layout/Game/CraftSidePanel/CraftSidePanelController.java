package UI.Layout.Game.CraftSidePanel;

import UI.Components.SelectHandler;
import UI.Layout.Game.GameUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CraftSidePanelController {

        GameUIController gameUIController;
        @FXML
        Button RobotButton;
        @FXML
        Button TeleportsButton;
        @FXML
        Button BaseButton;
        @FXML
        Button CancelButton;

    public CraftSidePanelController(GameUIController GUIC){
        gameUIController = GUIC;
    }

    public void Init(){
        new SelectHandler(RobotButton);
        new SelectHandler(TeleportsButton);
        new SelectHandler(BaseButton);
        new SelectHandler(CancelButton);
    }

    @FXML
    public void Robot(){
        try {
            gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " craft robot");
            gameUIController.SwitchToInventory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Teleports(){try {
        gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " craft teleports");
        gameUIController.SwitchToInventory();
    } catch (Exception e) {
        e.printStackTrace();
    }}
    @FXML
    public void Base(){ try {
        gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " craft base");
        gameUIController.SwitchToInventory();
    } catch (Exception e) {
        e.printStackTrace();
    } }
    @FXML
    public void Cancel(){ gameUIController.SwitchToActionSidePanel(); }
}
