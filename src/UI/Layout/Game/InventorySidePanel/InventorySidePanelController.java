package UI.Layout.Game.InventorySidePanel;

import Model.Materials.*;
import UI.Layout.Game.GameUIController;
import Model.Map;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
        Refresh();
    }

    public void Refresh() {
        ArrayList<Material> inv = gameUIController.getGameController().getCurrentPlayer().getMaterials();
        BillCreator bc = BillCreator.GetInstance();
        CoalNumb.setText(Integer.toString(bc.Count(inv, new Coal(new Map()))));
        IronNumb.setText(Integer.toString(bc.Count(inv, new Iron(new Map()))));
        IceNumb.setText(Integer.toString(bc.Count(inv, new Ice(new Map()))));
        UranNumb.setText(Integer.toString(bc.Count(inv, new Uranium(new Map()))));
        PortalNumb.setText(Integer.toString(gameUIController.getGameController().getCurrentPlayer().getTeleports().size()));
    }
}
