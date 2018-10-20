package kap7;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 *
 * @author speedy
 */
public class Oppg9Sierpinsky extends Application {
    
    private final int ORDER = 12;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();

        Point2D left = new Point2D(50, 750),
                top = new Point2D(400, 50),
                right = new Point2D(750, 750);

        drawSierpinsky(root, ORDER, left, top, right);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    // Draw Sierpinsky triangle
    void drawSierpinsky(Pane p, int order, Point2D p1, Point2D p2, Point2D p3) {

        if (order == 0) {
            // Draw triangle to connect three points
            Polygon poly = new Polygon();
            poly.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
            poly.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
            poly.setFill(Color.WHITE);
            
            p.getChildren().add(poly);
        }

        else {
            // Get the midpoint on each edge in the triangle
            Point2D p12 = p1.midpoint(p2);
            Point2D p23 = p2.midpoint(p3);
            Point2D p31 = p3.midpoint(p1);
            
            // Recursively display three triangles
            drawSierpinsky(p, order - 1, p1, p12, p31);
            drawSierpinsky(p, order - 1, p12, p2, p23);
            drawSierpinsky(p, order - 1, p31, p23, p3);
        }
    }

}
