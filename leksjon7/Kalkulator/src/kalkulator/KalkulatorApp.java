package kalkulator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author speedy laptop
 */
public class KalkulatorApp extends Application {

    // GUI
    private String[][] knappeTekster
            = {
                {"7", "8", "9", "+"},
                {"4", "5", "6", "-"},
                {"1", "2", "3", "*"},
                {"0", "+/-", "C", "/"},};
    private GridPane knapper = new GridPane();
    private TextField display = new TextField();
    private Button erLik = new Button("=");

    // Modell
    Kalkulator kalk = new Kalkulator();

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        for (int rad = 0; rad < 4; rad++) {
            for (int kol = 0; kol < 4; kol++) {
                Button knapp = new Button(knappeTekster[rad][kol]);
                knapper.add(knapp, kol, rad);

                knapp.prefHeightProperty()
                        .bind(knapper.heightProperty().divide(4));
                knapp.prefWidthProperty()
                        .bind(knapper.widthProperty().divide(4));

                knapp.setOnAction(e -> behandleKnappetrykk(e));
            }
        }
        erLik.setOnAction(e -> behandleKnappetrykk(e));
        erLik.prefWidthProperty().bind(knapper.widthProperty().multiply(2));
        knapper.setPrefSize(50, 50);

        root.setTop(display);
        root.setCenter(knapper);
        root.setBottom(erLik);

        Scene scene = new Scene(root);
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

    private void behandleKnappetrykk(ActionEvent e) {
        Button knapp = (Button) e.getSource();
        String s = knapp.getText();

        switch (s) {
            case "0":
                kalk.siffer(0);
                break;
            case "1":
                kalk.siffer(1);
                break;
            case "2":
                kalk.siffer(2);
                break;
            case "3":
                kalk.siffer(3);
                break;
            case "4":
                kalk.siffer(4);
                break;
            case "5":
                kalk.siffer(5);
                break;
            case "6":
                kalk.siffer(6);
                break;
            case "7":
                kalk.siffer(7);
                break;
            case "8":
                kalk.siffer(8);
                break;
            case "9":
                kalk.siffer(9);
                break;
            case "+":
                kalk.oper('+');
                break;
            case "-":
                kalk.oper('-');
                break;
            case "*":
                kalk.oper('*');
                break;
            case "/":
                kalk.oper('/');
                break;
            case "+/-":
                kalk.snuFortegn();
                break;
            case "=":
                kalk.erLik();
                break;
            case "C":
                kalk.nullstill();
                break;
            default:
                break;
        }
        
        display.setText(kalk.hentTall());
    }

}
