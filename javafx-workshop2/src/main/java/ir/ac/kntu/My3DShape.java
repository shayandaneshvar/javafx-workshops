package ir.ac.kntu;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

/**
 * Triangle Mesh is needed
 * Mesh view class
 * <p>
 * triangle mesh needs data for 3 aspects of 3D object:
 * + texture coordinates
 * 2D object mapped to the vertices of the triangles
 * + points
 * vertices of triangles in the mesh
 * + faces
 * planes created by joining these vertices
 */

public class My3DShape {
    public static MeshView getShape() {
        TriangleMesh pyramidMesh = new TriangleMesh();
        pyramidMesh.getTexCoords().addAll(0,0);
        pyramidMesh.getPoints()
                .addAll(
                        0, 0, 0,//top
                        0, 200, -150,//front
                        -150, 200, 0,//left
                        150, 200, 0,//back
                        0, 200, 150//right
                );
        pyramidMesh.getFaces().addAll(
                0, 0, 2, 0, 1, 0, //front left
                0, 0, 1, 0, 3, 0, //front right
                0, 0, 3, 0, 4, 0, //back right
                0, 0, 4, 0, 2, 0, //back left
                4, 0, 1, 0, 2, 0, //bottom rear
                4, 0, 3, 0, 1, 0 //bottom front
        );
        MeshView meshView = new MeshView(pyramidMesh);
        meshView.setDrawMode(DrawMode.FILL);
        PhongMaterial phongMaterial = new PhongMaterial();
        phongMaterial.setDiffuseColor(Color.GOLD);
        phongMaterial.setSpecularColor(Color.RED);
        meshView.setMaterial(phongMaterial);
        meshView.setTranslateX(400);
        meshView.setTranslateY(250);
        meshView.setTranslateZ(50);
        meshView.setOnMouseClicked(e-> meshView.setRotate(meshView.getRotate() + 20));
        return meshView;
    }
}
