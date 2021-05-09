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
                    ImageView img = new ImageView(new Image(new FileInputStream("E:/BME/4.felev/Projlab/projlab/src/UI/Layout/Game/CurrentAsteroidSidePanel/Resources/pipa.png")));
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
                    ImageView img = new ImageView(new Image(new FileInputStream("E:/BME/4.felev/Projlab/projlab/src/UI/Layout/Game/CurrentAsteroidSidePanel/Resources/x.png")));
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
