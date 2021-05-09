package UI.Layout.StartMenu;

import Controllers.GameController;
import UI.Components.MagicConstants;
import UI.Components.SelectHandler;
import UI.Layout.Game.GameUIController;
import UI.Layout.MainMenu.MainMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartMenuController {
    public Stage stage;
    private int playerNum = 1;

    @FXML
    AnchorPane Anchor;
    @FXML
    Button Start_button;
    @FXML
    Button Back_button;
    @FXML
    Button Small_button;
    @FXML
    Button Normal_button;
    @FXML
    Button Large_button;
    @FXML
    TextField Counter_field;

    public StartMenuController(Stage s){
        stage = s;
    }

    public void setAnchor(AnchorPane a){
        Anchor = a;
    }

    public void Init(){
        new SelectHandler(Start_button);
        new SelectHandler(Back_button);
        new SelectHandler(Small_button);
        new SelectHandler(Normal_button);
        new SelectHandler(Large_button);
        Small_button.setEffect(new Glow(0.5));
    }

    @FXML
    public void Counter(){
        if(Counter_field.getText().length() == 0){
            return;
        }
        try {
            int p;
            p = Integer.parseInt(Counter_field.getText());
            if(p < 1 || p > 5){
                int uj = Character.getNumericValue(Counter_field.getText().charAt(0));
                if(uj < 1 || uj > 5){
                    throw new Exception();
                }
                p = uj;
            }
            playerNum = p;
        }
        catch (Exception e){
            System.out.println("Invalid value! Please enter a number between 1 and 5.");
        }
        Counter_field.setText(Integer.toString(playerNum));
        MagicConstants.setShipNumber(playerNum);
    }
    @FXML
    public void Start(){

        GameController gc = new GameController();
        gc.NewMap();
        GameUIController gameUIController = new GameUIController(gc, stage);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(gameUIController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/Game/GameLayout.fxml"));
        AnchorPane root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Anchor.getChildren().clear();
        Anchor.getChildren().addAll(root.getChildren());
        gameUIController.setAnchor(Anchor);
        stage.setFullScreen(MagicConstants.FullScreen);
        CleanUp();
        gameUIController.Init();

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
        stage.setFullScreen(MagicConstants.FullScreen);
        CleanUp();
        mainMenuController.Init();
    }
    @FXML
    public void Small(){
        Small_button.setEffect(new Glow(0.5));
        Normal_button.setEffect(null);
        Large_button.setEffect(null);
        MagicConstants.setMapSize(0);
    }
    @FXML
    public void Normal(){
        Small_button.setEffect(null);
        Normal_button.setEffect(new Glow(0.5));
        Large_button.setEffect(null);
        MagicConstants.setMapSize(1);
    }
    @FXML
    public void Large(){
        Small_button.setEffect(null);
        Normal_button.setEffect(null);
        Large_button.setEffect(new Glow(0.5));
        MagicConstants.setMapSize(2);
    }

    public void CleanUp(){
        Anchor = null;
        Start_button = null;
        Back_button = null;
        Small_button = null;
        Normal_button = null;
        Large_button = null;
        Counter_field = null;
    }
}
