package UI.Layout.WonMenu;

import UI.Components.MagicConstants;
import UI.Components.SelectHandler;
import UI.Layout.LoadMenu.LoadMenuController;
import UI.Layout.MainMenu.MainMenuController;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WonMenuController {
    public Stage stage;
    @FXML
    AnchorPane Anchor;
    @FXML
    Button Restart_button;
    @FXML
    Button Exit_button;

    public WonMenuController(Stage s){
        stage = s;
    }

    public void setAnchor(AnchorPane a){
        Anchor = a;
        Anchor.getStylesheets().clear();
        Anchor.getStylesheets().add(this.getClass().getResource("wonmenu.css").toExternalForm());
        Anchor.getStyleClass().clear();
        Anchor.getStyleClass().add("anchor");
    }

    public void Init(){
        new SelectHandler(Restart_button);
        new SelectHandler(Exit_button);
    }

    @FXML
    public void Restart(){
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
}
