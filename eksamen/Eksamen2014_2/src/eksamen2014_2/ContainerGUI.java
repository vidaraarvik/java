package eksamen2014_2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContainerGUI extends Application {

    // Modell
    private ContainerModell cm;

    // GUI
    private VBox inputRoot;
    private HBox rbBox, buttonBox;
    private RadioButton dagRb, timeRb;
    private Label adresse, minstevolum, antTimer;
    private TextField adresseTf, minstevolumTf, antTimerTf, infoTf;
    private Button nyttOppdragBtn, avsluttOppdragBtn;

    @Override
    public void start(Stage primaryStage) {

        initContent();

        Scene scene = new Scene(inputRoot, 300, 250);

        primaryStage.setTitle("Eksamen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initContent() {
        cm = new ContainerModell();

        inputRoot = new VBox();
        buttonBox = new HBox();
        rbBox = new HBox();

        dagRb = new RadioButton("Dag-oppdrag");
        timeRb = new RadioButton("Time-oppdrag");
        adresse = new Label("Adresse:");
        adresseTf = new TextField();
        minstevolum = new Label("Minste volum:");
        minstevolumTf = new TextField();
        antTimer = new Label("Antall timer:");
        antTimerTf = new TextField();
        nyttOppdragBtn = new Button("Nytt oppdrag");
        avsluttOppdragBtn = new Button("Avslutt oppdrag");
        infoTf = new TextField();

        rbBox.getChildren().addAll(dagRb, timeRb);
        buttonBox.getChildren().addAll(nyttOppdragBtn, avsluttOppdragBtn);
        inputRoot.getChildren().addAll(rbBox,
                adresse, adresseTf,
                minstevolum, minstevolumTf,
                antTimer, antTimerTf,
                buttonBox, infoTf);

        infoTf.setEditable(false);

        dagRb.setOnAction(e -> dagRbHandler());
        timeRb.setOnAction(e -> timeRbHandler());
        nyttOppdragBtn.setOnAction(e -> nyttOppdrag());
        avsluttOppdragBtn.setOnAction(e -> avsluttOppdrag());
    }

    private void dagRbHandler() {
        antTimerTf.setVisible(false);
        minstevolumTf.setVisible(true);
        timeRb.setSelected(false);
        infoTf.setText("");
    }

    private void timeRbHandler() {
        antTimerTf.setVisible(true);
        minstevolumTf.setVisible(false);
        dagRb.setSelected(false);
        infoTf.setText("");
    }

    private void nyttOppdrag() {
        
        Oppdrag o = null;

        if (dagRb.isSelected() && minstevolumTf.getText() != null && adresseTf.getText() != null) {
            o = new DagOppdrag(Integer.parseInt(minstevolumTf.getText()), adresseTf.getText());
        } else if (timeRb.isSelected() && antTimerTf.getText() != null && adresseTf.getText() != null) {
            o = new TimeOppdrag(Integer.parseInt(antTimerTf.getText()), adresseTf.getText());
        } else {
            infoTf.setText("Mangler info!");
        }

        if (o != null) {
            cm.regOppdrag(o);
            infoTf.setText("Oppdraget er tildelt container!");
        } 
        
        
        //Oppdrag o = new TimeOppdrag(19, "Oslo");
        //cm.regOppdrag(o);
    }

    private void avsluttOppdrag() {
        //String adr = adresseTf.getText();
        
        String adr = "Oslo";
        
        try {
            cm.avsluttOppdrag(adr);
            infoTf.setText("Oppdrag avsluttet!");
        } catch (IOException ex) {
            infoTf.setText(ex.getMessage());
        }
    }

}
