package UI.Layout.Game.CurrentTeleportSidePanel;

import Model.Field;
import UI.Components.FieldImage;
import UI.Components.ImageVisitor;
import UI.Components.InfoPanelVisitor;
import UI.Components.MagicConstants;
import UI.Layout.Game.ActionSidePanel.ActionSidePanelController;
import UI.Layout.Game.GameUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    public CurrentTeleportSidePanelController(GameUIController GUIC){
        gameUIController = GUIC;
    }

    public void Init(){

    }

    public void Show(FieldImage f){
        /*InfoPanelVisitor ipv = new InfoPanelVisitor(f.getField());
        if (!ipv.isAsteroid) {

            ActiveField.getChildren().clear();
            NameLabel.setText("Teleport" + f.getField().GetUID());
            SectorField.setText(Integer.toString(f.getField().getSector().GetUID()));
            if( != null) {
                ImageVisitor iv = new ImageVisitor(ipv.Core);
                ImageView MaterialImage = new ImageView(iv.getImage());
                MaterialImage.setPreserveRatio(true);
                MaterialImage.setFitHeight(MagicConstants.CoreInfoImageSize);
                AnchorPane.setRightAnchor(MaterialImage, 15.0 + MaterialImage.getFitWidth());
               // AnchorPane.setTopAnchor(MaterialImage, (CoreField.getHeight() - MaterialImage.getFitHeight()) / 2);
               // CoreField.getChildren().add(MaterialImage);
            }
        }
        else
            CurrentTeleportPanel.setVisible(false);*/
    }
}
