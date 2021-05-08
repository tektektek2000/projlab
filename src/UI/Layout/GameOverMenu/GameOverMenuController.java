package UI.Layout.WonMenu;

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

public class GameOverMenuController {
    public Stage stage;
    @FXML
    AnchorPane Anchor;
    @FXML
    Button Restart_button;
    @FXML
    Button Exit_button;

    public GameOverMenuController(Stage s){
        stage = s;
    }

    public void setAnchor(AnchorPane a){
        Anchor = a;
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
        stage.setFullScreen(true);
        mainMenuController.Init();
    }
    @FXML
    public void Exit(){
        stage.close();
    }
}
