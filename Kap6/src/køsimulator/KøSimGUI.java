package køsimulator;

import java.util.LinkedList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class KøSimGUI extends Application {

    private LinkedList<Person> køListe;
    private LinkedList<Person> betjentListe;

    private AnimationTimer timer;

    private VBox root;
    private FlowPane grafisk;
    private Button nyKundeBtn, betjenBtn, statistikkBtn;
    private TextField personTf;

    @Override
    public void start(Stage primaryStage) {
        initContent();

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initContent() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        køListe = new LinkedList();
        betjentListe = new LinkedList();
        root = new VBox();
        nyKundeBtn = new Button("Ny kunde");
        nyKundeBtn.setOnAction(e -> nyKundeBtnHandler());
        betjenBtn = new Button("Betjen kunde");
        betjenBtn.setOnAction(e -> betjenBtnHandler());
        statistikkBtn = new Button("Hent statistikk");
        statistikkBtn.setOnAction(e -> statistikkBtnHandler());
        personTf = new TextField();
        grafisk = new FlowPane(5, 5);
        
        root.getChildren().addAll(nyKundeBtn, new Label("Kundenavn"), personTf, betjenBtn, statistikkBtn, grafisk);
    }

    private void onUpdate() {
        
    }

    private void nyKundeBtnHandler() {
        Person p = new Person(personTf.getText());
        p.stillIKø();
        køListe.addFirst(p);
        
        updateRectangles();
    }

    private void betjenBtnHandler() {
        køListe.getFirst().betjen();
        betjentListe.add(køListe.pop());
        
        updateRectangles();
    }

    private void statistikkBtnHandler() {
        for (Person p : betjentListe) {
            System.out.println(p.toString());
        }
    }

    private void updateRectangles() {
        grafisk.getChildren().clear();
        
        for (Person p : køListe) {
            StackPane sp = new StackPane();
            Rectangle r = new Rectangle(60, 60, Color.WHITE);
            Label l = new Label(p.getNavn());
            
            sp.getChildren().addAll(r, l);
            
            grafisk.getChildren().add(sp);
        }
    }

}
