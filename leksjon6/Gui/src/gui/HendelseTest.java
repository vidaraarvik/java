package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class HendelseTest extends Application {

    private String melding = "Hei verden!";

    @Override
    public void start(Stage primaryStage) {
        Button btnOK = new Button("OK");
        Scene scene = new Scene(btnOK, 200, 250);

        // Registrerer lytter på knappen
        KnappeLytter lytter = new KnappeLytter();
        btnOK.setOnAction(lytter);

        // Registrerer lytter på vinduet (standard lukkeknapp)
        primaryStage.setOnCloseRequest(new VinduLytter());

        primaryStage.setTitle("HendelseTest");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Indre klasse for å håndtere klikk på knapp
    class KnappeLytter implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            // Her har vi tilgang på objektvariabelen melding
            System.out.println(melding);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


// Klasse for å håndtere at bruker lukker vinduet
class VinduLytter implements EventHandler<WindowEvent> {

    @Override
    public void handle(WindowEvent event) {
        System.out.println("Avslutter...");
    }
}
