package mvc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MVCApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        Model m = new Model();
        View v = new View();
        Controller c = new Controller(m, v);

        Scene scene = new Scene(v, 300, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
