package eksamen2014_3;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private HBox root;
    private TegneVindu tegneVindu;
    private VBox inputBox;
    private HBox rbBox, buttonBox;
    private RadioButton dagRb, timeRb;
    private Label adresse, minVolum, antTimer;
    private TextField adresseTf, minVolumTf, antTimerTf, infoTf;
    private Button regBtn, avsluttBtn;
    
    @Override
    public void start(Stage primaryStage) {
        
        initContent();
        
        Scene scene = new Scene(root, 600, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private void initContent() {
        // Modell
        cm = new ContainerModell();
        // GUI
        root = new HBox(20);
        tegneVindu = new TegneVindu(cm);
        inputBox = new VBox(5);
        rbBox = new HBox();
        buttonBox = new HBox();
        dagRb = new RadioButton("Dag-oppdrag");
        timeRb = new RadioButton("Time-oppdrag");
        adresse = new Label("Adresse:");
        minVolum = new Label("Minste volum:");
        antTimer = new Label("Antall timer:");
        adresseTf = new TextField();
        minVolumTf = new TextField();
        antTimerTf = new TextField();
        infoTf = new TextField();
        regBtn = new Button("Nytt oppdrag");
        avsluttBtn = new Button("Avslutt oppdrag");
        
        rbBox.getChildren().addAll(dagRb, timeRb);
        buttonBox.getChildren().addAll(regBtn, avsluttBtn);
        inputBox.getChildren().addAll(rbBox, adresse, adresseTf, minVolum, minVolumTf,
                antTimer, antTimerTf, buttonBox, infoTf);
        root.getChildren().addAll(inputBox, tegneVindu);
        
        dagRb.setOnAction(e -> dagRbActionEvent());
        timeRb.setOnAction(e -> timeRbActionEvent());
        
        regBtn.setOnAction(e -> regBtnActionEvent());
        avsluttBtn.setOnAction(e -> avsluttBtnActionEvent());
        
    }
    
    private void dagRbActionEvent() {
        timeRb.setSelected(false);
        minVolumTf.setEditable(true);
        minVolumTf.setStyle("");
        antTimerTf.setEditable(false);
        antTimerTf.setText("");
        antTimerTf.setStyle("-fx-background-color: lightgray;");
    }
    
    private void timeRbActionEvent() {
        dagRb.setSelected(false);
        antTimerTf.setEditable(true);
        antTimerTf.setStyle("");
        minVolumTf.setEditable(false);
        minVolumTf.setText("");
        minVolumTf.setStyle("-fx-background-color: lightgray;");
    }
    
    private void regBtnActionEvent() {
        if (dagRb.isSelected()) {
            String adresse = adresseTf.getText();
            int minVolum = Integer.parseInt(minVolumTf.getText());
            
            Oppdrag op = new DagOppdrag(minVolum, adresse);
            cm.regOppdrag(op);
        }
        
        if (timeRb.isSelected()) {
            String adresse = adresseTf.getText();
            int antTimer = Integer.parseInt(antTimerTf.getText());
            
            Oppdrag op = new TimeOppdrag(antTimer, adresse);
            cm.regOppdrag(op);
        }
        
        infoTf.setText("Oppdraget er tildelt container!");
        
        updateTegneVindu();
    }
    
    private void avsluttBtnActionEvent() {
        try {
            String adresse = adresseTf.getText();
            
            cm.avsluttOppdrag(adresse);
            
            infoTf.setText("Oppdraget er avsluttet!");
        } catch (IOException ex) {
            infoTf.setText("Kunne ikke skrive til loggfil: 'logg.txt'");
        }
        
        updateTegneVindu();
    }

    private void updateTegneVindu() {
        root.getChildren().remove(tegneVindu);
        tegneVindu = new TegneVindu(cm);
        root.getChildren().add(tegneVindu);
    }
    
}
