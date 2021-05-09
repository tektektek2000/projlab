package UI.Layout.Game.CraftSidePanel;

import Model.Materials.BillCreator;
import Model.PlayerShip;
import UI.Components.SelectHandler;
import UI.Layout.Game.GameUIController;
import UI.Layout.Game.ISidePanelController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;

public class CraftSidePanelController implements ISidePanelController {

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
        Refresh();
    }

    @FXML
    public void Robot(){
        try {
            gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " craft robot");
            gameUIController.SwitchToInventory();
            gameUIController.SwitchToActionSidePanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Teleports(){try {
        gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " craft teleports");
        gameUIController.SwitchToInventory();
        gameUIController.SwitchToActionSidePanel();
    } catch (Exception e) {
        e.printStackTrace();
    }}
    @FXML
    public void Base(){ try {
        gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " craft base");
        gameUIController.SwitchToInventory();
        gameUIController.SwitchToActionSidePanel();
    } catch (Exception e) {
        e.printStackTrace();
    } }
    @FXML
    public void Cancel(){ gameUIController.SwitchToActionSidePanel(); }

    @Override
    public void Refresh() {
        PlayerShip curr = gameUIController.getGameController().getCurrentPlayer();
        BillCreator bc = BillCreator.GetInstance();
        if(bc.CreateForRobot(curr.getMaterials()) == null){
            RobotButton.setEffect(new ColorAdjust(0, 1, 0, 0));
        } else{
            RobotButton.setEffect(null);
        }

        if(bc.CreateForTeleport(curr.getMaterials()) == null){
            TeleportsButton.setEffect(new ColorAdjust(0, 1, 0, 0));
        } else{
            TeleportsButton.setEffect(null);
        }

        if(bc.CreateForBaseFoundation(curr.getMaterials()) == null){
            BaseButton.setEffect(new ColorAdjust(0, 1, 0, 0));
        } else{
            BaseButton.setEffect(null);
        }
    }
}
