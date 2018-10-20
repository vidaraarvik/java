package eksamen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BankApp extends Application {
    
    // Modell
    private Bank bank;
    
    // GUI
    private GridPane root;
    private Label fraKonto, tilKonto, beløp, kjørtInfo, avbruttInfo;
    private TextField fraTf, tilTf, beløpTf;
    private Button nyTransaksjonBtn, kjørTransaksjonerBtn;
    
    @Override
    public void start(Stage primaryStage) {
        
        initContent();
        
        nyTransaksjonBtn.setOnAction(e -> nyTransaksjon());
        kjørTransaksjonerBtn.setOnAction(e -> kjørTransaksjoner());
        
        primaryStage.setOnCloseRequest(e -> Logg.getLogg().lukk());
        
        Scene scene = new Scene(root, 300, 180);
        primaryStage.setTitle("BankApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initContent() {
        // Modell
        bank = new Bank();
        // GUI
        root = new GridPane();
        fraKonto = new Label("Fra konto:");
        tilKonto = new Label("Til konto:");
        beløp = new Label("Beløp:");
        kjørtInfo = new Label();
        avbruttInfo = new Label();
        fraTf = new TextField();
        tilTf = new TextField();
        beløpTf = new TextField();
        nyTransaksjonBtn = new Button("Ny transaksjon");
        kjørTransaksjonerBtn = new Button("Kjør transaksjoner");
        
        root.add(fraKonto, 0, 0);
        root.add(fraTf, 1, 0);
        root.add(tilKonto, 0, 1);
        root.add(tilTf, 1, 1);
        root.add(beløp, 0, 2);
        root.add(beløpTf, 1, 2);
        root.add(nyTransaksjonBtn, 0, 3);
        root.add(kjørTransaksjonerBtn, 1, 3);
        root.add(kjørtInfo, 0, 4);
        root.add(avbruttInfo, 1, 4);
       
        root.setPadding(new Insets(15));
    }

    private void nyTransaksjon() {
        int fra = Integer.parseInt(fraTf.getText());
        int til = Integer.parseInt(tilTf.getText());
        double siffer = Double.parseDouble(beløpTf.getText());
        
        Konto kontoFra = bank.finnKonto(fra);
        Konto kontoTil = bank.finnKonto(til);
        
        Transaksjon tr = new Transaksjon(kontoFra, kontoTil, siffer);
        
        bank.nyTransaksjon(tr);
        
        fraTf.setText("");
        tilTf.setText("");
        beløpTf.setText("");
    }

    private void kjørTransaksjoner() {
        int antTransaksjoner = bank.getAntTransaksjoner();
        
        int kjørt = bank.kjørTransaksjoner();
        int avbrutt = antTransaksjoner - kjørt;
        
        kjørtInfo.setText("Kjørt: " + kjørt);
        avbruttInfo.setText("Avbrutt: " + avbrutt);
    }
    
}
