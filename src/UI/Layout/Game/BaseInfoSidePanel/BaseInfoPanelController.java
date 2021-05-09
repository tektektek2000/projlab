package UI.Layout.Game.BaseInfoSidePanel;

import Model.Base;
import Model.Map;
import Model.Materials.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class BaseInfoPanelController {
    @FXML
    Label Coal;
    @FXML
    Label Iron;
    @FXML
    Label Ice;
    @FXML
    Label Uranium;

    public void Show(Base base){
        ArrayList<Material> inv = base.getMaterials();
        BillCreator bc = BillCreator.GetInstance();
        Coal.setText(Integer.toString(bc.Count(inv, new Coal(new Map()))));
        Iron.setText(Integer.toString(bc.Count(inv, new Iron(new Map()))));
        Ice.setText(Integer.toString(bc.Count(inv, new Ice(new Map()))));
        Uranium.setText(Integer.toString(bc.Count(inv, new Uranium(new Map()))));
    }

}
