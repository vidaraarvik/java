package no.hit.kart;

/*  Vidar Årvik, Eivind Aasbø
 *  09-2017
 *
 *
 * Applikasjon som vha javafx viser et kart med
 * 5 ulike punkter. 
 * 
 * Brukeren skal oppgi (cirka) koordinater for et punkt,
 * dersom brukeren kommer nærme nok (nærmere enn 20 piksler)
 * vil det dukke opp en tekstlig beskrivelse av punktet.
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class KartApp extends Application {

    private Hendelse[] tab;
    private int nesteLedige;
    private static final String SKILLETEGN = ";";

    @Override
    public void start(Stage stage) {

        try {
            InputStream in = getClass().getResourceAsStream("/data/hendelser.txt");
            BufferedReader innfil = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            // Setter tabellen lik størrelse som i tekstfil
            tab = new Hendelse[Integer.parseInt(innfil.readLine())];

            // Henter linjer fra datafil til tabell (tab)
            String linje;
            int teller = 0;
            while ((linje = innfil.readLine()) != null) {
                tab[teller] = new Hendelse(linje);
                teller++;
            }
            innfil.close();

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(KartApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KartApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Definerer vinduet og initierer scenegrafen
        Group root = new Group();
        Scene scene = new Scene(root, 800, 400);
        stage.setScene(scene);
        stage.setTitle("Kart");
        stage.setResizable(false);

        // Legger til bakgrunn
        Image bilde = new Image("/img/verden.jpg");
        ImageView bildenode = new ImageView();
        bildenode.setImage(bilde);
        root.getChildren().add(bildenode);

        // Tegner en sirkel for hver hendelse i tabellen (legger den inn i scenegrafen)
        for (int i = 0; i < tab.length; i++) {
            Circle sirkel = new Circle(tab[i].getPunktX(), tab[i].getPunktY(), 5, Color.rgb(0, 0, 255));
            root.getChildren().add(sirkel);
        }

        // Viser scenegrafen
        stage.show();

        // Velkomstmelding som viser en forklaring til kartet
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informasjon");
        alert.setContentText("Du må oppgi koordinater maks 20 piksler unna punktet for å se hva det inneholder!");
        alert.showAndWait();

        // Leser inn X og Y koordinater 
        // programmet kjører frem til til brukeren oppgir '0' eller 'cancel'
        boolean kjør = true;
        while (kjør) {
            int x = 0, y = 0;
            try {
                x = lesHeltallFraBruker("X");
                y = lesHeltallFraBruker("Y");
                if (x == 0 && y == 0) {
                    kjør = false;
                }
            } catch (NumberFormatException ex) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("NumberFormatException");
                alert.setContentText("Oppgitt verdi er ikke et gyldig siffer!");
                alert.showAndWait();
            }
            // Tegner et kryss på de oppgitte koordinatene
            Line line1 = new Line(x - 5, y - 5, x + 5, y + 5);
            Line line2 = new Line(x - 5, y + 5, x + 5, y - 5);
            line1.setStroke(Color.RED);
            line1.setStrokeWidth(5);
            line2.setStroke(Color.RED);
            line2.setStrokeWidth(5);
            root.getChildren().add(line1);
            root.getChildren().add(line2);

            // Finner den nærmeste hendelsen
            try {
                Hendelse h = finnHendelse(x, y);
                Text tekst = new Text(h.getDato() + ": " + h.getBeskrivelse());
                tekst.setFont(Font.font("Verdana", 14));
                tekst.setFill(Color.BLACK);
                tekst.setStyle("-fx-font-weight: bold");
                tekst.setX(h.getPunktX() - 100);
                tekst.setY(h.getPunktY() + 20);
                root.getChildren().add(tekst);
            } catch (NullPointerException ex) {

            }
            stage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Leser et koordinat fra bruker
    private int lesHeltallFraBruker(String n) {
        TextInputDialog dialog = new TextInputDialog("0");
        dialog.setTitle("Søkepunkt");
        dialog.setHeaderText("Oppgi " + n + "-koordinater");
        dialog.setContentText(n + ":");

        Optional<String> result = dialog.showAndWait();
        int koordinat = 0;
        if (result.isPresent()) {
            koordinat = Integer.parseInt(result.get());
        }
        return koordinat;
    }

    // Finner dem nærmeste hendelsen
    private Hendelse finnHendelse(int x, int y) {
        Punkt xy = new Punkt(x, y); // krysset
        Hendelse h = null; // hendelse som skal returneres
        int nærmeste = Integer.MAX_VALUE;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].getPunkt().avstand(xy) < nærmeste) {
                nærmeste = tab[i].getPunkt().avstand(xy);
                h = tab[i];
            }
        }
        if (nærmeste <= 20) {   // hvis nærmeste hendelse er < 20 piksler unna
            return h;
        } else {
            return null;   // nærmeste hendelse er for langt borte
        }
    }

}
