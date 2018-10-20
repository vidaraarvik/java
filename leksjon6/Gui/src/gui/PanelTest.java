package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PanelTest extends Application {
    
    private Button knapp1;
    private Button knapp2;
    private Button knapp3;
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        HBox knapper = new HBox();

        knapp1 = new Button("K1");
        knapp1.setOnAction(new Lytter());
        knapp2 = new Button("K2");
        knapp3 = new Button("K3");
        knapper.getChildren().addAll(knapp1, knapp2, knapp3);
        
        pane.setTop(knapper);
        pane.setRight(new Button("Right"));
        pane.setBottom(new Button("Bottom"));
        pane.setLeft(new Button("Left"));
        pane.setCenter(new TextArea("Center"));

        Scene scene = new Scene(pane);
        primaryStage.setTitle("PanelTest");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Lytter implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Klikk: " + event.toString());
    }
    
}