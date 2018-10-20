package javafxapplication4;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class JavaFXApplication4 extends Application {

    private Group root;
    private double x1, y1, x2, y2;

    @Override
    public void start(Stage primaryStage) {

        root = new Group();
        Scene scene = new Scene(root, 500, 500);
        scene.setOnMouseMoved(new Lytter());
        primaryStage.setTitle("Frihåndstegning");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    class Lytter implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            x1 = x2;
            y1 = y2;
            x2 = event.getX();
            y2 = event.getY();
            Line l = new Line(x1, y1, x2, y2);
            root.getChildren().add(l);
        }
        
    }
    
}
