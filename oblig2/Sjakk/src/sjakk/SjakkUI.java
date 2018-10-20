/**
 * Vidar Årvik, 10-2017
 * vidaraarvik@gmail.com
 *
 * Sjakk spill
 * 
 * Brikkene flyttes ved å oppgi sjakkkordinater: rad 1-8, kolonne a-h,
 * eller ved museklikk.
 * Spillet er ment for 2 spillere.
 * 
 * SjakkUI.java:
 * Det grafiske brukergrensesnittet til applikasjonen.
 * Oppretter et brett med brikker, tar deretter imot input fra bruker
 * gjennom museklikk eller flytting med kontroller. Flytter brikken
 * dersom trekket er lovlig.
 * 
 * 26.10.2017:
 * Bonde - ferdig
 * Tårn - ferdig
 * Konge - ferdig
 * Løper - mangler samtlige regler
 * 
 * Har ikke lagt til tur-ordning enda, spillet spilles
 * ved et "honor" basert system der man stoler på at
 * en spiller ikke utfører flere trekk etter hverandre.
 */
package sjakk;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SjakkUI extends Application {

    // Modell
    private Brett b;
    private int spillNr = 0;
    // Variabler for museklikk
    private boolean fokusert = false;
    private String mFra, mTil;

    // GUI
    BorderPane root;
    StackPane sp;
    GridPane brettGrid, brikkeGrid;
    HBox kontroller;
    Label infoLabel = new Label("Velkommen, Hvit starter.");
    TextField fraRute, tilRute;
    Button flytt, lagre, nyttSpill;

    @Override
    public void start(Stage primaryStage) {

        // Deklarerer panelene
        root = new BorderPane();
        sp = new StackPane();
        brettGrid = new GridPane();
        brettGrid.setAlignment(Pos.CENTER);
        brikkeGrid = new GridPane();
        brikkeGrid.setAlignment(Pos.CENTER);
        
        // Legger brett og brikker i en stackpane
        sp.getChildren().addAll(brettGrid, brikkeGrid);

        // Input-område for flytting av brikker
        kontroller = new HBox(5);
        kontroller.setAlignment(Pos.CENTER);
        kontroller.setPadding(new Insets(10));

        // Infopanel
        BorderPane.setAlignment(infoLabel, Pos.CENTER);
        infoLabel.setPadding(new Insets(10));
        infoLabel.setStyle("-fx-font: 14 arial;");

        // Legger panelene i en BorderPane
        root.setTop(infoLabel);
        root.setBottom(kontroller);
        root.setCenter(sp);

        // Initierer spillet
        tegnBakgrunn();
        tegnBrikker();
        tegnKontroller();

        // Initierer scenen
        Scene scene = new Scene(root, 800, 650);
        primaryStage.setTitle("Sjakk");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    // Metode som tegner en ny bakgrunn
    private void tegnBakgrunn() {
        int i = 0;
        spillNr++;
        b = new Brett(spillNr);
        for (int rad = 0; rad < b.BRETTSTORRELSE; rad++) {
            for (int kol = 0; kol < b.BRETTSTORRELSE; kol++) {
                Rectangle r = new Rectangle(70, 70);
                if (i % 2 == 0) {
                    r.setFill(Color.WHITE);
                } else {
                    r.setFill(Color.GREY);
                }
                brettGrid.add(r, kol, rad);
                i++;
            }
            i++;
        }

    }

    // Metode som oppretter først et usynlig rutenett over brettet og
    // deretter plasseres brikkene på rutenettet
    private void tegnBrikker() {
        for (int rad = 0; rad < b.BRETTSTORRELSE; rad++) {
            for (int kol = 0; kol < b.BRETTSTORRELSE; kol++) {
                //Bruker rektangler for å få samme størrelse som brettGrid
                Rectangle r = new Rectangle(70, 70);
                r.setFill(Color.BEIGE);
                r.setOpacity(0.0);
                r.toFront();
                brikkeGrid.add(r, kol, rad);
                //Tegner samtlige brikker
                Label l = new Label();
                if (b.brikkene[rad][kol] != null) {
                    String s = b.brikkene[rad][kol].brikkenavn();
                    String brikke = "";
                    switch (s) {
                        case "bs":
                            brikke = "♟";
                            break;
                        case "bh":
                            brikke = "♙";
                            break;
                        case "ts":
                            brikke = "♜";
                            break;
                        case "th":
                            brikke = "♖";
                            break;
                        case "ls":
                            brikke = "♝";
                            break;
                        case "lh":
                            brikke = "♗";
                            break;
                        case "ks":
                            brikke = "♚";
                            break;
                        case "kh":
                            brikke = "♔";
                            break;
                    }
                    l.setText(brikke);
                    l.setStyle("-fx-font: 60 arial;");
                    l.setPadding(new Insets(0, 0, 0, 6));
                    brikkeGrid.add(l, kol, rad);
                }
                //Label som benyttes ved museklikk og debugging.
                Label l2 = new Label(hentRute(kol, rad));
                l2.setStyle("-fx-font: 60 arial;");
                l2.setOpacity(0.0);
                l2.toFront();
                l2.setOnMouseClicked(e -> {
                    museklikk(l2);
                    l.setStyle("-fx-font: 60 arial;-fx-text-fill: gold;");
                });
                brikkeGrid.add(l2, kol, rad);
            }
        }
    }

    // Metode som oppretter kontroller for bruker-input
    private void tegnKontroller() {
        fraRute = new TextField();
        tilRute = new TextField();
        flytt = new Button("Flytt");
        lagre = new Button("Lagre");
        nyttSpill = new Button("Nytt Spill");
        kontroller.getChildren().addAll(
                new Label("Fra Rute:"),
                fraRute,
                new Label("Til Rute:"),
                tilRute,
                flytt,
                lagre,
                nyttSpill);
        flytt.setOnAction(new flyttHandler());
        nyttSpill.setOnAction(e -> {
            brettGrid.getChildren().clear();
            brikkeGrid.getChildren().clear();
            tegnBakgrunn();
            tegnBrikker();
            infoLabel.setText("Spill: " + b.getSpillNr());
        });
    }

    // Flytter en brikke
    private void flytt(String fra, String til) {
        try {
            if (b.flyttBrikke(fra, til)) {
                infoLabel.setText(fra + " -> " + til);
            } else {
                infoLabel.setText("Ulovlig trekk!");
            }
        } catch (NullPointerException ex) {
            infoLabel.setText("Ulovlig trekk!");
        } catch (ArrayIndexOutOfBoundsException ex) {
            infoLabel.setText("Ulovlig trekk! Kan ikke flytte brikken ut av brettet.");
        }
        brikkeGrid.getChildren().clear();
        tegnBrikker();
    }

    // Behandler museklikk
    private void museklikk(Label l2) {
        if (!fokusert) {
            mFra = l2.getText();
            fokusert = true;
        } else {
            mTil = l2.getText();
            fokusert = false;

            flytt(mFra, mTil);
        }
    }

    // Behandler flytting fra bruker-input
    private class flyttHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String fra = oversett(fraRute.getText().toLowerCase());
            String til = oversett(tilRute.getText().toLowerCase());
            flytt(fra, til);
        }
        
        // Oversetter fra tradisjonelt sjakkbrett-oppsett til oppsettet i koden
        private String oversett(String rutenavn) {
            switch (rutenavn.charAt(1)) {
                case '1':
                    rutenavn = rutenavn.replace('1', '8');
                    break;
                case '2':
                    rutenavn = rutenavn.replace('2', '7');
                    break;
                case '3':
                    rutenavn = rutenavn.replace('3', '6');
                    break;
                case '4':
                    rutenavn = rutenavn.replace('4', '5');
                    break;
                case '5':
                    rutenavn = rutenavn.replace('5', '4');
                    break;
                case '6':
                    rutenavn = rutenavn.replace('6', '3');
                    break;
                case '7':
                    rutenavn = rutenavn.replace('7', '2');
                    break;
                case '8':
                    rutenavn = rutenavn.replace('8', '1');
                    break;
            }
            return rutenavn;
        }
    }

    // Returnerer en rute fra oppgitt kolonne og rad
    private String hentRute(int mKol, int mRad) {
        char kol = (char) (mKol + 97);
        int rad = mRad + 1;
        return "" + kol + "" + rad;
    }
}
