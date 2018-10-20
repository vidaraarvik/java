package kap7;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class FractalTreeExample extends Application {

    @Override
    public void start(Stage primaryStage) {

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        TextField tfOrder = new TextField();
        hBox.getChildren().addAll(new Label("Antall greiner "), tfOrder);

        TreePane pane = new TreePane();
        tfOrder.setOnAction(e -> pane.setDepth(Integer.parseInt(tfOrder.getText())));

        BorderPane bp = new BorderPane();
        bp.setBottom(hBox);
        bp.setCenter(pane);

        Scene scene = new Scene(bp, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    static class TreePane extends Pane {

        private int depth = 0;
        private double angleFactor = 0.5;
        private double sizeFactor = 0.5;

        public void setDepth(int depth) {
            this.depth = depth;
            draw();
        }

        public void draw() {
            getChildren().clear();
            drawBranch(depth, getWidth() / 2, getHeight(), getHeight() / 2, Math.PI / 2);
        }

        public void drawBranch(int depth, double x1, double y1, double length, double angle) {

            if (depth >= 0) {

                double x2 = x1 + Math.cos(angle) * length;
                double y2 = y1 - Math.sin(angle) * length;

                getChildren().add(new Line(x1, y1, x2, y2));

                drawBranch(depth - 1, x2, y2, length * sizeFactor, angle + angleFactor);
                drawBranch(depth - 1, x2, y2, length * sizeFactor, angle - angleFactor);
            }
        }
    }
}
