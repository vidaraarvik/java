package eksamen2015;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GodsGUI extends Application {

    //GUI
    BorderPane bp = new BorderPane();
    HBox inputBox = new HBox();
    TextField inputTF = new TextField();
    Button finnBtn = new Button("Finn Gods");
    TextArea togsettTF = new TextArea();
    TextField outputTF = new TextField();

    // Modell
    ArrayList<Togsett> togsettListe;

    @Override
    public void start(Stage stage) {

        initPane();
        hentTogsett();
        updateInfo();

        Scene scene = new Scene(bp, 300, 200);
        stage.setTitle("Eksamen 2015");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initPane() {
        inputBox.getChildren().addAll(inputTF, finnBtn);

        bp.setTop(inputBox);
        bp.setCenter(togsettTF);
        bp.setBottom(outputTF);

        finnBtn.setOnAction(e -> finnGods());
    }

    private void hentTogsett() {
        togsettListe = Jernbane.hentAlleTogsett();
    }

    private void updateInfo() {
        String s = "";
        for (Togsett ts : togsettListe) {
            s += ts.toString();
            s += "\n";
        }
        togsettTF.setText(s);
    }

    private void finnGods() {
        int id = Integer.parseInt(inputTF.getText());

        boolean funnet = false;
        while (!funnet) {
            for (Togsett ts : togsettListe) {
                if (ts.harGods(id)) {
                    outputTF.setText(ts.finnGods(id).toString() + " tilhører togsett " + ts.getTogsettId());
                    funnet = true;
                }
            }
            break;
        }
        if (!funnet) {
            outputTF.setText("Fant ikke gods med ID: " + id);
        }

    }

}
