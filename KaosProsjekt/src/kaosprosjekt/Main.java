package kaosprosjekt;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Program som tegner og viser fram ulike diagrammer og fraktaler.
 * Programmert av Vidar, Magnus og Kasim.
 *
 * @author Vidar Årvik
 */
public class Main extends Application {

    private int WIDTH = 800;
    private int HEIGTH = 800;

    private Tab tabMan1, tabMan2, tabBif, tabCel, tabGoL;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.TOP);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabMan1 = new Tab();
        tabMan1.setText("Mandelbrot 1");
        tabMan2 = new Tab();
        tabMan2.setText("Mandelbrot 2");
        tabBif = new Tab();
        tabBif.setText("Bifurcation");
        tabCel = new Tab();
        tabCel.setText("Cellulær automat");
        tabGoL = new Tab();
        tabGoL.setText("Conway's Game of Life");
        tabPane.getTabs().addAll(tabMan1, tabMan2, tabBif, tabCel, tabGoL);
        root.setTop(tabPane);

        drawContent();

        Scene scene = new Scene(root, WIDTH, HEIGTH);
        scene.widthProperty().addListener(e -> {
            WIDTH = (int) scene.getWidth();
            drawContent();
        });
        scene.heightProperty().addListener(e -> {
            HEIGTH = (int) scene.getHeight();
            drawContent();
        });
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
     * Metode som initierer de ulike diagrammene.
     */
    private void drawContent() {
        mandelTab manTab = new mandelTab();
        tabMan1.setContent(manTab.init());
        manTab.plotPoints();

        MandelbrotPane manPane = new MandelbrotPane(WIDTH, HEIGTH);
        tabMan2.setContent(manPane);
        BifurcationPane bifPane = new BifurcationPane(WIDTH, HEIGTH);
        tabBif.setContent(bifPane);
        CellularAutomatonPane celPane = new CellularAutomatonPane(WIDTH, HEIGTH);
        tabCel.setContent(celPane);
        GameOfLifePane golPane = new GameOfLifePane(WIDTH, HEIGTH);
        tabGoL.setContent(golPane);
    }

}
