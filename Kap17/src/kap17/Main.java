package kap17;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private final int width = 600;
    private final int height = 400;

    @Override
    public void start(Stage primaryStage) {
        BinaryTreePane root = new BinaryTreePane(width, height);


        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
