package UI.Layout.Game;
import Controllers.GameController;
import Model.Asteroid;
import Model.Field;
import Model.Map;
import Model.Sector;
import UI.Components.Connection;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class GameUIController {
    GameController gameController;
    Pair<Double,Double> Camera = new Pair<Double,Double>(0.0,0.0);
    @FXML
    AnchorPane Anchor;
    @FXML
    Button SaveButton;
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
        new SelectHandler(SaveButton);
        new SelectHandler(DrillButton);
        new SelectHandler(MineButton);
        new SelectHandler(BuildButton);
        new SelectHandler(CraftButton);
        new SelectHandler(PutBackButton);
        new SelectHandler(PutDownButton);
    }

    public void setAnchor(AnchorPane a){
        Anchor = a;
        Anchor.getStylesheets().add(this.getClass().getResource("game.css").toExternalForm());
        Anchor.getStyleClass().clear();
        Anchor.getStyleClass().add("anchor");
    }

    public void Init(){
        SwitchToActionSidePanel();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(200),
                ae -> Refresh()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private double FieldX(FieldImage image){
        double widthHalf = GameContent.getWidth()/2;
        return widthHalf + image.x * widthHalf - (image.getFitWidth()/2);
    }

    private double FieldY(FieldImage image){
        double heightHalf = GameContent.getHeight()/2;
        return heightHalf + image.y * heightHalf - (image.getFitHeight()/2);
    }

    private void PlaceImage(FieldImage image){
        double posx = FieldX(image);
        double posy = FieldY(image);
        System.out.println(image.getFitWidth());
        image.relocate(posx,posy);
        System.out.println(posx + " " + posy);
        GameContent.getChildren().add(image);
    }

    private void PlaceConnection(Connection connection){
        double widthHalf = GameContent.getWidth()/2;
        double heightHalf = GameContent.getHeight()/2;
        FieldImage f1 = new FieldImage(connection.getF1());
        double f1posx = widthHalf + f1.x * widthHalf;
        double f1posy = heightHalf + f1.y * heightHalf;
        FieldImage f2 = new FieldImage(connection.getF2());
        double f2posx = widthHalf + f2.x * widthHalf;
        double f2posy = heightHalf + f2.y * heightHalf;
        Line line = new Line(f1posx,f1posy,f2posx,f2posy);
        line.setStyle("-fx-stroke: yellow;");
        GameContent.getChildren().add(line);
    }

    @FXML
    public void Save(){
        System.out.println("Nem működik a mentés még tesókám, erre bizony alaposan rábasztál!");
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
        ArrayList<Connection> connections = new ArrayList<>();
        for(Sector s : map.getSectors()){
            for(Field f : s.getFields()){
                fieldImages.add(new FieldImage(f));
                for(Field n : f.getNeighbours()){
                    Connection c = new Connection(f,n);
                    boolean found = false;
                    for(Connection connection : connections){
                        if(connection.isSame(c)){
                            found = true;
                            break;
                        }
                    }
                    if(!found)
                        connections.add(c);
                }
            }
        }
        for(Connection c : connections){
            PlaceConnection(c);
        }
        for(FieldImage f : fieldImages){
            PlaceImage(f);
        }
    }
}
