package tegneprogram;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 *
 * @author speedy
 */
public class TegneprogramGUI extends Application {

    // GUI
    BorderPane bp;
    Pane pane;
    HBox hBox;
    Scene scene;
    ComboBox<String> shapeCbo;
    ComboBox<Color> colorCbo;
    Label shapeInfo;
    Button newPainting, undo;

    // Modell
    int teller = -1;
    protected String shape = "";
    Color color = null;
    protected double x1, x2, y1, y2;

    @Override
    public void start(Stage primaryStage) {
        // Create the borderpane
        bp = new BorderPane();
        bp.setTop(getHBox());
        bp.setCenter(getPane());
        bp.setBottom(shapeInfo);

        // Create a scene and place it in the stage
        scene = new Scene(bp);
        primaryStage.setTitle("VidiPaint"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Pane getPane() {
        // Create VBox
        pane = new Pane();
        pane.setPadding(new Insets(12, 12, 12, 12));
        pane.setStyle("-fx-background-color: white;");
        pane.setPrefSize(600, 800);
        pane.setOnMousePressed(new StartDragHandler());
        pane.setOnMouseReleased(new EndDragHandler());
        pane.setOnMouseMoved(new PencilHandler());

        // Creates the label which holds information about the marked shape
        shapeInfo = new Label("");

        return pane;
    }

    private HBox getHBox() {
        hBox = new HBox(15);
        hBox.setPadding(new Insets(1, 12, 1, 12));
        hBox.setStyle("-fx-background-color: white;");

        // Create combobox to choose shape
        shapeCbo = new ComboBox<>();
        shapeCbo.getItems().addAll("Pencil", "Line", "Rectangle", "Circle");
        shapeCbo.setStyle("-fx-color: red");
        shapeCbo.setValue("Pencil");
        shapeCbo.setOnAction(new CboHandler());

        // Create combobox to choose color
        colorCbo = new ComboBox<>();
        colorCbo.getItems().addAll(Color.BLACK, Color.BLUE, Color.GREEN, Color.RED);
        colorCbo.setStyle("-fx-color: red");
        colorCbo.setValue(Color.BLACK);
        colorCbo.setOnAction(new CboHandler());

        // New Painting Button
        newPainting = new Button("New Painting");
        newPainting.setStyle("-fx-color: red");
        newPainting.setOnAction(new NewPaintingHandler());

        // Undo Button
        undo = new Button("Undo");
        undo.setStyle("-fx-color: red");
        undo.setOnAction(new UndoHandler());

        hBox.getChildren().addAll(
                new Label("Shape:"), shapeCbo,
                new Label("Color:"), colorCbo,
                newPainting, undo);

        return hBox;
    }

    class PencilHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (shape.equals("Pencil")) {
                x1 = x2;
                y1 = y2;
                x2 = event.getX();
                y2 = event.getY();
                if (event.isShiftDown()) {
                    Shape e = new Line(x1, y1, x2, y2);
                    pane.getChildren().add(e);
                    System.out.println("Drawing" + event.getX());
                }
            }
        }

    }

    class UndoHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (teller >= 0) {
                pane.getChildren().remove(teller);
                teller--;
            }
        }
    }

    class StartDragHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            x1 = event.getX();
            y1 = event.getY();
        }
    }

    class EndDragHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            Shape e = null;

            x2 = event.getX();
            y2 = event.getY();

            if (shape.equals("Line")) {
                e = new Line(x1, y1, x2, y2);
                e.setFill(color);
                e.setOpacity(0.5);
                e.setStrokeWidth(5);
                e.setStroke(color);
                e.setOnMouseEntered(new ShapeInfoHandler());
                pane.getChildren().add(e);
                teller++;
                return;
            }

            if (x1 > x2) {
                double tmp = x1;
                x1 = x2;
                x2 = tmp;
            }

            if (y1 > y2) {
                double tmp = y1;
                y1 = y2;
                y2 = tmp;
            }

            if (shape.equals("Rectangle")) {
                e = new Rectangle(x1, y1, x2 - x1, y2 - y1);
                e.setFill(color);
                e.setOpacity(0.5);
                e.setStroke(color);
                e.setOnMouseEntered(new ShapeInfoHandler());
            }

            if (shape.equals("Circle")) {
                e = new Ellipse((x1 + x2) / 2, (y1 + y2) / 2, (x2 - x1) / 2, (y2 - y1) / 2);
                e.setFill(color);
                e.setOpacity(0.5);
                e.setStroke(color);
                e.setOnMouseEntered(new ShapeInfoHandler());
            }

            pane.getChildren().add(e);
            teller++;
        }
    }

    class NewPaintingHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            bp.setCenter(getPane());
        }
    }

    class ShapeInfoHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            shapeInfo.setText(event.getSource().toString());
        }
    }

    class CboHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            shape = shapeCbo.getValue();
            color = colorCbo.getValue();
        }

    }

}
