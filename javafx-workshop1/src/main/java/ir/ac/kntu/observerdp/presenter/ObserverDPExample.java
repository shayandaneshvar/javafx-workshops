package ir.ac.kntu.observerdp.presenter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ObserverDPExample extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 800, Color.AZURE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Soldier");
        primaryStage.show();
        new Presenter(primaryStage, scene, root);
    }
}
