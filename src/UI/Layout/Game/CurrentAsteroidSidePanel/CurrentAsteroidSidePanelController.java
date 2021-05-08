package UI.Layout.Game.CurrentAsteroidSidePanel;

import UI.Layout.Game.GameUIController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CurrentAsteroidSidePanelController {
    GameUIController gameUIController;
    @FXML
    TextField ShellField;
    @FXML
    TextField CoreField;

    public CurrentAsteroidSidePanelController(GameUIController GUIC){
        gameUIController = GUIC;
    }

    public void Init(){

    }

}
