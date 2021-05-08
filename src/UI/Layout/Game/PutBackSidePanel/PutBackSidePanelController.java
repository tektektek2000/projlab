package UI.Layout.Game.PutBackSidePanel;

import UI.Components.SelectHandler;
import UI.Layout.Game.GameUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PutBackSidePanelController {
    GameUIController gameUIController;
    @FXML
    Button CoalButton;
    @FXML
    Button IronButton;
    @FXML
    Button IceButton;
    @FXML
    Button UranButton;
    @FXML
    Button CancelButton;

    public PutBackSidePanelController(GameUIController GUIC){
        gameUIController = GUIC;
    }

    public void Init(){
        new SelectHandler(CoalButton);
        new SelectHandler(IronButton);
        new SelectHandler(IceButton);
        new SelectHandler(UranButton);
        new SelectHandler(CancelButton);
    }

    @FXML
    public void Coal(){ System.out.println("Coal"); }
    @FXML
    public void Iron(){
        System.out.println("Iron");
    }
    @FXML
    public void Ice(){
        System.out.println("Ice");
    }
    @FXML
    public void Uran(){ System.out.println("Uranium"); }
    @FXML
    public void Cancel(){ System.out.println("Cancel"); }
}
