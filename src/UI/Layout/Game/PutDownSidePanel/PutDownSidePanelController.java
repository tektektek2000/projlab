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
    public void Teleport1(){ System.out.println("Teleport1"); }
    @FXML
    public void Teleport2(){ System.out.println("Teleport2"); }
    @FXML
    public void Teleport3(){ System.out.println("Teleport3"); }
    @FXML
    public void Cancel() {
        gameUIController.SwitchToActionSidePanel();
    }
}
