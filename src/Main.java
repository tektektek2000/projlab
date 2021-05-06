import Controllers.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import UI.Layout.Game.GameUIController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GameController gc = new GameController();
        gc.NewMap();
        GameUIController gameUIController = new GameUIController(gc);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(gameUIController);
        fxmlLoader.setLocation(getClass().getResource("/UI/Layout/Game/GameLayout.fxml"));
        Parent root = null;
        root = fxmlLoader.load();
        primaryStage.setTitle("Projlab");
        Scene scene = new Scene(root, 1600, 800);
        primaryStage.setScene(scene);
        gameUIController.Init();
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
