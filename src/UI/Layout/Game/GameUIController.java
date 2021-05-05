package UI.Layout.Game;
import UI.SelectHandler;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameUIController {
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

    public void Craft(){
        ObservableList<String> strs = CraftButton.getStylesheets();
        for(String s : strs){
            System.out.println(s);
        }
    }


    public void Drill(){

    }

}
