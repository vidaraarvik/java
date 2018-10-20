package eksamen2016;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProveGUI extends Application {

    // GUI
    private BorderPane bp = new BorderPane();
    private FlervalgPane fp = new FlervalgPane();
    private KortsvarPane kp = new KortsvarPane();

    private VBox vbox = new VBox(10);
    private TextField nick = new TextField();
    private Label spm = new Label();
    private Button svarBtn = new Button("Svar");

    // Model
    Prove prove = new Prove();
    Sporsmaal gjeldendeSpm;

    @Override
    public void start(Stage stage) {

        initPane();
        lesFraFil();
        nesteSpm();

        Scene scene = new Scene(bp, 300, 250);

        stage.setTitle("Eksamen 2016");
        stage.setScene(scene);
        stage.show();
        System.out.println("hei");
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initPane() {
        vbox.getChildren().addAll(nick, spm);
        nick.setOnAction(e -> nick.setEditable(false));

        bp.setTop(vbox);
        bp.setBottom(svarBtn);

        svarBtn.setOnAction(e -> sjekkSvar());
    }

    private void lesFraFil() {
        prove.lesFraFil();
    }

    private void nesteSpm() {
        Sporsmaal s = prove.neste();
        gjeldendeSpm = s;

        if (s.spørsmålstype().equals("Flervalg")) {
            FlervalgSporsmaal fs = (FlervalgSporsmaal) s;

            spm.setText(fs.getSpTekst());

            String[] alt = fs.getAlternativer();
            fp.setAl(alt[0]);
            fp.setBl(alt[1]);
            fp.setCl(alt[2]);
            fp.setDl(alt[3]);
            bp.setCenter(fp);

        }

        if (s.spørsmålstype().equals("Kortsvar")) {
            KortsvarSporsmaal ks = (KortsvarSporsmaal) s;

            spm.setText(ks.getSpTekst());

            bp.setCenter(kp);
        }
    }

    private void sjekkSvar() {

        if (gjeldendeSpm.spørsmålstype().equals("Flervalg")) {
            String svar = "";
            if (fp.getA().selectedProperty().getValue()) {
                svar += "A";
            }
            if (fp.getB().selectedProperty().getValue()) {
                svar += "B";
            }
            if (fp.getC().selectedProperty().getValue()) {
                svar += "C";
            }
            if (fp.getD().selectedProperty().getValue()) {
                svar += "D";
            }
            
            int poeng = gjeldendeSpm.poeng(svar);
            Resultat res = new Resultat(nick.getText());
            res.addPoeng(poeng);
            
            resetPanes();
        }

        if (gjeldendeSpm.spørsmålstype().equals("Kortsvar")) {
            int poeng = gjeldendeSpm.poeng(kp.getSvar());
            Resultat res = new Resultat(nick.getText());
            res.addPoeng(poeng);
            
            resetPanes();
        }
        
        nesteSpm();
    }

    private void resetPanes() {
        kp.removeSvar();
        fp.getA().selectedProperty().set(false);
        fp.getB().selectedProperty().set(false);
        fp.getC().selectedProperty().set(false);
        fp.getD().selectedProperty().set(false);
    }

}
