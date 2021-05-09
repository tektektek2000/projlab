package UI.Layout.Game.PutBackSidePanel;
import Model.Map;
import Model.Materials.BillCreator;
import Model.Materials.Coal;
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
    public void Coal(){
        BillCreator bc=BillCreator.GetInstance();
        try {
            gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " put_back" + bc.SearchCoal(gameUIController.getGameController().getCurrentPlayer().getMaterials()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Iron(){
        BillCreator bc=BillCreator.GetInstance();
        try {
            gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " put_back" + bc.SearchIron(gameUIController.getGameController().getCurrentPlayer().getMaterials()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Ice(){
        BillCreator bc=BillCreator.GetInstance();
        try {
            gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " put_back" + bc.SearchIce(gameUIController.getGameController().getCurrentPlayer().getMaterials()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Uran(){  BillCreator bc=BillCreator.GetInstance();
        try {
            gameUIController.getGameController().InterpretCommand("p " + gameUIController.getGameController().getCurrentPlayer().GetUID() + " put_back " + bc.SearchUranium(gameUIController.getGameController().getCurrentPlayer().getMaterials()));
        } catch (Exception e) {
            e.printStackTrace();
        } }
    @FXML
    public void Cancel(){
        gameUIController.SwitchToActionSidePanel();
    }
}
