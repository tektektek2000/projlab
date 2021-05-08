package UI.Layout.Game;
import Controllers.GameController;
import Model.Field;
import Model.Map;
import Model.PlayerShip;
import Model.Sector;
import UI.Components.*;
import UI.Layout.Game.ActionSidePanel.ActionSidePanelController;
import UI.Layout.Game.CraftSidePanel.CraftSidePanelController;
import Utils.Pair;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import javax.swing.text.Position;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class GameUIController implements EventHandler<KeyEvent> {
    ArrayList<String> CameraShift = new ArrayList<>();
    GameController gameController;
    boolean invalidState = true;
    double PaneHalf = 1920.0/2.0;
    Camera camera = new Camera(0,0,1);
    static ImageView Sun = null;
    FieldImage selected = null;
    Circle selectedCircle = null;
    Circle selectedPlayer = null;
    ArrayList<FieldImage> fieldImages = new ArrayList<>();
    ArrayList<Connection> connections = new ArrayList<>();
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

    public void SwitchToActionSidePanel(){
        SidePanelWrapper.getChildren().clear();
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
    public void SwitchToCraftSidePanel(){
        SidePanelWrapper.getChildren().clear();
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

    private double FieldX(double x){
        return PaneHalf + camera.TransformX(x) * PaneHalf;
    }

    private double FieldY(double y){
        return PaneHalf + camera.TransformY(y) * PaneHalf;
    }

    private void PositionField(FieldImage image){
        image.setFitWidth(camera.TransformWidth(image.size));
        image.setFitHeight(camera.TransformHeight(image.size));
        double posx = FieldX(image.x) - (camera.TransformWidth(image.size)/2);
        double posy = FieldY(image.y) - (camera.TransformWidth(image.size)/2);
        //System.out.println(image.getFitWidth());
        image.relocate(posx,posy);
        HBox ships = image.getShips();
        if(ships != null) {
            double ShipPosX = FieldX(image.x) + (camera.TransformWidth(image.size) / 2);
            double ShipPosY = FieldY(image.y) - (camera.TransformHeight(image.size) / 2);
            ships.relocate(ShipPosX, ShipPosY);
            for (Node n : ships.getChildren()) {
                ShipImage s = (ShipImage) n;
                s.setFitWidth(camera.TransformWidth(s.size));
                s.setFitHeight(camera.TransformHeight(s.size));
                PlayerShip curr = gameController.getCurrentPlayer();
                if(s.getShip() == curr){
                    /*GameContent.getChildren().remove(selectedCircle);
                    selectedCircle = new Circle();
                    selectedCircle.setCenterX(FieldX(selected.x));
                    selectedCircle.setCenterY(FieldY(selected.y));
                    selectedCircle.setRadius(selected.getFitWidth() / 2.0 + 5);
                    selectedCircle.setFill(Color.BLUE);
                    GameContent.getChildren().add(0, selectedCircle);*/
                }
            }
        }
    }

    private void PlaceField(FieldImage image){
        //System.out.println(posx + " " + posy);
        GameContent.getChildren().add(image);
        image.addEventHandler(MouseEvent.ANY,new FieldImageMouseHandler(image,this));
        PositionField(image);
        HBox ships = image.getShips();
        if(ships != null){
            GameContent.getChildren().add(ships);
        }
    }

    private void PlaceSun(){
        if(Sun == null){
            String filePath = new File("").getAbsolutePath();
            filePath+="\\img\\";
            filePath += "Sun.png";
            try {
                Sun = new ImageView(new Image(new FileInputStream(filePath)));
                GameContent.getChildren().add(Sun);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Sun.setFitWidth(MagicConstants.SunDiameter);
        Sun.setFitHeight(MagicConstants.SunDiameter);
        Sun.setPreserveRatio(true);
        Sun.setFitWidth(camera.TransformWidth(Sun.getFitWidth()));
        Sun.setFitHeight(camera.TransformHeight(Sun.getFitHeight()));
        double posx = FieldX(0) - (Sun.getFitWidth()/2);
        double posy = FieldY(0) - (Sun.getFitHeight()/2);
        //System.out.println(image.getFitWidth());
        Sun.relocate(posx,posy);
        //System.out.println(posx + " " + posy);
    }

    private void PositionConnection(Connection connection){
        FieldImage f1 = new FieldImage(connection.getF1());
        double f1posx = FieldX(f1.x);
        double f1posy = FieldY(f1.y);
        FieldImage f2 = new FieldImage(connection.getF2());
        double f2posx = FieldX(f2.x);
        double f2posy = FieldY(f2.y);
        connection.line.setStartX(f1posx);
        connection.line.setStartY(f1posy);
        connection.line.setEndX(f2posx);
        connection.line.setEndY(f2posy);
    }

    public void PositionSelectedCircle(){
        if(selected != null) {
            GameContent.getChildren().remove(selectedCircle);
            selectedCircle = new Circle();
            selectedCircle.setCenterX(FieldX(selected.x));
            selectedCircle.setCenterY(FieldY(selected.y));
            selectedCircle.setRadius(selected.getFitWidth() / 2.0 * 1.2);
            selectedCircle.setFill(Color.MAGENTA);
            GameContent.getChildren().add(0, selectedCircle);
        }
    }

    private void PlaceConnection(Connection connection){
        Line line = new Line();
        line.setStyle("-fx-stroke: yellow;");
        connection.line = line;
        PositionConnection(connection);
        GameContent.getChildren().add(line);
    }

    public void Move(FieldImage f){
        System.out.println("Move to " + f.getField().GetUID());
    }

    public void Select(FieldImage image){
        selected = image;
        PositionSelectedCircle();
    }

    public void Deselect(FieldImage f){
        if(f == selected) {
            selected = null;
            GameContent.getChildren().remove(selectedCircle);
        }
    }

    @FXML
    public void Save(){
        System.out.println("Nem működik a mentés még tesókám, erre bizony alaposan rábasztál!");
    }


    public void Refresh(){
        final double CamShift = 0.015;
        final double CamZoom = 0.98;
        if (GameContent.getWidth() >= GameContent.getHeight())
            PaneHalf = GameContent.getWidth() / 2.0;
        else
            PaneHalf = GameContent.getHeight() / 2.0;
        for (String s : CameraShift) {
            if (s.equals("Left"))
                camera.setX(camera.getX() - (CamShift / camera.getZoom()));
            else if (s.equals("Right"))
                camera.setX(camera.getX() + (CamShift / camera.getZoom()));
            else if (s.equals("Up"))
                camera.setY(camera.getY() - (CamShift / camera.getZoom()));
            else if (s.equals("Down"))
                camera.setY(camera.getY() + (CamShift / camera.getZoom()));
            else if (s.equals("Zoom"))
                camera.setZoom(camera.getZoom() * CamZoom);
            else if (s.equals("Unzoom"))
                camera.setZoom(camera.getZoom() / CamZoom);
        }
        if(invalidState) {
            System.out.println("Invalidated");
            Map map = gameController.getMap();
            GameContent.getChildren().clear();
            fieldImages = new ArrayList<>();
            connections = new ArrayList<>();
            for (Sector s : map.getSectors()) {
                for (Field f : s.getFields()) {
                    fieldImages.add(new FieldImage(f));
                    for (Field n : f.getNeighbours()) {
                        Connection c = new Connection(f, n);
                        boolean found = false;
                        for (Connection connection : connections) {
                            if (connection.isSame(c)) {
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                            connections.add(c);
                    }
                }
            }
            PositionSelectedCircle();
            for (Connection c : connections) {
                PlaceConnection(c);
            }
            for (FieldImage f : fieldImages) {
                PlaceField(f);
            }
            invalidState = false;
        }
        else {
            PositionSelectedCircle();
            for (Connection c : connections) {
                PositionConnection(c);
            }
            for (FieldImage f : fieldImages) {
                PositionField(f);
            }
            PlaceSun();
        }
    }

    public void Invalidate(){
        invalidState = true;
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
