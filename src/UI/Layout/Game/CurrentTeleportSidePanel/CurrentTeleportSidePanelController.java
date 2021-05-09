package UI.Layout.Game.CurrentTeleportSidePanel;

import Model.Asteroid;
import Model.Field;
import Model.TeleportGate;
import UI.Components.FieldImage;
import UI.Components.ImageVisitor;
import UI.Components.InfoPanelVisitor;
import UI.Components.MagicConstants;
import UI.Layout.Game.ActionSidePanel.ActionSidePanelController;
import UI.Layout.Game.GameUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CurrentTeleportSidePanelController {
    Field shown;
    GameUIController gameUIController;
    @FXML
    Label NameLabel;
    @FXML
    VBox CurrentTeleportPanel;
    @FXML
    AnchorPane ActiveField;
    @FXML
    AnchorPane CrazyField;
    @FXML
    TextField SectorField;
    @FXML
    AnchorPane CloseField;
    static Image X = null;
    static Image checkmark = null;

    public CurrentTeleportSidePanelController(GameUIController GUIC){
        gameUIController = GUIC;
    }

    public void Init(){

    }

    public void Show(FieldImage f){
        InfoPanelVisitor ipv = new InfoPanelVisitor(f.getField());
        if (!ipv.isAsteroid) {

            ActiveField.getChildren().clear();
            CrazyField.getChildren().clear();
            CloseField.getChildren().clear();
            NameLabel.setText("Teleport" + f.getField().GetUID());
            SectorField.setText(Integer.toString(f.getField().getSector().GetUID()));
            if(ipv.isActive) {
                try {
                    if(checkmark == null){
                        checkmark = new Image(new FileInputStream(new File("").getAbsolutePath()+"\\img\\checkmark.png"));
                    }
                    ImageView img = new ImageView(checkmark);
                    img.setPreserveRatio(true);
                    img.setFitHeight(MagicConstants.CoreInfoImageSize);
                    AnchorPane.setRightAnchor(img, 15.0);
                    AnchorPane.setTopAnchor(img, 9.0);
                    ActiveField.getChildren().add(img);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if(X == null){
                        X = new Image(new FileInputStream(new File("").getAbsolutePath()+"\\img\\x.png"));
                    }
                    ImageView img = new ImageView(X);
                    img.setPreserveRatio(true);
                    img.setFitHeight(MagicConstants.CoreInfoImageSize);
                    AnchorPane.setRightAnchor(img, 15.0);
                    AnchorPane.setTopAnchor(img, 9.0);
                    ActiveField.getChildren().add(img);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            if(ipv.isCrazy) {
                try {
                    if(checkmark == null){
                        checkmark = new Image(new FileInputStream(new File("").getAbsolutePath()+"\\img\\checkmark.png"));
                    }
                    ImageView img = new ImageView(checkmark);
                    img.setPreserveRatio(true);
                    img.setFitHeight(MagicConstants.CoreInfoImageSize);
                    AnchorPane.setRightAnchor(img, 15.0);
                    AnchorPane.setTopAnchor(img, 9.0);
                    CrazyField.getChildren().add(img);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if(X == null){
                        X = new Image(new FileInputStream(new File("").getAbsolutePath()+"\\img\\x.png"));
                    }
                    ImageView img = new ImageView(X);
                    img.setPreserveRatio(true);
                    img.setFitHeight(MagicConstants.CoreInfoImageSize);
                    AnchorPane.setRightAnchor(img, 15.0);
                    AnchorPane.setTopAnchor(img, 9.0);
                    CrazyField.getChildren().add(img);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            if(((TeleportGate) f.getField()).getSunClose()){
                try {
                    if(checkmark == null){

                        checkmark = new Image(new FileInputStream(new File("").getAbsolutePath()+"\\img\\checkmark.png"));
                    }
                    ImageView img = new ImageView(checkmark);
                    img.setPreserveRatio(true);
                    img.setFitHeight(MagicConstants.CoreInfoImageSize);
                    AnchorPane.setRightAnchor(img, 15.0 );
                    AnchorPane.setTopAnchor(img, 9.0);
                    CloseField.getChildren().add(img);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    if(X == null){
                        X = new Image(new FileInputStream(new File("").getAbsolutePath()+"\\img\\x.png"));
                    }
                    ImageView img = new ImageView(X);
                    img.setPreserveRatio(true);
                    img.setFitHeight(MagicConstants.CoreInfoImageSize);
                    AnchorPane.setRightAnchor(img, 15.0);
                    AnchorPane.setTopAnchor(img, 9.0);
                    CloseField.getChildren().add(img);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        else
            CurrentTeleportPanel.setVisible(false);
    }

    public void CleanUp(){
        gameUIController = null;
        NameLabel = null;
        CurrentTeleportPanel = null;
        ActiveField = null;
        CrazyField = null;
        SectorField = null;
        CloseField = null;
    }
}
