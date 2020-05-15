package ir.ac.kntu.edit;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Material;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;


public class Table extends Group {
    private Box top;
    private Box p1;
    private Box p2;
    private Box p3;
    private Box p4;

    public Table() {
        top = new Box(20, 200, 120);
        p1 = new Box(90, 20, 20);
        p2 = new Box(90, 20, 20);
        p3 = new Box(90, 20, 20);
        p4 = new Box(90, 20, 20);
        p1.setTranslateY(80);
        p1.setTranslateZ(40);
        p1.setTranslateX(45);

        p2.setTranslateY(-80);
        p2.setTranslateZ(40);
        p2.setTranslateX(45);

        p3.setTranslateY(80);
        p3.setTranslateZ(-40);
        p3.setTranslateX(45);

        p4.setTranslateY(-80);
        p4.setTranslateZ(-40);
        p4.setTranslateX(45);
        getChildren().addAll(p1, p2, p3, p4, top);
        getTransforms().addAll(new Rotate(90,new Point3D(0,0,1)));
    }

    public void setMaterial(Material material){
        p1.setMaterial(material);
        p2.setMaterial(material);
        p3.setMaterial(material);
        p4.setMaterial(material);
        top.setMaterial(material);
    }
}
