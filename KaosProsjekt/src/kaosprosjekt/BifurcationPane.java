package kaosprosjekt;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Klasse som representerer et Bifurcation (logistic map) diagram i form av en javafx-pane.
 *
 * @author Vidar Årvik
 */
public class BifurcationPane extends Pane {

    private Button zoomInBtn, zoomOutBtn, leftBtn, rightBtn, upBtn, downBtn;
    private double zoom, xPos, yPos;

    private final int IMGWIDTH;
    private final int IMGHEIGTH;
    private PixelWriter pw;

    BifurcationPane(int width, int height) {
        IMGWIDTH = width;
        IMGHEIGTH = height;
        zoom = 0;
        xPos = 0;
        yPos = 0;

        initImage();
        initControls();
        draw();
    }

    /**
     * Metode som initierer kontrollere til diagrammet - brukt til zooming.
     */
    private void initControls() {
        HBox hBox = new HBox();
        zoomInBtn = new Button("+");
        zoomInBtn.setOnAction(e -> buttonHandler(1));
        zoomOutBtn = new Button("-");
        zoomOutBtn.setOnAction(e -> buttonHandler(2));
        leftBtn = new Button("< LEFT");
        leftBtn.setOnAction(e -> buttonHandler(3));
        rightBtn = new Button("RIGHT >");
        rightBtn.setOnAction(e -> buttonHandler(4));
        upBtn = new Button("UP");
        upBtn.setOnAction(e -> buttonHandler(5));
        downBtn = new Button("DOWN");
        downBtn.setOnAction(e -> buttonHandler(6));
        hBox.getChildren().addAll(zoomInBtn, zoomOutBtn, leftBtn, upBtn, downBtn, rightBtn);
        this.getChildren().add(hBox);

        this.setOnMouseClicked(e -> {
            double x = getRangeValue(0, IMGWIDTH, -1, 1, e.getX());
            double y = getRangeValue(0, IMGHEIGTH, -1, 1, e.getY());
            System.out.println("x: " + x);
            System.out.println("y: " + y);

            //xPos -= x / zoom;
            //yPos -= y / zoom;
            //zoom /= 0.5;
            //draw();
        });
    }

    /**
     * Metode som behandler input i form av knappetrykk.
     *
     * @param i Knapp-verdi som ble utført
     */
    private void buttonHandler(int i) {
        switch (i) {
            case 1: // Zoom in
                zoom += 0.1;
                break;
            case 2: // Zoom out
                zoom -= 0.1;
                break;
            case 3: // Left
                xPos -= 50;
                break;
            case 4: // Right
                xPos += 50;
                break;
            case 5: // UP
                yPos += 50;
                break;
            case 6: // Down
                yPos -= 50;
                break;
        }
        draw();
    }

    /**
     * Metode som initerer et WriteableImage. Dette brukes til å tegne diagrammet på.
     */
    private void initImage() {
        ImageView imgView = new ImageView();
        WritableImage wr = new WritableImage(IMGWIDTH, IMGHEIGTH);
        pw = wr.getPixelWriter();
        imgView.setImage(wr);

        this.getChildren().add(imgView);
    }

    /**
     * Metode som tegner et nytt utsnitt basert på diagrammets variabler.
     */
    private void draw() {
        for (int y = 0; y < IMGHEIGTH; y++) {
            for (int x = 0; x < IMGWIDTH; x++) {
                pw.setColor(x, y, Color.WHITE);
            }
        }

        double minVal = 2.4;
        double maxVal = 4;
        for (double r = minVal; r < maxVal; r += 0.0004) {
            double h = 0.5;
            for (int n = 0; n < 1100; n++) {
                h = r * h * (1 - h);
                if (n > 1000) {
                    int x = (int) (getRangeValue(minVal + zoom, maxVal - zoom, 0, IMGWIDTH, r) + xPos);
                    int y = (int) (getRangeValue(1 - zoom, 0 + zoom, 0, IMGHEIGTH, h) - yPos);

                    if (x >= 0 && x < IMGWIDTH && y >= 0 && y < IMGHEIGTH) {
                        pw.setColor(x, y, Color.BLACK);

                    }
                }
            }
        }

    }

    /**
     * Metode som konverter en vilkårlig verdi fra range1 til en ny verdi i range2.
     *
     * @param oldMin Gammel minimum verdi
     * @param oldMax Gammel maksimum verdi
     * @param newMin Ny minimum verdi
     * @param newMax Ny maksimum verdi
     * @param oldValue Gammel verdi
     * @return Ny verdi
     */
    private double getRangeValue(double oldMin, double oldMax, double newMin, double newMax, double oldValue) {
        double oldRange = (oldMax - oldMin);

        double newRange = (newMax - newMin);

        double newValue = (((oldValue - oldMin) * newRange) / oldRange) + newMin;
        return newValue;
    }

}
