package ir.ac.kntu.observerdp.presenter;

import ir.ac.kntu.observerdp.model.Observable;
import ir.ac.kntu.observerdp.model.Soldier;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Presenter implements Observer {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Rectangle rectangle;

    public Presenter(Stage stage, Scene scene, Parent root) {
        this.stage = stage;
        this.scene = scene;
        this.root = root;
        rectangle = new Rectangle(100, 200, 200, 100);
        rectangle.setFill(Color.RED);
        rectangle.setArcHeight(50);
        rectangle.setArcWidth(60);
        ((StackPane) root).getChildren().add(rectangle);
        Soldier soldier = new Soldier("Soldier");
        soldier.addObserver(this);
        rectangle.setOnMouseClicked(event -> soldier.getHurt(10));
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof Soldier) {
            Soldier soldier = (Soldier) observable;
            rectangle.setOpacity(rectangle.getOpacity() - 0.1);
        }
    }
}
