package UI.Layout.Game.OptionsSidePanel;

import UI.Components.MagicConstants;
import UI.Components.SelectHandler;
import UI.Layout.Game.GameUIController;
import UI.Layout.MainMenu.MainMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import static java.lang.System.exit;


public class OptionsSidePanelController {

    GameUIController gameUIController;
    Stage stage;
    AnchorPane Anchor;
    @FXML
    Button SaveButton;
    @FXML
    Button MenuButton;
    @FXML
    Button ExitButton;
    @FXML
    Button CancelButton;

    public OptionsSidePanelController(GameUIController GUIC, Stage s){
        gameUIController = GUIC;
        stage = s;
    }

    public void setAnchor(AnchorPane a){
        Anchor = a;
    }

    public void Init(){
        new SelectHandler(SaveButton);
        new SelectHandler(MenuButton);
        new SelectHandler(ExitButton);
        new SelectHandler(CancelButton);
    }

    @FXML
    public void Save(){
        System.out.println("Save");
    }
    @FXML
    public void Menu(){
        MainMenuController mainMenuController = new MainMenuController(stage);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(mainMenuController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/MainMenu/MainMenuLayout.fxml"));
        AnchorPane root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Anchor.getChildren().clear();
        Anchor.getChildren().addAll(root.getChildren());
        mainMenuController.setAnchor(Anchor);
        stage.setFullScreen(MagicConstants.FullScreen);
        mainMenuController.Init();
    }
    @FXML
    public void Exit(){
        stage.close();
    }
    @FXML
    public void Cancel(){ gameUIController.SwitchToActionSidePanel(); }
}
