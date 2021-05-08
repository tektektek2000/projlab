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
        System.out.println("Robot");
    }
    @FXML
    public void Teleports(){ System.out.println("Teleports"); }
    @FXML
    public void Base(){ System.out.println("Base"); }
    @FXML
    public void Cancel(){ gameUIController.SwitchToActionSidePanel(); }
}
