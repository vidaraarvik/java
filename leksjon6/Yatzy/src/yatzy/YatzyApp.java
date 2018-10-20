package yatzy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author speedy
 */
public class YatzyApp extends Application {
    
    // GUI
    BorderPane bp;
    HBox hBox;
    Label melding = new Label("Spill Yatzy!");
    Button btn1, btn2, btn3, btn4, btn5, btnKast;

    @Override
    public void start(Stage primaryStage) {

        bp = new BorderPane();
        bp.setTop(melding);
        bp.setCenter(getCenter());
        bp.setBottom(getBottom());
        

        Scene scene = new Scene(bp, 300, 250);

        primaryStage.setTitle("Yatzy");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private HBox getCenter() {
        hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setStyle("-fx-background-color: green;");
        btn1 = new Button("1");
        btn2 = new Button("2");
        btn3 = new Button("3");
        btn4 = new Button("4");
        btn5 = new Button("5");
        hBox.getChildren().addAll(btn1, btn2, btn3, btn4, btn5);
        return hBox;
    }

    private Node getBottom() {
        btnKast = new Button("Kast terninger.");
        return btnKast;
    }

}
