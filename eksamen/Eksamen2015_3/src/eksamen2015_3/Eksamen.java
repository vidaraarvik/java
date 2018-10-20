package eksamen2015_3;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Eksamen extends Application {

    // Modell
    private ArrayList<Togsett> alleTogsett;
    
    // GUI
    private BorderPane root;
    private HBox inputBox;
    private TextField godsTf, infoTf;
    private Button finnGodsBtn;
    private TextArea togsettFelt;

    @Override
    public void start(Stage primaryStage) {

        initContent();
        
        hentTogsett();

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Eksamen 2015");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initContent() {
        root = new BorderPane();
        inputBox = new HBox();
        godsTf = new TextField();
        infoTf = new TextField();
        finnGodsBtn = new Button("Finn gods");
        togsettFelt = new TextArea();

        inputBox.getChildren().addAll(godsTf, finnGodsBtn);
        root.setTop(inputBox);
        root.setCenter(togsettFelt);
        root.setBottom(infoTf);
        
        finnGodsBtn.setOnAction(e -> finnGodsBtnActionPerformed());
    }

    private void hentTogsett() {
        alleTogsett = Jernbane.hentAlleTogsett();
        
        for (Togsett ts : alleTogsett) {
            togsettFelt.appendText(ts.toString() + "\n");
        }
    }

    private void finnGodsBtnActionPerformed() {
        int godsId = Integer.parseInt(godsTf.getText());
        for (Togsett ts : alleTogsett) {
            if (ts.harGods(godsId)) {
                infoTf.setText(ts.finnGods(godsId).toString() + " tilhører togsett " + ts.getId());
            }
        }
    }

}
