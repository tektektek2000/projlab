package UI.Layout.LoadMenu;

import UI.Components.SelectHandler;
import UI.Layout.MainMenu.MainMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadMenuController {
    public Stage stage;
    @FXML
    AnchorPane Anchor;
    @FXML
    Button Back_button;
    @FXML
    Button Load_button;
    @FXML
    Button Left_button;
    @FXML
    Button Right_button;

    public LoadMenuController(Stage s){
        stage = s;
    }

    public void setAnchor(AnchorPane a){
        Anchor = a;
    }

    public void Init(){
        new SelectHandler(Back_button);
        new SelectHandler(Load_button);
        new SelectHandler(Left_button);
        new SelectHandler(Right_button);
    }
    @FXML
    public void Back(){
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
    public void Load(){
        System.out.println("Load");
    }
    @FXML
    public void Left(){
        System.out.println("Left");
    }
    @FXML
    public void Right(){
        System.out.println("Right");
    }
}
