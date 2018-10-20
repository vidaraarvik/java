package comparable;

import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Klasse som håndterer det grafiske brukergrensesnittet for applikasjonen.
 *
 * @author Vidar Årvik
 */
public class CompareGUI extends Application {

    // Modell
    private ArrayList<Person> personListe;

    // GUI
    private VBox root;
    private HBox rbBox;
    private GridPane gp;
    private TextField navnTf, adresseTf, postNrTf, postStedTf, kundeNrTf;
    private TextArea personFelt;
    private Button leggTilBtn;
    private RadioButton rbNavn, rbNavnBaklengs, rbPostNr, rbPostNrBaklengs, rbKundeNr, rbKundeNrBaklengs;

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        initContent();

        Scene scene = new Scene(root, 500, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void initContent() {
        //Modell
        personListe = new ArrayList();

        // GUI
        root = new VBox();
        gp = new GridPane();
        rbBox = new HBox();
        navnTf = new TextField();
        adresseTf = new TextField();
        postNrTf = new TextField();
        postStedTf = new TextField();
        kundeNrTf = new TextField();

        personFelt = new TextArea();
        personFelt.setPrefRowCount(10);
        personFelt.setEditable(false);

        leggTilBtn = new Button("Legg til");
        leggTilBtn.setPrefWidth(500);
        leggTilBtn.setOnAction(e -> leggTilPerson());

        rbNavn = new RadioButton("Navn");
        rbNavn.setOnAction(new radioButtonHandler());
        rbNavnBaklengs = new RadioButton("Navn Baklengs");
        rbNavnBaklengs.setOnAction(new radioButtonHandler());
        rbPostNr = new RadioButton("PostNr");
        rbPostNr.setOnAction(new radioButtonHandler());
        rbPostNrBaklengs = new RadioButton("PostNr Baklengs");
        rbPostNrBaklengs.setOnAction(new radioButtonHandler());
        rbKundeNr = new RadioButton("KundeNr");
        rbKundeNr.setOnAction(new radioButtonHandler());
        rbKundeNrBaklengs = new RadioButton("KundeNr Baklengs");
        rbKundeNrBaklengs.setOnAction(new radioButtonHandler());

        gp.add(new Label("Navn"), 0, 0);
        gp.add(navnTf, 1, 0);
        gp.add(new Label("Adresse"), 0, 1);
        gp.add(adresseTf, 1, 1);
        gp.add(new Label("PostNr"), 0, 2);
        gp.add(postNrTf, 1, 2);
        gp.add(new Label("Sted"), 0, 3);
        gp.add(postStedTf, 1, 3);
        gp.add(new Label("KundeNr"), 0, 4);
        gp.add(kundeNrTf, 1, 4);
        // RadioButtons
        gp.add(new Label("Sortering:"), 2, 1);
        gp.add(rbNavn, 2, 2);
        gp.add(rbNavnBaklengs, 3, 2);
        gp.add(rbPostNr, 2, 3);
        gp.add(rbPostNrBaklengs, 3, 3);
        gp.add(rbKundeNr, 2, 4);
        gp.add(rbKundeNrBaklengs, 3, 4);

        //rbBox.getChildren().addAll(rbNavn, rbNavnBaklengs, rbPostNr, rbPostNrBaklengs, rbKundeNr, rbKundeNrBaklengs);
        root.getChildren().addAll(gp, personFelt, leggTilBtn);
    }

    /**
     * Legger til ny person i personListe array.
     */
    private void leggTilPerson() {
        Adresse adr = new Adresse(adresseTf.getText(), postNrTf.getText(), postStedTf.getText());
        Person p = new Person(navnTf.getText(), adr, Integer.parseInt(kundeNrTf.getText()));

        personListe.add(p);

        skrivTilFelt();
    }

    /**
     * Skriver ut en tekstlig representasjon av personListe i tekstfeltet
     */
    private void skrivTilFelt() {
        personFelt.clear();
        for (Person pers : personListe) {
            personFelt.appendText(pers.toString() + "\n");
        }
    }

    /**
     * Sorterer personListe.
     */
    private class radioButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == rbNavn) {
                Collections.sort(personListe);
                skrivTilFelt();
                setSelected((RadioButton) event.getSource());
            } else if (event.getSource() == rbNavnBaklengs) {
                Collections.sort(personListe, Collections.reverseOrder());
                skrivTilFelt();
                setSelected((RadioButton) event.getSource());
            } else if (event.getSource() == rbPostNr) {
                Collections.sort(personListe, new PostNrKomparator());
                skrivTilFelt();
                setSelected((RadioButton) event.getSource());
            } else if (event.getSource() == rbPostNrBaklengs) {
                Collections.sort(personListe, Collections.reverseOrder(new PostNrKomparator()));
                skrivTilFelt();
                setSelected((RadioButton) event.getSource());
            } else if (event.getSource() == rbKundeNr) {
                Collections.sort(personListe, new KundeNrKomparator());
                skrivTilFelt();
                setSelected((RadioButton) event.getSource());
            } else {
                Collections.sort(personListe, Collections.reverseOrder(new KundeNrKomparator()));
                skrivTilFelt();
                setSelected((RadioButton) event.getSource());
            }
        }

        /**
         * "Huker av" alle andre radioknapper.
         * @param source
         */
        private void setSelected(RadioButton source) {
            rbNavn.setSelected(false);
            rbNavnBaklengs.setSelected(false);
            rbPostNr.setSelected(false);
            rbPostNrBaklengs.setSelected(false);
            rbKundeNr.setSelected(false);
            rbKundeNrBaklengs.setSelected(false);

            source.setSelected(true);
        }
    }

}
