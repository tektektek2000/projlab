package UI.Layout.Game.ActionSidePanel;

import UI.Components.SelectHandler;
import UI.Layout.Game.GameUIController;
import UI.Layout.GameOverMenu.GameOverMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ActionSidePanelController {
    GameUIController gameUIController;
    @FXML
    Button DrillButton;
    @FXML
    Button MineButton;
    @FXML
    Button BuildButton;
    @FXML
    Button CraftButton;
    @FXML
    Button PutBackButton;
    @FXML
    Button PutDownButton;
    @FXML
    Button SkipButton;

    public ActionSidePanelController(GameUIController GUIC){
        gameUIController = GUIC;
    }

    public void Init(){
        new SelectHandler(DrillButton);
        new SelectHandler(MineButton);
        new SelectHandler(BuildButton);
        new SelectHandler(CraftButton);
        new SelectHandler(PutBackButton);
        new SelectHandler(PutDownButton);
        new SelectHandler(SkipButton);
    }

    @FXML
    public void Drill(){
        try {
            gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " drill");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Mine(){
        try {
            gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " mine");
            gameUIController.SwitchToInventory();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void Build(){
        try {
            gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " build_base");
            gameUIController.SwitchToInventory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Craft(){
        gameUIController.SwitchToCraftSidePanel();
    }
    @FXML
    public void PutBack(){ gameUIController.SwitchToPutBackSidePanel();}
    @FXML
    public void PutDown(){
        gameUIController.SwitchToPutDownSidePanel();
    }
    @FXML
    public void Skip(){
        System.out.println("Skip");
    }
}
