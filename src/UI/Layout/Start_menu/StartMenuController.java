package UI.Layout.Start_menu;

import Model.PlayerShip;
import UI.Components.SelectHandler;
import UI.Layout.Load_menu.LoadMenuController;
import UI.Layout.Main_menu.MainMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    }
    @FXML
    public void Start(){
        System.out.println("Start");
    }
    @FXML
    public void Back(){
        MainMenuController mainMenuController = new MainMenuController(stage);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(mainMenuController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/Main_menu/Main_menuLayout.fxml"));
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
    public void Small(){
    }
    @FXML
    public void Normal(){
    }
    @FXML
    public void Large(){
    }
}
