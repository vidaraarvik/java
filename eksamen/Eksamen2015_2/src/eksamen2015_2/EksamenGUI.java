package eksamen2015_2;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EksamenGUI extends Application {

    // Modell
    private ArrayList<Togsett> alleTogsett;

    // GUI
    private BorderPane root;
    private HBox toppBox;
    private TextField søkeFelt;
    private Button finnGodsBtn;
    private TextArea togsettFelt;
    private TextField godsFelt;

    @Override
    public void start(Stage stage) {

        initContent();

        hentTogsett();

        Scene scene = new Scene(root, 300, 250);
        stage.setTitle("Eksamen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initContent() {
        root = new BorderPane();
        toppBox = new HBox();
        søkeFelt = new TextField();
        finnGodsBtn = new Button("Finn gods");
        togsettFelt = new TextArea();
        godsFelt = new TextField();

        finnGodsBtn.setOnAction(e -> finnGods());

        toppBox.getChildren().addAll(søkeFelt, finnGodsBtn);
        root.setTop(toppBox);
        root.setCenter(togsettFelt);
        root.setBottom(godsFelt);
    }

    private void hentTogsett() {
        alleTogsett = Jernbane.hentAlleTogsett();

        for (Togsett ts : alleTogsett) {
            togsettFelt.appendText(ts.toString() + "\n");
        }
    }

    private void finnGods() {
        for (Togsett ts : alleTogsett) {
            int godsId = Integer.parseInt(søkeFelt.getText());
            if (ts.harGods(godsId)) {
                godsFelt.setText(ts.finnGods(godsId).toString()
                        + " tilhører togsett " + ts.getId());
            } else {
                godsFelt.setText("Finner ikke gitt gods-Id");
            }

        }
    }

}
