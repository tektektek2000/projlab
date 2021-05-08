package UI.Layout.Game;
import Controllers.GameController;
import Model.Asteroid;
import Model.Field;
import Model.Map;
import Model.Sector;
import UI.Components.Connection;
import UI.Components.FieldImage;
import UI.Components.SelectHandler;
import UI.Layout.Game.ActionSidePanel.ActionSidePanelController;
import Utils.Pair;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
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
    Pane SidePanelWrapper;
    @FXML
    Pane GameContent;

    public GameUIController(GameController gc){
        gameController = gc;
    }

    private void SwitchToActionSidePanel(){
        ActionSidePanelController actionSidePanelController = new ActionSidePanelController(this);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(actionSidePanelController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/Game/ActionSidePanel/ActionSidePanel.fxml"));
        try {
            VBox actionPanel = fxmlLoader.load();
            SidePanelWrapper.getChildren().add(actionPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        actionSidePanelController.Init();
        new SelectHandler(SaveButton);
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
