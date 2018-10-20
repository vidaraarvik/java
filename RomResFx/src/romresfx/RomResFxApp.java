package romresfx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    // Lagre på rotmappe i prosjektet (der manifest.mf ligger)
    private final String FILNAVN = "reservasjoner.txt";

    private TextField txtRomkode;
    private TextField txtDato;
    private TextField txtFra;
    private TextField txtTil;
    private TextField txtKontakt;
    private Button btnNyReservasjon;

    private ArrayList<Reservasjon> resListe = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        // Les inn fra fil
        lesFraFil();
        visReservasjoner();

        // Setter opp layout for vinduet
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        // Lager GUI-komponenter (kontroller)
        txtRomkode = new TextField();
        txtDato = new TextField();
        txtFra = new TextField();
        txtTil = new TextField();
        txtKontakt = new TextField();
        btnNyReservasjon = new Button("Ny reservasjon");

        // Legger til GUI-komponenter
        pane.add(new Label("Romkode:"), 0, 0);
        pane.add(txtRomkode, 1, 0);
        pane.add(new Label("Dato:"), 0, 1);
        pane.add(txtDato, 1, 1);
        pane.add(new Label("Fra:"), 0, 2);
        pane.add(txtFra, 1, 2);
        pane.add(new Label("Til:"), 0, 3);
        pane.add(txtTil, 1, 3);
        pane.add(new Label("Kontakt:"), 0, 4);
        pane.add(txtKontakt, 1, 4);
        pane.add(btnNyReservasjon, 1, 5);
        GridPane.setHalignment(btnNyReservasjon, HPos.RIGHT);

        // Registrerer lytter på knappen
        KnappeLytter lytter = new KnappeLytter();
        btnNyReservasjon.setOnAction(lytter);

        // Registrerer lytter på vinduet (standard lukkeknapp)
        primaryStage.setOnCloseRequest(new VinduLytter());
        
        // Viser fram vinduet
        Scene scene = new Scene(pane);
        primaryStage.setTitle("ShowGridPane");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void lesFraFil() {
        try {
            BufferedReader innfil = new BufferedReader(new FileReader(FILNAVN));
            String linje = innfil.readLine();
            while (linje != null) { // Ikke slutt på filen
                Reservasjon ny = new Reservasjon(linje);
                resListe.add(ny);
                linje = innfil.readLine();
            }
            innfil.close();
        } catch (IOException e) {
            System.err.println("Reservasjonsfilen finnes ikke.");
            System.exit(-1);
        } catch (NumberFormatException e) {
            System.err.println("Reservasjonsfilen er på feil format.");
            System.exit(-1);
        }

    }

    private void visReservasjoner() {
        String ut = "Reservasjoner: \n";
        for (Reservasjon r : resListe) {
            ut += r.toString() + "\n";
        }
        visMelding(ut);
    }

    private void skrivTilFil() {
        PrintWriter utfil = null;
        try {
            utfil = new PrintWriter(new FileWriter(FILNAVN, false));
            for (Reservasjon r : resListe) {
                utfil.println(r.toString());
            }
        } catch (IOException e) {
            visMelding("Feil ved skriving til reservasjonsfil.");
            System.exit(-1);
        } finally {
            utfil.close();
        }
    }

    // Avleser tekstfelt og oppretter en ny reservasjon
    private Reservasjon lesFraBruker() {
        String romkode = txtRomkode.getText();
        String strDato = txtDato.getText();
        Dato dato = new Dato(strDato);
        String strFra = txtFra.getText();
        KlSlett fra = new KlSlett(strFra);
        String strTil = txtTil.getText();
        KlSlett til = new KlSlett(strTil);
        String kontakt = txtKontakt.getText();
        Reservasjon r = new Reservasjon(romkode, dato, fra, til, kontakt);
        return r;
    }

    // Rensker alle tekstfelt
    private void rensk() {
        txtRomkode.setText("");
        txtDato.setText("");
        txtFra.setText("");
        txtTil.setText("");
        txtKontakt.setText("");
    }

    // Legger ny reservasjon inn i listen hvis mulig
    private void leggInn(Reservasjon r) {
        boolean kollisjon = false;
        for (Reservasjon reservasjon : resListe) {
            if (reservasjon != null) {
                if (r.kolliderer(reservasjon)) {
                    kollisjon = true;
                    String msg = "Reservasjonen kolliderer med: "
                            + reservasjon.toString();
                    visMelding(msg);
                }
            }
        }

        // Legger inn bakerst hvis det ikke er kollisjoner
        if (!kollisjon) {
            resListe.add(r);
        }
    }

    // Viser melding i dialogvindu
    private void visMelding(String m) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Melding");
        alert.setContentText(m);
        alert.showAndWait();
    }

    // Klasse for å håndtere klikk på knapp for å legge til reservasjon
    class KnappeLytter implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            Reservasjon r = lesFraBruker();
            leggInn(r);
            visReservasjoner();
            rensk();
        }
    }

    // Klasse for å håndtere at bruker lukker vinduet
    class VinduLytter implements EventHandler<WindowEvent> {

        @Override
        public void handle(WindowEvent event) {
            skrivTilFil();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
