package kaosprosjekt;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Klasse som representerer et mandelbrot diagram i form av en javafx-pane.
 * 
 * @author Vidar Årvik
 */
public class MandelbrotPane extends Pane {

    private final int MAXITERATIONS = 500;
    private Button zoomInBtn, zoomOutBtn, leftBtn, rightBtn, upBtn, downBtn;
    private double zoom, xPos, yPos;

    private final int IMGWIDTH;
    private final int IMGHEIGTH;
    private PixelWriter pw;

    MandelbrotPane(int width, int height) {
        IMGWIDTH = width;
        IMGHEIGTH = height;
        zoom = 1;
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

            xPos -= x / zoom;
            yPos -= y / zoom;
            zoom /= 0.5;
            draw();
        });
    }

    /**
     * Metode som behandler input i form av knappetrykk.
     * @param i Knapp-verdi som ble utført
     */
    private void buttonHandler(int i) {
        switch (i) {
            case 1: // Zoom in
                zoom /= 0.5;
                break;
            case 2: // Zoom out
                zoom *= 0.5;
                break;
            case 3: // Left
                xPos += 1 / zoom;
                break;
            case 4: // Right
                xPos -= 1 / zoom;
                break;
            case 5: // UP
                yPos += 1 / zoom;
                break;
            case 6: // Down
                yPos -= 1 / zoom;
                break;
        }
        draw();
    }

    /**
     * Metode som initerer et WriteableImage.
     * Dette brukes til å tegne diagrammet på.
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
        double minRe = -2.0;
        double maxRe = 1.0;
        double minIm = -1.2;

        minRe = (minRe / zoom) - xPos;
        maxRe = (maxRe / zoom) - xPos;
        minIm = minIm / zoom + yPos;

        double maxIm = minIm + (maxRe - minRe) * IMGHEIGTH / IMGWIDTH;
        double Re_factor = (maxRe - minRe) / (IMGWIDTH - 1);
        double Im_factor = (maxIm - minIm) / (IMGHEIGTH - 1);

        for (int y = 0; y < IMGHEIGTH; ++y) {
            double c_im = maxIm - y * Im_factor;
            for (int x = 0; x < IMGWIDTH; ++x) {
                double c_re = minRe + x * Re_factor;

                double Z_re = c_re, Z_im = c_im;
                int n;
                for (n = 0; n < MAXITERATIONS; ++n) {
                    double Z_re2 = Z_re * Z_re, Z_im2 = Z_im * Z_im;
                    if (Z_re2 + Z_im2 > 4) {
                        break;
                    }
                    Z_im = 2 * Z_re * Z_im + c_im;
                    Z_re = Z_re2 - Z_im2 + c_re;
                }
                if (n == MAXITERATIONS) {
                    pw.setColor(x, y, Color.BLACK);
                } else if (n < MAXITERATIONS / 2 - 1) {
                    pw.setColor(x, y, Color.rgb(getRgbValue(n, false), 0, 0));
                } else if (n > MAXITERATIONS / 2 && n < MAXITERATIONS - 1) {
                    pw.setColor(x, y, Color.rgb(255, getRgbValue(n, true), getRgbValue(n, true)));
                }
            }
        }
    }

    /**
     * Metode som konverterer antall iterasjoner n
     * til et heltall mellom newMin og newMax.
     * @param n Antall iterasjoner
     * @param redToWhite Fargen skal gå fra rød til hvit
     * @return 
     */
    private int getRgbValue(int n, boolean redToWhite) {
        int oldMin = 0;
        int oldMax = MAXITERATIONS;
        int oldRange = (oldMax - oldMin);

        int newMin, newMax;
        if (redToWhite) {
            newMin = 0;
            newMax = 100;
        } else {
            newMin = 25;
            newMax = 255;
        }

        int newRange = (newMax - newMin);

        return (((n - oldMin) * newRange) / oldRange) + newMin;
    }

    /**
     * Metode som konverter en vilkårlig verdi fra range1 til en ny verdi i range2.
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
