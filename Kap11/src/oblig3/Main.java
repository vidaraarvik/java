package oblig3;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Program som tar en webside eller fil og analyserer denne.
 * HTML tagger, kommentarer og vanlig kode splittes og skrives ut i hver sitt tekstfelt.
 * 
 * @author Vidar Årvik
 */

public class Main extends Application {

    //GUI
    private BorderPane bp;
    private TextField tfWeb, tfLokal;
    private TextArea taTags, taKommentarer, taKode;
    private HBox hBox, inputBox;

    //Modell
    private int tilstand;
    private final int ordinær_programkode = 0,
            kommentar = 1,
            htmltag = 4,
            lt = 100;

    @Override
    public void start(Stage primaryStage) {

        bp = new BorderPane();

        tfWeb = new TextField("https://...");
        tfWeb.setOnAction(e -> lesHtmlFil(true));
        tfLokal = new TextField("C:\\...");
        tfLokal.setOnAction(e -> lesHtmlFil(false));
        
        inputBox = new HBox(5);
        inputBox.getChildren().addAll(new Label("Webside: "), tfWeb, new Label("Fil:"), tfLokal);
        inputBox.setAlignment(Pos.CENTER);

        taTags = new TextArea();
        taTags.setEditable(false);
        taTags.setWrapText(true);

        taKommentarer = new TextArea();
        taKommentarer.setEditable(false);
        taKommentarer.setWrapText(true);

        taKode = new TextArea();
        taKode.setEditable(false);
        taKode.setWrapText(true);

        hBox = new HBox();
        hBox.getChildren().addAll(taTags, taKommentarer, taKode);

        bp.setTop(inputBox);
        bp.setCenter(hBox);

        Scene scene = new Scene(bp, 800, 600);

        primaryStage.setTitle("WebAnalyzer 1.0");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metode som leser en webside/fil. Splitter html koden fra kommentarer og html-tagger. 
     * Skriver så disse ut i hver sitt textarea.
     * 
     * @param web True = webside, false = lokal fil
     */
    private void lesHtmlFil(boolean web) {
        taTags.clear();
        taKommentarer.clear();
        taKode.clear();
        try {
            URL url;
            if (web) {
                url = new URL(tfWeb.getText());
            } else {
                url = new URL("file:" + tfLokal.getText());
            }
            URLConnection conn = url.openConnection();
            InputStream inn = conn.getInputStream();
            Scanner leser = new Scanner(inn);
            tilstand = ordinær_programkode;

            String txt = "";
            while (leser.hasNext()) {
                String s = leser.next();
                txt += " ";
                
                for (char c : s.toCharArray()) {
                    txt += c;

                    if (tilstand == ordinær_programkode) {
                        if (c == '<') {
                            tilstand = lt;
                        } else {
                            taKode.appendText(txt);
                            txt = "";
                        }
                    }

                    else if (tilstand == lt) {
                        if (c == '!') {
                            tilstand = kommentar;
                        } else {
                            tilstand = htmltag;
                        }
                    }

                    else if (tilstand == kommentar) {
                        if (c == '>') {
                            tilstand = ordinær_programkode;
                            taKommentarer.appendText(txt+"\n");
                            txt = "";
                        }
                    }
                    
                    else if (tilstand == htmltag) {
                        if (c == '>') {
                            tilstand = ordinær_programkode;
                            taTags.appendText(txt);
                            txt = "";
                        }
                    }

                }

            }
        } catch (MalformedURLException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
