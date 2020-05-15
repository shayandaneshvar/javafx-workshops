package ir.ac.kntu.shapes;

import ir.ac.kntu.My3DShape;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class ShapesDemo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 1000, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.WHEAT);
        stage.setScene(scene);
        Sphere sphere = new Sphere(50);
        Box box = new Box(50, 90, 100);
        Cylinder cylinder = new Cylinder(50, 200);
        root.getChildren().addAll(sphere, box, cylinder);
        sphere.setTranslateX(300);
        sphere.setTranslateY(500);
        sphere.setTranslateZ(10);
        cylinder.setTranslateX(500);
        cylinder.setTranslateY(500);
        PerspectiveCamera camera = new PerspectiveCamera();
        System.err.println(camera.getFarClip());
        System.err.println(camera.getNearClip());
        scene.setCamera(camera);

        Box box1 = new Box(10, 20, 40);
        Lighting lighting = new Lighting();
        Light.Point light = new Light.Point();
        light.setColor(Color.BLACK);
        lighting.setLight(light);
//        light.setDisable(true);
//        light.setTranslateX(700);
//        light.setTranslateY(500);
//        light.setTranslateZ(100);

//        AmbientLight light = new AmbientLight();

        box.setTranslateZ(5);
        box1.setTranslateX(700);
        box1.setTranslateY(500);
        box.setTranslateX(700);
        box.setTranslateY(500);
        stage.show();
        Point3D point3D = new Point3D(1, 0, 0);
        cylinder.setRotationAxis(point3D);
        cylinder.setRotate(45);
        root.getChildren().addAll(box1);
        new AnimationTimer() {
            int rotateSphere = 0;
            Point3D axis = new Point3D(0, 1, 0);

            @Override
            public void handle(long l) {
//                sphere.getTransforms().add(new Rotate(++rotateSphere,
//                        axis));
//                light.setRotationAxis(axis);
//                light.setRotate(rotateSphere);
                ++rotateSphere;
                box1.setRotationAxis(axis);
                box1.setRotate(rotateSphere);
                box.setRotationAxis(axis);
                box.setRotate(rotateSphere);
                sphere.setTranslateZ(sphere.getTranslateZ() + 5);
            }
        }.start();
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.RED);
//        material.setSpecularColor(Color.WHITE);
//        material.setSpecularPower(1.0);
        cylinder.setMaterial(material);


        PhongMaterial material1 = new PhongMaterial();
        material1.setDiffuseMap(new Image(getClass().getResource("/container" +
                ".png").toString()));
        material1.setSpecularMap(new Image(getClass().getResource("/spec" +
                ".png").toString()));
//        material1.setSpecularColor(Color.BLACK);
        material1.setBumpMap(new Image(getClass().getResource("/bump.png").toString()));
        box.setMaterial(material1);

        box.setDrawMode(DrawMode.LINE);
        box.setCullFace(CullFace.BACK);
        camera.setFarClip(200);

        scene.addEventFilter(ScrollEvent.SCROLL, e -> {
            camera.setFarClip(camera.getFarClip() + e.getDeltaY());
            System.out.println(camera.getFarClip());
        });

        Box box2 = new Box(200, 200, 200);
        box2.setTranslateZ(300);
        box2.setTranslateX(900);
        box2.setTranslateY(10);
        root.getChildren().addAll(box2);
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    camera.setFieldOfView(camera.getFieldOfView() + 10);
                    cylinder.setCullFace(CullFace.NONE);
                    break;
                case SPACE:
                    camera.setFieldOfView(camera.getFieldOfView() - 10);
                    cylinder.setCullFace(CullFace.BACK);
                    break;
                case SHIFT:
                    camera.setVerticalFieldOfView(!camera.verticalFieldOfViewProperty().get());
                    cylinder.setCullFace(CullFace.FRONT);
                    break;
            }


        });

        PhongMaterial material2 = new PhongMaterial();
//        material2.setDiffuseMap(new Image("file:///src/main" +
//                "/resources/container" +
//                ".png"));
        material2.setDiffuseColor(Color.GOLD);
        sphere.setDrawMode(DrawMode.LINE);
//        sphere.setCullFace(CullFace.BACK);//?
        sphere.setMaterial(material2);
        MeshView mesh = My3DShape.getShape();
        root.getChildren().addAll(mesh);
        mesh.setTranslateY(400);
        mesh.setTranslateX(700);
        box2.setTranslateY(400);
        PhongMaterial material3 = new PhongMaterial();
        material3.setSelfIlluminationMap(new Image(getClass().getResource(
                "/illu" +
                        ".png").toString()));
        box2.setMaterial(material3);
        box2.setEffect(lighting);
    }

}
