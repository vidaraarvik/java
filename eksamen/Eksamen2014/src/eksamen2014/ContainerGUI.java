/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eksamen2014;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author speedy
 */
public class ContainerGUI extends Application {

    // GUI
    private BorderPane bp = new BorderPane();
    private VBox vBox = new VBox(5);
    private HBox rbBox = new HBox(5),
            btnBox = new HBox();
    private TegneVindu tegneVindu = new TegneVindu();
            
    private RadioButton dag = new RadioButton("Dag-oppdrag"),
            time = new RadioButton("Time-oppdrag");
    private TextField adresseTF = new TextField(),
            volumTF = new TextField(),
            timerTF = new TextField(),
            meldingTF = new TextField();
    private Button btnReg = new Button("Nytt oppdrag"),
            btnAvslutt = new Button("Avslutt Oppdrag");

    // Modell
    private ContainerModell cm = new ContainerModell();

    @Override
    public void start(Stage primaryStage) {

        initLPane();

        Scene scene = new Scene(bp, 800, 400);

        primaryStage.setTitle("Container");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void initLPane() {
        bp.setLeft(vBox);
        bp.setRight(tegneVindu);

        vBox.getChildren().addAll(
                rbBox,
                new Label("Adresse:"),
                adresseTF,
                new Label("Minste volum:"),
                volumTF,
                new Label("Antall timer:"),
                timerTF,
                btnBox,
                meldingTF);
        meldingTF.setEditable(false);
        meldingTF.setStyle("-fx-background-color: lightgrey;");

        dag.setOnAction(e -> {
            time.selectedProperty().set(false);
            timerTF.setEditable(false);
            timerTF.setText("");
            volumTF.setEditable(true);
        });
        time.setOnAction(e -> {
            dag.selectedProperty().set(false);
            timerTF.setEditable(true);
            volumTF.setEditable(false);
            volumTF.setText("");
        });

        btnReg.setOnAction(e -> btnReg());
        btnAvslutt.setOnAction(e -> btnAvslutt());

        for (Node n : vBox.getChildren()) {
            n.setTranslateX(5);
        }

        vBox.setPrefWidth(300);

        rbBox.setTranslateX(50);
        rbBox.setPadding(new Insets(15));
        rbBox.getChildren().addAll(dag, time);

        btnBox.setTranslateX(50);
        btnBox.getChildren().addAll(btnReg, btnAvslutt);

    }

    private void btnReg() {
        if (dag.isSelected()) {
            Oppdrag o = new DagOppdrag(adresseTF.getText(), Integer.parseInt(volumTF.getText()));
            
            meldingTF.setText(cm.regOppdrag(o));
        }
        
        if (time.isSelected()) {
            Oppdrag o = new TimeOppdrag(adresseTF.getText(), Double.parseDouble(timerTF.getText()));
            
            meldingTF.setText(cm.regOppdrag(o));
        }
    }

    private void btnAvslutt() {
        try {
            meldingTF.setText(cm.avsluttOppdrag(adresseTF.getText()));
        } catch (IOException ex) {
            System.out.println("feil ved skriving til fil");
        }
    }
}
