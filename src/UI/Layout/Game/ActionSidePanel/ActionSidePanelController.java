package UI.Layout.Game.ActionSidePanel;

import Model.Materials.BillCreator;
import Model.PlayerShip;
import UI.Components.SelectHandler;
import UI.Layout.Game.GameUIController;
import UI.Layout.Game.ISidePanelController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;

import java.awt.*;

public class ActionSidePanelController implements ISidePanelController {
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
        Refresh();
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
        try {
            gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " skip");
            gameUIController.SwitchToInventory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Refresh(){
        PlayerShip curr = gameUIController.getGameController().getCurrentPlayer();
        BillCreator bc = BillCreator.GetInstance();
        if(curr != null && curr.getAsteroid() != null){

            if(curr.getAsteroid().GetShell() == 0){
                DrillButton.setEffect(new ColorAdjust(0,1,0, 0));
            } else{
                DrillButton.setEffect(null);
            }

            if(curr.getAsteroid().GetCore() == null || curr.getAsteroid().GetShell() != 0){
                MineButton.setEffect(new ColorAdjust(0, 1, 0, 0));
            } else{
                MineButton.setEffect(null);
            }

            if(curr.getAsteroid().GetBase() == null){
                BuildButton.setEffect(new ColorAdjust(0, 1, 0, 0));
            } else{
                BuildButton.setEffect(null);
            }

            if(bc.CreateForRobot(curr.getMaterials()) == null && bc.CreateForTeleport(curr.getMaterials()) == null && bc.CreateForBaseFoundation(curr.getMaterials()) == null){
                CraftButton.setEffect(new ColorAdjust(0, 1, 0, 0));
            } else{
                CraftButton.setEffect(null);
            }

            if(curr.getMaterials().size() == 0 || curr.getAsteroid().GetShell() != 0 || curr.getAsteroid().GetCore() != null){
                PutBackButton.setEffect(new ColorAdjust(0, 1, 0, 0));
            } else{
                PutBackButton.setEffect(null);
            }

            if(curr.getTeleports().size() == 0){
                PutDownButton.setEffect(new ColorAdjust(0, 1, 0, 0));
            } else{
                PutDownButton.setEffect(null);
            }
        }
    }
}
