package UI.Layout.Game;
import Controllers.GameController;
import Model.Asteroid;
import Model.Field;
import Model.Map;
import Model.Sector;
import UI.Components.Camera;
import UI.Components.Connection;
import UI.Components.FieldImage;
import UI.Components.SelectHandler;
import UI.Layout.Game.ActionSidePanel.ActionSidePanelController;
import UI.Layout.Game.CraftSidePanel.CraftSidePanelController;
import Utils.Pair;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
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

public class GameUIController implements EventHandler<KeyEvent> {
    ArrayList<String> CameraShift = new ArrayList<>();
    GameController gameController;
    double PaneHalf = 1920.0/2.0;
    Camera camera = new Camera(0,0,1);
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
    private void SwitchToCraftSidePanel(){
        CraftSidePanelController craftSidePanelController = new CraftSidePanelController(this);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(craftSidePanelController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/Game/CraftSidePanel/CraftSidePanel.fxml"));
        try {
            VBox craftPanel = fxmlLoader.load();
            SidePanelWrapper.getChildren().add(craftPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        craftSidePanelController.Init();
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
                Duration.millis(17),
                ae -> Refresh()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        new SelectHandler(SaveButton);
        Anchor.addEventHandler(KeyEvent.KEY_PRESSED,this);
        Anchor.addEventHandler(KeyEvent.KEY_RELEASED,this);
    }

    private double FieldX(FieldImage image){
        return PaneHalf + camera.TransformX(image.x) * PaneHalf;
    }

    private double FieldY(FieldImage image){
        return PaneHalf + camera.TransformY(image.y) * PaneHalf;
    }

    private void PlaceImage(FieldImage image){
        image.setFitWidth(camera.TransformWidth(image.getFitWidth()));
        image.setFitHeight(camera.TransformHeight(image.getFitHeight()));
        double posx = FieldX(image) - (image.getFitWidth()/2);
        double posy = FieldY(image) - (image.getFitHeight()/2);
        //System.out.println(image.getFitWidth());
        image.relocate(posx,posy);
        //System.out.println(posx + " " + posy);
        GameContent.getChildren().add(image);
    }

    private void PlaceConnection(Connection connection){
        FieldImage f1 = new FieldImage(connection.getF1());
        double f1posx = FieldX(f1);
        double f1posy = FieldY(f1);
        FieldImage f2 = new FieldImage(connection.getF2());
        double f2posx = FieldX(f2);
        double f2posy = FieldY(f2);
        Line line = new Line(f1posx,f1posy,f2posx,f2posy);
        line.setStyle("-fx-stroke: yellow;");
        GameContent.getChildren().add(line);
    }

    @FXML
    public void Save(){
        System.out.println("Nem működik a mentés még tesókám, erre bizony alaposan rábasztál!");
    }


    public void Refresh(){
        final double CamShift = 0.015;
        final double CamZoom = 0.02;
        if(GameContent.getWidth() >= GameContent.getHeight())
            PaneHalf = GameContent.getWidth()/2.0;
        else
            PaneHalf = GameContent.getHeight()/2.0;
        for(String s : CameraShift){
            if(s.equals("Left"))
                camera.setX(camera.getX()-CamShift);
            else if(s.equals("Right"))
                camera.setX(camera.getX()+CamShift);
            else if(s.equals("Up"))
                camera.setY(camera.getY()-CamShift);
            else if(s.equals("Down"))
                camera.setY(camera.getY()+CamShift);
            else if(s.equals("Zoom"))
                camera.setZoom(camera.getZoom()+CamZoom);
            else if(s.equals("Unzoom"))
                camera.setZoom(camera.getZoom()-CamZoom);
        }
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

    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getEventType() == KeyEvent.KEY_PRESSED)
            switch ((keyEvent.getCode())){
                case A:
                    if(!CameraShift.contains("Left"))
                        CameraShift.add("Left");
                    break;
                case D:
                    if(!CameraShift.contains("Right"))
                        CameraShift.add("Right");
                    break;
                case W:
                    if(!CameraShift.contains("Up"))
                        CameraShift.add("Up");
                    break;
                case S:
                    if(!CameraShift.contains("Down"))
                        CameraShift.add("Down");
                    break;
                case Q:
                    if(!CameraShift.contains("Zoom"))
                        CameraShift.add("Zoom");
                    break;
                case E:
                    if(!CameraShift.contains("Unzoom"))
                        CameraShift.add("Unzoom");
                    break;
            }
        if(keyEvent.getEventType() == KeyEvent.KEY_RELEASED)
            switch ((keyEvent.getCode())){
                case A:
                    CameraShift.remove("Left");
                    break;
                case D:
                    CameraShift.remove("Right");
                    break;
                case W:
                    CameraShift.remove("Up");
                    break;
                case S:
                    CameraShift.remove("Down");
                    break;
                case Q:
                    CameraShift.remove("Zoom");
                    break;
                case E:
                    CameraShift.remove("Unzoom");
                    break;
            }
    }
}
