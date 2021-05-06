package UI.Layout.Game;
import Controllers.GameController;
import Model.Asteroid;
import Model.Field;
import Model.Map;
import Model.Sector;
import UI.Components.FieldImage;
import UI.Components.SelectHandler;
import Utils.Pair;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class GameUIController {
    GameController gameController;
    Pair<Double,Double> Camera = new Pair<Double,Double>(0.0,0.0);
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
    @FXML
    Pane SidePanelWrapper;
    @FXML
    Pane GameContent;

    public GameUIController(GameController gc){
        gameController = gc;
    }

    private void SwitchToActionSidePanel(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(this);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/Game/ActionSidePanel.fxml"));
        try {
            VBox actionPanel = fxmlLoader.load();
            SidePanelWrapper.getChildren().add(actionPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new SelectHandler(DrillButton);
        new SelectHandler(MineButton);
        new SelectHandler(BuildButton);
        new SelectHandler(CraftButton);
        new SelectHandler(PutBackButton);
        new SelectHandler(PutDownButton);
    }

    public void Init(){
        SwitchToActionSidePanel();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(200),
                ae -> Refresh()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    private void PlaceImage(FieldImage image){
        double widthHalf = GameContent.getWidth()/2;
        double heightHalf = GameContent.getHeight()/2;
        double posx = widthHalf + image.x * widthHalf - (image.getFitWidth()/2);
        double posy = heightHalf + image.y * heightHalf - (image.getFitHeight()/2);
        System.out.println(image.getFitWidth());
        image.relocate(posx,posy);
        System.out.println(posx + " " + posy);
        GameContent.getChildren().add(image);
    }

    @FXML
    public void Drill(){
        PlaceImage( new FieldImage(new Asteroid(gameController.getMap().getSectors().get(0))));
    }
    @FXML
    public void Mine(){
        System.out.println("Mine");
    }
    @FXML
    public void Build(){
        System.out.println("Build");
    }
    @FXML
    public void Craft(){
        System.out.println("Craft");
    }
    @FXML
    public void PutBack(){
        System.out.println("PutBack");
    }
    @FXML
    public void PutDown(){
        System.out.println("PutDown");
    }

    public void Refresh(){
        System.out.println("Timer activated");
        Map map = gameController.getMap();
        GameContent.getChildren().clear();
        ArrayList<FieldImage> fieldImages = new ArrayList<>();
        for(Sector s : map.getSectors()){
            for(Field f : s.getFields()){
                fieldImages.add(new FieldImage(f));
            }
        }
        for(FieldImage f : fieldImages){
            PlaceImage(f);
        }
    }
}
