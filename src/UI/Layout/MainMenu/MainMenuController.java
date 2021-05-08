package UI.Layout.MainMenu;

import UI.Components.MagicConstants;
import UI.Layout.LoadMenu.LoadMenuController;
import UI.Layout.StartMenu.StartMenuController;
import UI.Components.SelectHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    public Stage stage;
    @FXML
    AnchorPane Anchor;
    @FXML
    Button Start_button;
    @FXML
    Button Load_button;
    @FXML
    Button Exit_button;

    public MainMenuController(Stage s){
        stage = s;
    }

    public void setAnchor(AnchorPane a){
        Anchor = a;
        Anchor.getStylesheets().clear();
        Anchor.getStylesheets().add(this.getClass().getResource("mainmenu.css").toExternalForm());
        Anchor.getStyleClass().clear();
        Anchor.getStyleClass().add("anchor");
    }

    public void Init(){
        new SelectHandler(Start_button);
        new SelectHandler(Load_button);
        new SelectHandler(Exit_button);
    }
    @FXML
    public void Start(){
        StartMenuController startMenuController = new StartMenuController(stage);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(startMenuController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/StartMenu/StartMenuLayout.fxml"));
        AnchorPane root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Anchor.getChildren().clear();
        Anchor.getChildren().addAll(root.getChildren());
        startMenuController.setAnchor(Anchor);
        stage.setFullScreen(MagicConstants.FullScreen);
        CleanUp();
        startMenuController.Init();
    }
    @FXML
    public void Load(){
        LoadMenuController loadMenuController = new LoadMenuController(stage);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(loadMenuController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/LoadMenu/LoadMenuLayout.fxml"));
        AnchorPane root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Anchor.getChildren().clear();
        Anchor.getChildren().addAll(root.getChildren());
        loadMenuController.setAnchor(Anchor);
        stage.setFullScreen(MagicConstants.FullScreen);
        CleanUp();
        loadMenuController.Init();
    }
    @FXML
    public void Exit(){
       stage.close();
    }

    public void CleanUp(){
        Anchor = null;
        Start_button = null;
        Load_button = null;
        Exit_button = null;
    }
}
