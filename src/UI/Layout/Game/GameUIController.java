package UI.Layout.Game;
import Controllers.GameController;
import Model.*;
import Controllers.NotificationManager;
import Model.Field;
import Model.Map;
import UI.Components.*;
import UI.Layout.Game.ActionSidePanel.ActionSidePanelController;
import UI.Layout.Game.CraftSidePanel.CraftSidePanelController;
import UI.Layout.Game.OptionsSidePanel.OptionsSidePanelController;
import UI.Layout.Game.PutBackSidePanel.PutBackSidePanelController;
import UI.Layout.Game.PutDownSidePanel.PutDownSidePanelController;
import UI.Layout.Game.CurrentAsteroidSidePanel.CurrentAsteroidSidePanelController;
import UI.Layout.Game.InventorySidePanel.InventorySidePanelController;
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
import javafx.scene.layout.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.crypto.spec.OAEPParameterSpec;
import javax.swing.text.Position;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class GameUIController implements EventHandler<KeyEvent> {
    ArrayList<String> CameraShift = new ArrayList<>();
    GameController gameController;
    Stage stage;
    boolean invalidState = true;
    double PaneHalf = 1920.0/2.0;
    Camera camera = new Camera(0,0,2);
    static ImageView Sun = null;
    FieldImage selected = null;
    Circle selectedCircle = null;
    Circle selectedPlayer = null;
    ArrayList<FieldImage> fieldImages = new ArrayList<>();
    ArrayList<Connection> connections = new ArrayList<>();
    @FXML
    AnchorPane Anchor;
    @FXML
    Button OptionsButton;
    @FXML
    Pane SidePanelWrapper;
    @FXML
    Pane GameContent;

    public GameUIController(GameController gc, Stage s){
        gameController = gc;
        stage = s;
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
        OptionsButton.setVisible(true);
        new SelectHandler(OptionsButton);
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
        OptionsButton.setVisible(true);
        new SelectHandler(OptionsButton);
    }

    public void SwitchToOptionsSidePanel(){
        SidePanelWrapper.getChildren().clear();
        OptionsSidePanelController optionsSidePanelController = new OptionsSidePanelController(this, stage);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(optionsSidePanelController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/Game/OptionsSidePanel/OptionsSidePanelLayout.fxml"));
        try {
            VBox optionsPanel = fxmlLoader.load();
            SidePanelWrapper.getChildren().add(optionsPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        optionsSidePanelController.setAnchor(Anchor);
        optionsSidePanelController.Init();
        OptionsButton.setVisible(false);
        new SelectHandler(OptionsButton);
    }

    public void SwitchToCurrentAsteroidSidePanel(){
        CurrentAsteroidSidePanelController currentAsteroidSidePanelController = new CurrentAsteroidSidePanelController(this);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(currentAsteroidSidePanelController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/Game/CurrentAsteroidSidePanel/CurrentAsteroidSidePanel.fxml"));
        try {
            VBox CurrentAsteroidPanel = fxmlLoader.load();
            SidePanelWrapper.getChildren().add(CurrentAsteroidPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentAsteroidSidePanelController.Init();
        new SelectHandler(OptionsButton);
    }
    public void SwitchToInventorySidePanel(){
        InventorySidePanelController inventorySidePanelController = new InventorySidePanelController(this);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(inventorySidePanelController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/Game/InventorySidePanel/InventorySidePanel.fxml"));
        try {
            GridPane InventorySidePanel = fxmlLoader.load();
            SidePanelWrapper.getChildren().add(InventorySidePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        inventorySidePanelController.Init();
        new SelectHandler(OptionsButton);
    }


    public void SwitchToPutDownSidePanel(){
        PutDownSidePanelController putDownSidePanelController = new PutDownSidePanelController(this);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(putDownSidePanelController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/Game/PutDownSidePanel/PutDownSidePanel.fxml"));
        try {
            VBox putdownPanel = fxmlLoader.load();
            SidePanelWrapper.getChildren().add(putdownPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        putDownSidePanelController.Init();
        new SelectHandler(OptionsButton);
    }

    public void SwitchToPutBackSidePanel(){
        PutBackSidePanelController putBackSidePanelController = new PutBackSidePanelController(this);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(putBackSidePanelController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/Game/PutBackSidePanel/PutBackSidePanel.fxml"));
        try {
            VBox putbackPanel = fxmlLoader.load();
            SidePanelWrapper.getChildren().add(putbackPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        putBackSidePanelController.Init();
        new SelectHandler(OptionsButton);
    }

    public GameController getGameController(){
        return gameController;
    }

    public void setAnchor(AnchorPane a){
        Anchor = a;
        Anchor.getStylesheets().clear();
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
        new SelectHandler(OptionsButton);
        Anchor.addEventHandler(KeyEvent.KEY_PRESSED,this);
        Anchor.addEventHandler(KeyEvent.KEY_RELEASED,this);
    }

    private double FieldX(double x){
        return GameContent.getWidth()/2.0 + camera.TransformX(x) * PaneHalf;
    }

    private double FieldY(double y){
        return GameContent.getHeight()/2.0 + camera.TransformY(y) * PaneHalf;
    }

    private void PositionField(FieldImage image){
        image.setFitWidth(camera.TransformWidth(image.size));
        image.setFitHeight(camera.TransformHeight(image.size));
        double posx = FieldX(image.x) - (camera.TransformWidth(image.size)/2);
        double posy = FieldY(image.y) - (camera.TransformWidth(image.size)/2);
        //System.out.println(image.getFitWidth());
        image.relocate(posx,posy);
        GridPane ships = image.getShips();
        if(ships != null) {
            double ShipPosX = FieldX(image.x) + (camera.TransformWidth(image.size) / 2);
            double ShipPosY = FieldY(image.y) - (camera.TransformHeight(image.size) / 2);
            ships.relocate(ShipPosX, ShipPosY);
            boolean selectedAdd = false;
            int x = 0;
            ShipImage selected = null;
            for (Node n : ships.getChildren()) {
                if(n.toString().equals("ShipImage")) {
                    ShipImage s = (ShipImage) n;
                    s.setFitWidth(camera.TransformWidth(s.size));
                    s.setFitHeight(camera.TransformHeight(s.size));
                    PlayerShip curr = gameController.getCurrentPlayer();
                    if (s.getShip().GetUID() == curr.GetUID()) {
                        selected = s;
                        x = GridPane.getColumnIndex(s);
                        selectedPlayer = new Circle();
                        selectedPlayer.setRadius(s.getFitWidth() / 2.0);
                        selectedPlayer.setFill(Color.WHITE);
                        selectedAdd = true;
                    }
                }
            }
            if(selectedAdd) {
                ArrayList<Node> ToBeDeleted = new ArrayList<>();
                for(Node n : ships.getChildren()){
                    if(GridPane.getColumnIndex(n) == x)
                        ToBeDeleted.add(n);
                }
                for(Node n : ToBeDeleted)
                    ships.getChildren().remove(n);
                ships.add(selectedPlayer, x, 0);
                ships.add(selected,x,0);
            }
        }
    }

    private void PlaceField(FieldImage image){
        //System.out.println(posx + " " + posy);
        GameContent.getChildren().add(image);
        image.addEventHandler(MouseEvent.ANY,new FieldImageMouseHandler(image,this));
        PositionField(image);
        GridPane ships = image.getShips();
        if(ships != null){
            GameContent.getChildren().add(ships);
            for (Node n : ships.getChildren()) {
                if(n.toString().equals("ShipImage")) {
                    ShipImage s = (ShipImage) n;
                    s.setFitWidth(camera.TransformWidth(s.size));
                    s.setFitHeight(camera.TransformHeight(s.size));
                }
            }
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
        Deselect(f);
        try {
            gameController.InterpretCommand("p " + gameController.getCurrentPlayer().GetUID() + " move " + f.getField().GetUID());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if(NotificationManager.getLastCommandSuccess()){
            Invalidate();
            NotificationManager.setLastCommandSuccess(false);
        }
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
            selectedPlayer = null;
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
            PlaceSun();
            invalidState = false;
            camera.setX(gameController.getCurrentPlayer().getAsteroid().getX());
            camera.setY(gameController.getCurrentPlayer().getAsteroid().getY());
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
