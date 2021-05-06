package UI.Layout.Game;
import Controllers.GameController;
import UI.Components.SelectHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameUIController {
    GameController gameController;
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

    public void Init(){
        new SelectHandler(DrillButton);
        new SelectHandler(MineButton);
        new SelectHandler(BuildButton);
        new SelectHandler(CraftButton);
        new SelectHandler(PutBackButton);
        new SelectHandler(PutDownButton);
    }

    @FXML
    public void Drill(){
        System.out.println("Drill");
    }
    @FXML
    public void Mine(){
        System.out.println("Mine");
    }
    @FXML
    public void Build(){
        System.out.println("Build");
    }
    @FXML
    public void Craft(){
        System.out.println("Craft");
    }
    @FXML
    public void PutBack(){
        System.out.println("PutBack");
    }
    @FXML
    public void PutDown(){
        System.out.println("PutDown");
    }
}
