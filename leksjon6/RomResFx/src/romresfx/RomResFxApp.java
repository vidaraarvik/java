package romresfx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RomResFxApp extends Application {

    // GUI
    private TextField txtRomkode;
    private TextField txtDato;
    private TextField txtFra;
    private TextField txtTil;
    private TextField txtKontakt;
    private Button btnNy;
    private Alert alert;

    // Modell
    private ArrayList<Reservasjon> resListe = new ArrayList<>();
    private final String FILNAVN = "./reservasjoner.txt";
    private final String SKILLETEGN = ";";

    @Override
    public void start(Stage primaryStage) {

        lesFraFil(FILNAVN);

        txtRomkode = new TextField();
        txtDato = new TextField();
        txtFra = new TextField();
        txtTil = new TextField();
        txtKontakt = new TextField();
        btnNy = new Button("Ny reservasjon");

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        root.setHgap(5.5);
        root.setVgap(5.5);

        root.add(new Label("Romkode:"), 0, 0);
        root.add(txtRomkode, 1, 0);
        root.add(new Label("Dato:"), 0, 1);
        root.add(txtDato, 1, 1);
        root.add(new Label("Fra:"), 0, 2);
        root.add(txtFra, 1, 2);
        root.add(new Label("Til:"), 0, 3);
        root.add(txtTil, 1, 3);
        root.add(new Label("Kontakt:"), 0, 4);
        root.add(txtKontakt, 1, 4);
        root.add(btnNy, 1, 5);
        root.setHalignment(btnNy, HPos.RIGHT);

        KnappeLytter lytter = new KnappeLytter();
        btnNy.setOnAction(lytter);

        primaryStage.setOnCloseRequest(new VindusLytter());

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Reservasjon lesFraBruker() {
        String romkode = txtRomkode.getText();
        Dato dato = new Dato(txtDato.getText());
        KlSlett fra = new KlSlett(txtFra.getText());
        KlSlett til = new KlSlett(txtTil.getText());
        String kontakt = txtKontakt.getText();
        return new Reservasjon(romkode, dato, fra, til, kontakt);
    }

    private void leggInn(Reservasjon r) {
        boolean ok = true;
        for (Reservasjon res : resListe) {
            if (res.kolliderer(r)) {
                ok = false;
            }
        }
        if (ok) {
            resListe.add(r);
        }
    }

    private void visReservasjoner() {
        String tekst = "Reservasjoner:";
        for (Reservasjon res : resListe) {
            tekst += "\n" + res.toString();
        }
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Melding");
        alert.setHeaderText("Message");
        alert.setContentText(tekst);
        alert.showAndWait();
    }

    private void lesFraFil(String fil) {
        try {
            //InputStream in = getClass().getResourceAsStream(FILNAVN);
            InputStream in = new FileInputStream(new File(FILNAVN));
            BufferedReader innfil = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            // Overfører linjer fra datafil til resListe
            String linje;
            int i = 0;
            while ((linje = innfil.readLine()) != null) {
                String[] linjeTab = linje.split(SKILLETEGN);
                String romkode = linjeTab[0];
                Dato dato = new Dato(linjeTab[1]);
                KlSlett fra = new KlSlett(linjeTab[2]);
                KlSlett til = new KlSlett(linjeTab[3]);
                String kontakt = linjeTab[4];
                resListe.add(new Reservasjon(romkode, dato, fra, til, kontakt));
                i++;
            }
            innfil.close();
        } catch (IOException ex) {
            System.out.println("Feil i datafil.");
        } finally {
            visReservasjoner();
        }

    }

    private void lagreFil() {
        try {
            String r = "";
            for (Reservasjon res : resListe) {
                r += res.toString() + System.lineSeparator();
            }
            Files.write(Paths.get(FILNAVN), r.getBytes(UTF_8));
        } catch (IOException ex) {
            Logger.getLogger(RomResFxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class KnappeLytter implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Reservasjon r = lesFraBruker();
            leggInn(r);
            visReservasjoner();
            System.out.println(resListe);
        }
    }

    class VindusLytter implements EventHandler<WindowEvent> {

        @Override
        public void handle(WindowEvent event) {
            lagreFil();
        }
    }
}
