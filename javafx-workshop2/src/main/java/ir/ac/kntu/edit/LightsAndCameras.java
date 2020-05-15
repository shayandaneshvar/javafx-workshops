package ir.ac.kntu.edit;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


public class LightsAndCameras extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 1000, true, SceneAntialiasing.BALANCED);
        Table table = new Table();
        table.getTransforms().add(new Rotate(15, new Point3D(0, 1, 0)));
        scene.setFill(Color.YELLOWGREEN);
        table.setTranslateX(400);
        table.setTranslateY(500);
        root.getChildren().addAll(table);
        stage.setScene(scene);
        stage.show();
        PointLight light = new PointLight();
        AmbientLight ambientLight = new AmbientLight(Color.CYAN);
        Box flashLight = new Box(20, 20, 20);
        light.translateXProperty().bindBidirectional(flashLight.translateXProperty());
        light.translateYProperty().bindBidirectional(flashLight.translateYProperty());
        light.translateZProperty().bindBidirectional(flashLight.translateZProperty());
        root.getChildren().addAll(light, ambientLight);
        light.setLightOn(true);
        root.getChildren().add(flashLight);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setFarClip(2000);
        camera.setNearClip(0.1);
        camera.setFieldOfView(60);
        camera.setTranslateZ(-1000);
        camera.setTranslateX(500);
        camera.setTranslateY(500);
        scene.setCamera(camera);
        flashLight.setTranslateX(200);
        flashLight.setTranslateY(200);

        scene.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                light.setLightOn(!light.isLightOn());
            } else if (e.getButton() == MouseButton.MIDDLE) {
                ambientLight.setLightOn(!ambientLight.isLightOn());
                ambientLight.setColor(Color.WHITE);
            } else {
                ambientLight.setColor(Color.RED);
                flashLight.setTranslateX(e.getX());
                flashLight.setTranslateY(e.getY());
            }
        });
        scene.setOnScroll(e -> flashLight.setTranslateZ(e.getDeltaY() + flashLight.getTranslateZ()));

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass()
                .getResource("/wood.jpg").toString()));
        material.setSelfIlluminationMap(new Image(getClass()
                .getResource("/illu.png").toString()));
        table.setMaterial(material);
        Sphere sphere = new Sphere(100);
        PhongMaterial mat = new PhongMaterial();
        mat.setDiffuseMap(new Image(getClass()
                .getResource("/earth.jpg").toString()));
        mat.setSpecularColor(Color.NAVY);
        sphere.setMaterial(mat);
        sphere.setTranslateX(800);
        sphere.setTranslateY(800);
        root.getChildren().addAll(sphere);

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                table.setRotationAxis(new Point3D(0, 1, 0));
                sphere.setRotationAxis(new Point3D(0, 1, 0));
                table.setRotate(table.getRotate() + 4);
                sphere.setRotate(table.getRotate() + 0.1);
            }
        }.start();
    }
}
