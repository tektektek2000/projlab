package UI.Layout.Game.InventorySidePanel;

import UI.Layout.Game.GameUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InventorySidePanelController {
    GameUIController gameUIController;
    @FXML
    Label CoalNumb;
    @FXML
    Label IronNumb;
    @FXML
    Label IceNumb;
    @FXML
    Label UranNumb;
    @FXML
    Label PortalNumb;

    public InventorySidePanelController(GameUIController GUIC){
        gameUIController = GUIC;
    }

    public void Init(){

    }
}
