package UI.Layout.Game.CurrentAsteroidSidePanel;

import Model.Asteroid;
import Model.Field;
import UI.Components.FieldImage;
import UI.Components.ImageVisitor;
import UI.Components.InfoPanelVisitor;
import UI.Components.MagicConstants;
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

public class CurrentAsteroidSidePanelController {
    Field shown;
    GameUIController gameUIController;
    @FXML
    Label NameLabel;
    @FXML
    VBox CurrentAsteroidPanel;
    @FXML
    TextField ShellField;
    @FXML
    AnchorPane CoreField;
    @FXML
    TextField SectorField;
    @FXML
    AnchorPane CloseField;
    static Image X = null;
    static Image checkmark = null;

    public CurrentAsteroidSidePanelController(GameUIController GUIC){
        gameUIController = GUIC;
    }

    public void Init(){

    }

    public void Show(FieldImage f){
        InfoPanelVisitor ipv = new InfoPanelVisitor(f.getField());
        if (ipv.isAsteroid) {
            CoreField.getChildren().clear();
            CloseField.getChildren().clear();
            ShellField.setText(Integer.toString(ipv.Shell));
            NameLabel.setText("Asteroid" + f.getField().GetUID());
            SectorField.setText(Integer.toString(f.getField().getSector().GetUID()));

            if(ipv.Core != null) {
                ImageVisitor iv = new ImageVisitor(ipv.Core);
                ImageView MaterialImage = new ImageView(iv.getImage());
                MaterialImage.setPreserveRatio(true);
                MaterialImage.setFitHeight(MagicConstants.CoreInfoImageSize);
                AnchorPane.setRightAnchor(MaterialImage, 15.0 + MaterialImage.getFitWidth());
                AnchorPane.setTopAnchor(MaterialImage, (CoreField.getHeight() - MaterialImage.getFitHeight()) / 2);
                CoreField.getChildren().add(MaterialImage);
            }
            if(((Asteroid) f.getField()).getSunClose()){
                try {
                    if(checkmark == null){

                        checkmark = new Image(new FileInputStream(new File("").getAbsolutePath()+"\\img\\checkmark.png"));
                    }
                    ImageView img = new ImageView(checkmark);
                    img.setPreserveRatio(true);
                    img.setFitHeight(MagicConstants.CoreInfoImageSize);
                    AnchorPane.setRightAnchor(img, 15.0 + img.getFitWidth());
                    AnchorPane.setTopAnchor(img, (CloseField.getHeight() - img.getFitHeight()) / 2);
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
                    AnchorPane.setRightAnchor(img, 15.0 + img.getFitWidth());
                    AnchorPane.setTopAnchor(img, (CloseField.getHeight() - img.getFitHeight()) / 2);
                    CloseField.getChildren().add(img);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
        else
            CurrentAsteroidPanel.setVisible(false);
    }
}
