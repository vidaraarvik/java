package hendelse;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author speedy laptop
 */
public class HendelseDemo extends Application implements EventHandler<ActionEvent> {

    Button btn1, btn2, btn3, btn3b, btn4;

    @Override
    public void start(Stage primaryStage) {
        // Med egen lytteklasse
        btn1 = new Button("Knapp 1");
        btn1.setOnAction(new KnappeLytter());

        // Med indre lytteklasse
        btn2 = new Button("Knapp 2");
        btn2.setOnAction(new IndreKnappeLytter());

        // La klassen selv lytte
        btn3 = new Button("Knapp 3");
        btn3.setOnAction(this);

        // Enda en knapp for this
        btn3b = new Button("Knapp 3b");
        btn3b.setOnAction(this);

        // Med anonym klasse
        btn4 = new Button();
        btn4.setText("Knapp 4");
        btn4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hei fra knapp 4.");
            }
        });

        // Med lambda-uttrykk
        Button btn5 = new Button("Knapp 5");
        btn5.setOnAction(e -> System.out.println("Hei fra knapp 5."));

        FlowPane root = new FlowPane();
        root.getChildren().addAll(btn1, btn2, btn3, btn3b, btn4, btn5);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btn3) {
            System.out.println("Hei fra knapp 3.");
        } else {
            System.out.println("Hei fra knapp 3b.");
        }
    }

    private class IndreKnappeLytter implements EventHandler<ActionEvent> {

        public IndreKnappeLytter() {
        }

        @Override
        public void handle(ActionEvent event) {
            System.out.println("Hei fra knapp 2.");
        }
    }

}
