package kap7;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class main extends Application {

    public Canvas canvas;
    public Tree tree;
    public Scene scene;
    private BorderPane root;
    private HBox bottom;

    public double length = 150, angle = 60, width = 1;
    public int iterations = 8;

    @Override
    public void start(Stage primaryStage) throws Exception {

        scene = new Scene(root);
        tree.branch((int) canvas.getWidth() / 2, (int) canvas.getHeight(), angle, iterations, length);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tree");
        primaryStage.show();
    }

    @Override
    public void init() {
        canvas = new Canvas(Screen.getPrimary().getBounds().getWidth() * (4d / 5), Screen.getPrimary().getBounds().getHeight() * (4d / 5));
        tree = new Tree(canvas, canvas.getWidth() / 2, canvas.getHeight() * (19d / 20d));

        Slider angleSlider = new Slider(0, 359, 60);
        Slider widthSlider = new Slider(1, 5, 1);
        Slider iterationsSlider = new Slider(1, 10, 8);

        widthSlider.setShowTickLabels(true);
        widthSlider.setShowTickMarks(true);
        widthSlider.setMinorTickCount(0);
        widthSlider.setMajorTickUnit(1);
        widthSlider.valueProperty().addListener((e, o, n) -> {
            width = (double) n;
            tree.widthLine(width);
        });

        angleSlider.setShowTickLabels(true);
        angleSlider.setShowTickMarks(true);
        angleSlider.setMinorTickCount(0);
        angleSlider.setMajorTickUnit(45);
        angleSlider.valueProperty().addListener((e, o, n) -> {
            angle = (double) n;
            tree.branch((int) canvas.getWidth() / 2, (int) canvas.getHeight(), angle, iterations, length);
        });

        iterationsSlider.setShowTickLabels(true);
        iterationsSlider.setShowTickMarks(true);
        iterationsSlider.setMajorTickUnit(2);
        iterationsSlider.setMinorTickCount(1);
        iterationsSlider.setSnapToTicks(true);
        iterationsSlider.valueProperty().addListener((e, o, n) -> {
            iterations = (int) Math.round((double) n);
            tree.branch((int) canvas.getWidth() / 2, (int) canvas.getHeight(), angle, iterations, length);
        });
        bottom = new HBox(30);
        bottom.setPadding(new Insets(10));
        bottom.setAlignment(Pos.CENTER);
        bottom.getChildren().addAll(new Label("Angle:"), angleSlider, new Label("Iterations:"), iterationsSlider, new Label("width:"), widthSlider);
        root = new BorderPane(canvas);
        root.setBottom(bottom);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
