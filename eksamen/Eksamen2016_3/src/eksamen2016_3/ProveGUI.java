package eksamen2016_3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProveGUI extends Application {

    // Modell
    private Prove prove;
    private int poengsum;
    private Sporsmaal spm;
    private Rangering rangering;

    // GUI
    private VBox root;
    private TextField kallenavnTf, kortsvarTf;
    private Label spmTekst;
    private CheckBox cbA, cbB, cbC, cbD;
    private Button svarBtn;

    @Override
    public void start(Stage primaryStage) {

        initContent();

        nesteSpm();

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Eksamen 2016");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initContent() {
        root = new VBox();
        kallenavnTf = new TextField("Velg kallenavn");
        kortsvarTf = new TextField();
        spmTekst = new Label();
        cbA = new CheckBox();
        cbB = new CheckBox();
        cbC = new CheckBox();
        cbD = new CheckBox();
        svarBtn = new Button("Svar");

        kallenavnTf.setOnAction(e -> kallenavnTf.setEditable(false));
        svarBtn.setOnAction(e -> svarBtnActionPerformed());

        root.getChildren().addAll(kallenavnTf,
                spmTekst,
                cbA, cbB, cbC, cbD,
                kortsvarTf,
                svarBtn);
    }

    private void svarBtnActionPerformed() {
        if (spm instanceof KortsvarSporsmaal) {
            poengsum += spm.poeng(kortsvarTf.getText());
            System.out.println(poengsum);   // testing
        }
        
        if (spm instanceof FlervalgSporsmaal) {
            String svar = "";
            if (cbA.isSelected()) {
                svar += "A";
            }
            if (cbB.isSelected()) {
                svar += "B";
            }
            if (cbC.isSelected()) {
                svar += "C";
            }
            if (cbD.isSelected()) {
                svar += "D";
            }
            
            poengsum += spm.poeng(svar);
            System.out.println(poengsum);   // testing
        }
        
        nesteSpm();
    }

    private void nesteSpm() {
        if (prove == null) {
            prove = new Prove();
            prove.lesFraFil();
            
            poengsum = 0;
            rangering = new Rangering();
        }

        spm = prove.neste();
        if (spm instanceof KortsvarSporsmaal) {
            cbA.setVisible(false);
            cbB.setVisible(false);
            cbC.setVisible(false);
            cbD.setVisible(false);
            kortsvarTf.setVisible(true);

            spmTekst.setText(((KortsvarSporsmaal) spm).spTekst);
        }

        if (spm instanceof FlervalgSporsmaal) {
            cbA.setVisible(true);
            cbB.setVisible(true);
            cbC.setVisible(true);
            cbD.setVisible(true);
            kortsvarTf.setVisible(false);
            
            spmTekst.setText(((FlervalgSporsmaal) spm).spTekst);
            cbA.setText(((FlervalgSporsmaal) spm).getA());
            cbB.setText(((FlervalgSporsmaal) spm).getB());
            cbC.setText(((FlervalgSporsmaal) spm).getC());
            cbD.setText(((FlervalgSporsmaal) spm).getD());
        }
        
        if (spm == null) {
            String kallenavn = kallenavnTf.getText();
            if (kallenavn.isEmpty()) {
                kallenavn = "NoName";
            }
            rangering.leggTilResultat(new Resultat(kallenavn, poengsum));
            rangering.skrivUtListe();
        }
    }

}
