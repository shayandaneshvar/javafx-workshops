package ir.ac.kntu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //shapes have material,draw mode and cull face
    //material => color
    // draw mode => how is shape rendered
    // cull face => technique to only render visible parts
    //triangles
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        root.setStyle("-fx-background-color: wheat");
        Scene scene = new Scene(root, 600, 800, true,
                SceneAntialiasing.BALANCED);
        stage.setScene(scene);
        Camera camera = new ParallelCamera();
        scene.setCamera(camera);
        Circle circle = new Circle(100, 100, 100, Color.hsb(0.5, 1, 0.2));
        circle.setTranslateX(100);
        circle.setTranslateY(200);
        circle.setFill(Color.RED);
        Box box = new Box(40, 50, 40);
//        box.setCullFace(CullFace.FRONT);
        box.setOnMouseClicked((e) -> box.setCullFace(CullFace.BACK));
        box.setLayoutX(300);
        box.setLayoutX(400);
        box.setMaterial(new PhongMaterial(Color.YELLOWGREEN));
        Sphere sphere = new Sphere(50);
        sphere.setDrawMode(DrawMode.LINE);
        sphere.setTranslateX(40);
        sphere.setTranslateY(60);
        sphere.setMaterial(new PhongMaterial(Color.MAGENTA));

        MeshView duck = FXMLLoader.load(getClass().getResource("/Duck.fxml"));
        TriangleMesh headRaw = FXMLLoader.load(getClass().getResource(
                "/CylinderHead.fxml"));
        MeshView head = new MeshView(headRaw);
        Group scooter = FXMLLoader.load(getClass().getResource("/Scooter" +
                ".fxml"));
        Group tux = FXMLLoader.load(getClass().getResource("/TuxRotation" +
                ".fxml"));
        tux.setScaleX(100);
        tux.setScaleY(100);
        tux.setScaleY(100);
        head.setScaleX(100);
        head.setScaleY(100);
        head.setScaleZ(100);
        head.setTranslateY(400);
        head.setTranslateX(500);
        tux.setTranslateX(300);
        tux.setTranslateY(100);
        tux.setTranslateZ(100);
        PointLight pointLight = new PointLight(Color.WHEAT);
        pointLight.setTranslateX(20);
        pointLight.setTranslateY(120);
        pointLight.setTranslateZ(110);
        AmbientLight light = new AmbientLight(Color.hsb(0.8, 0.3, 0.81));
        System.out.println(((TriangleMesh) duck.meshProperty().get()).getPoints().size());
        System.out.println(((TriangleMesh) duck.meshProperty().get()).getFaceSmoothingGroups().size());
        System.out.println(((TriangleMesh) duck.meshProperty().get()).getFaces().size());
        System.out.println(((TriangleMesh) duck.meshProperty().get()).getTexCoords().size());

        root.getChildren().addAll(circle, sphere, box, My3DShape.getShape()
                , duck, pointLight, light, scooter, head, tux);
        duck.setScaleX(100);
        duck.setScaleY(100);
        duck.setScaleZ(100);

        scene.setOnKeyPressed((e) -> {
            pointLight.setDisable(!pointLight.disabledProperty().get());
            if (e.getCode() == KeyCode.RIGHT) {
                camera.setLayoutX(camera.getLayoutX() + 10);
            } else if (e.getCode() == KeyCode.LEFT) {
                camera.setLayoutX(camera.getLayoutX() - 10);
            } else if (e.getCode() == KeyCode.UP) {
                camera.setLayoutY(camera.getLayoutY() - 10);
            } else if (e.getCode() == KeyCode.DOWN) {
                camera.setLayoutY(camera.getLayoutY() + 10);
            } else if (e.getCode() == KeyCode.W) {
                camera.setTranslateZ(camera.getTranslateZ() + 10);
            } else if (e.getCode() == KeyCode.S) {
                camera.setTranslateZ(camera.getTranslateZ() - 10);
            } else if (e.getCode() == KeyCode.D) {
                camera.setRotationAxis(new Point3D(1, 0, 0));
                camera.setRotate(camera.getRotate() + 10);
            }
            System.err.println("KEY PRESSED!");
        });
        scooter.setScaleX(100);
        scooter.setScaleY(100);
        scooter.setScaleZ(100);
        scooter.setTranslateX(300);
        duck.setTranslateY(-100);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            int value = 10;
            switch (e.getCode()) {
                case L:
                    value = -10;
                case J:
                    camera.setRotationAxis(new Point3D(0, 1, 0));
                    break;
                case K:
                    value *= -1;
                case I:
                    camera.setRotationAxis(new Point3D(1, 0, 0));
                    break;
                default:
                    Toolkit.getDefaultToolkit().beep();
                    return;
            }
            camera.setRotate(camera.getRotate() + value);
        });

        stage.show();
    }
}
