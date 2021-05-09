package UI.Layout.Game.CurrentAsteroidSidePanel;

import Model.Field;
import UI.Components.FieldImage;
import UI.Components.ImageVisitor;
import UI.Components.InfoPanelVisitor;
import UI.Components.MagicConstants;
import UI.Layout.Game.GameUIController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class CurrentAsteroidSidePanelController {
    Field shown;
    GameUIController gameUIController;
    @FXML
    VBox CurrentAsteroidPanel;
    @FXML
    TextField ShellField;
    @FXML
    AnchorPane CoreField;

    public CurrentAsteroidSidePanelController(GameUIController GUIC){
        gameUIController = GUIC;
    }

    public void Init(){

    }

    public void Show(FieldImage f){
        if(shown != f.getField()) {
            shown = f.getField();
            InfoPanelVisitor ipv = new InfoPanelVisitor(shown);
            if (ipv.isAsteroid) {
                ShellField.setText(Integer.toString(ipv.Shell));
                ImageVisitor iv = new ImageVisitor(ipv.Core);
                ImageView MaterialImage = new ImageView(iv.getImage());
                CoreField.getChildren().clear();
                MaterialImage.setPreserveRatio(true);
                MaterialImage.setFitHeight(MagicConstants.CoreInfoImageSize);
                AnchorPane.setRightAnchor(MaterialImage, 30.0 + MaterialImage.getFitWidth());
                AnchorPane.setTopAnchor(MaterialImage, (CoreField.getHeight()-MaterialImage.getFitHeight())/2);
                CoreField.getChildren().add(MaterialImage);
            } else {
                CurrentAsteroidPanel.setVisible(false);
            }
        }
    }

}
