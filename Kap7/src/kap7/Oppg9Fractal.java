package kap7;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author speedy
 */
public class Oppg9Fractal extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        Rectangle r = new Rectangle(0, 0, 400, 400);
        r.setFill(Color.GREY);
        root.getChildren().add(r);

        drawFractal(root, 200, 200, 400);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    // Draw picture
    void drawFractal(Pane p, int xCenter, int yCenter, int boundingDim) {
        int side = boundingDim / 2;

        if (side < 1) {
            return;
        }

        // Compute corners
        int left = xCenter - side / 2;
        int top = yCenter - side / 2;
        int right = xCenter + side / 2;
        int bottom = yCenter + side / 2;

        // Recursively draw four quadrants
        drawFractal(p, left, top, boundingDim / 2);
        drawFractal(p, left, bottom, boundingDim / 2);
        drawFractal(p, right, top, boundingDim / 2);
        drawFractal(p, right, bottom, boundingDim / 2);

        // Draw central square, overlapping quadrants
        Rectangle r = new Rectangle(left, top, right - left, bottom - top);
        r.setFill(Color.WHITE);
        p.getChildren().add(r);
    }

}
