package UI.Layout.Main_menu;

import UI.Layout.Load_menu.LoadMenuController;
import UI.Layout.Start_menu.StartMenuController;
import javafx.collections.ObservableList;
import UI.Components.SelectHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.System.exit;
import static java.lang.System.load;

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
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/Start_menu/Start_menuLayout.fxml"));
        AnchorPane root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Anchor.getChildren().clear();
        Anchor.getChildren().addAll(root.getChildren());
        startMenuController.setAnchor(Anchor);
        stage.setFullScreen(true);
        startMenuController.Init();
    }
    @FXML
    public void Load(){
        LoadMenuController loadMenuController = new LoadMenuController(stage);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(loadMenuController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/Load_menu/Load_menuLayout.fxml"));
        AnchorPane root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Anchor.getChildren().clear();
        Anchor.getChildren().addAll(root.getChildren());
        loadMenuController.setAnchor(Anchor);
        stage.setFullScreen(true);
        loadMenuController.Init();
    }
    @FXML
    public void Exit(){
       stage.close();
    }
}
