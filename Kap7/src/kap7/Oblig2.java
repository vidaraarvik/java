package kap7;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Program that draws a fractal tree using a recursive method
 *
 * @author Vidar Årvik
 */
public class Oblig2 extends Application {

    @Override
    public void start(Stage primaryStage) {

        BorderPane bp = new BorderPane();
        TreePane pane = new TreePane();
        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER);

        TextField tfDepth = new TextField();
        tfDepth.setOnAction(e -> pane.setDepth(Integer.parseInt(tfDepth.getText())));

        // Slider for setting the branch angle
        Slider angleSlider = new Slider();
        angleSlider.setMin(0);
        angleSlider.setMax(100);
        angleSlider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            pane.setFactorAngle((double) newValue / 100);
        });
        // Slider for setting the branch length
        Slider lengthSlider = new Slider();
        lengthSlider.setMin(0);
        lengthSlider.setMax(100);
        lengthSlider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            pane.setFactorLength((double) newValue / 100);
        });
        // Slider for setting the randomness value
        Slider randomSlider = new Slider();
        randomSlider.setMin(0);
        randomSlider.setMax(100);
        randomSlider.setDisable(true);
        randomSlider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            pane.setRandomVal((double) newValue);
        });

        // Checkbox for randomizing length and angle
        CheckBox cb = new CheckBox("Random");
        cb.setOnAction(e -> {
            if (cb.isSelected()) {
                angleSlider.setDisable(true);
                lengthSlider.setDisable(true);
                randomSlider.setDisable(false);
                
                pane.setRandom(true);
            } else {
                angleSlider.setDisable(false);
                lengthSlider.setDisable(false);
                randomSlider.setDisable(true);
                
                pane.setRandom(false);
            }
        });
        
        hBox.getChildren().addAll(new Label("Set depth of tree: (Press enter to update tree) "), tfDepth, new Label("Angle: "), angleSlider, new Label("Length: "), lengthSlider, cb, randomSlider);

        bp.setCenter(pane);
        bp.setTop(hBox);

        Scene scene = new Scene(bp, 1280, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Class that represents a Pane in which the tree will be drawn
     */
    private static class TreePane extends Pane {

        private int depth = 0;
        private boolean random = false;
        private double randomVal = 100;
        private double factorAngle = 0.5;
        private double factorLength = 0.5;

        /**
         * Sets the depth of the tree
         *
         * @param depth
         */
        public void setDepth(int depth) {
            this.depth = depth;

            draw();
        }

        /**
         * Sets the angle of the branch
         *
         * @param factorAngle
         */
        public void setFactorAngle(double factorAngle) {
            this.factorAngle = factorAngle;

            draw();
        }

        /**
         * Sets the length of the branch
         *
         * @param factorLength
         */
        public void setFactorLength(double factorLength) {
            this.factorLength = factorLength;

            draw();
        }

        /**
         * Turns random length and angle on/off
         *
         * @param random
         */
        public void setRandom(boolean random) {
            this.random = random;

            draw();
        }

        /**
         * Sets the randomness of the tree
         * @param randomVal 
         */
        public void setRandomVal(double randomVal) {
            this.randomVal = randomVal;
            
            draw();
        }
        
        

        private void draw() {
            this.getChildren().clear();

            drawBranch(depth, this.getWidth() / 2, this.getHeight(), this.getHeight() / 5, Math.PI / 2);
        }

        /**
         * Draws a new branch with start position x1 y1
         *
         * @param depth The depth value of the branch
         * @param x1 X start position
         * @param y1 Y start position
         * @param length Branch length
         * @param angle Branch angle
         */
        private void drawBranch(int depth, double x1, double y1, double length, double angle) {
            if (depth >= 0) {
                // Calculate x2 and y2..
                double x2 = x1 + Math.cos(angle) * length;
                double y2 = y1 - Math.sin(angle) * length;

                // Draw line from xy to x2y2
                this.getChildren().add(new Line(x1, y1, x2, y2));

                if (!random) {
                    // Recursively draw lines from x2y2
                    drawBranch(depth - 1, x2, y2, length * factorLength, angle + factorAngle);
                    drawBranch(depth - 1, x2, y2, length * factorLength, angle - factorAngle);
                } else {
                    // Recursively draw lines from x2y2
                    // ..with some randomness
                    drawBranch(depth - 1, x2, y2, length * ((Math.random() * 100) / 100), angle + ((Math.random() * randomVal) / 100));
                    drawBranch(depth - 1, x2, y2, length * ((Math.random() * 100) / 100), angle - ((Math.random() * randomVal) / 100));
                }
            }
        }

    }

}
