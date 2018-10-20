package eksamen2016_2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProveGUI extends Application {

    // Modell
    private Prove prove;
    private Sporsmaal spm;
    private int poengsum;
    private Rangering rangering;

    // GUI
    private BorderPane root;

    private VBox topBox;
    private TextField kallenavnTf;
    private Label spmTekst;
    private Button svarBtn;

    private VBox flervalgBox;
    private CheckBox cb1, cb2, cb3, cb4;
    private TextField kortsvarTf;

    @Override
    public void start(Stage primaryStage) {

        initTopBox();
        initflervalgBox();

        kortsvarTf = new TextField();
        svarBtn = new Button("Svar");
        svarBtn.setOnAction(e -> svarHandler());

        root = new BorderPane();
        root.setTop(topBox);
        root.setBottom(svarBtn);

        prove = new Prove();
        prove.lesFraFil();

        nesteSpm();

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Eksamen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void initTopBox() {
        topBox = new VBox();
        kallenavnTf = new TextField("Velg kallenavn");
        spmTekst = new Label();
        topBox.getChildren().addAll(kallenavnTf, spmTekst);
    }

    private void initflervalgBox() {
        cb1 = new CheckBox("test 1");
        cb2 = new CheckBox("test 2");
        cb3 = new CheckBox("test 3");
        cb4 = new CheckBox("test 4");
        flervalgBox = new VBox();
        flervalgBox.getChildren().addAll(cb1, cb2, cb3, cb4);
    }

    private void nesteSpm() {
        spm = prove.neste();
        if (spm != null) {
            spmTekst.setText(spm.spTekst);

            if (spm instanceof FlervalgSporsmaal) {
                FlervalgSporsmaal fspm = (FlervalgSporsmaal) spm;

                String[] alt = fspm.getAlternativer();
                cb1.setText(alt[0]);
                cb2.setText(alt[1]);
                cb3.setText(alt[2]);
                cb4.setText(alt[3]);

                root.setCenter(flervalgBox);
            } else {
                root.setCenter(kortsvarTf);
            }
        } else {
            proveFerdig();
        }
    }

    private void svarHandler() {
        if (spm instanceof FlervalgSporsmaal) {
            String svar = "";
            if (cb1.isSelected()) {
                svar += "A";
            }
            if (cb2.isSelected()) {
                svar += "B";
            }
            if (cb3.isSelected()) {
                svar += "C";
            }
            if (cb4.isSelected()) {
                svar += "D";
            }

            poengsum += spm.poeng(svar);
        }

        if (spm instanceof KortsvarSporsmaal) {
            String svar = kortsvarTf.getText();
            poengsum += spm.poeng(svar);
        }

        nesteSpm();

        System.out.println(poengsum);
    }

    private void proveFerdig() {
        String navn = kallenavnTf.getText();
        if (navn.length() == 0) {
            navn = "Anonym";
        }
        
        Resultat res = new Resultat(navn);
        res.setPoengsum(poengsum);
        
        rangering = new Rangering();
        rangering.leggTilResultat(res);
        rangering.printResultater();
    }
}
