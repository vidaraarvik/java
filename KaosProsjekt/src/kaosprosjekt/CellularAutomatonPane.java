package kaosprosjekt;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Klasse som representerer en Cellulær automat i form av en javafx-pane.
 *
 * @author Vidar Årvik
 */
public class CellularAutomatonPane extends Pane {

    private int[] ruleset, ruleset30, ruleset54, ruleset60, ruleset62, ruleset90, ruleset94,
            ruleset102, ruleset110, ruleset122, ruleset126, ruleset150, ruleset158, ruleset182,
            ruleset188, ruleset190, ruleset220, ruleset222, ruleset250;
    private ComboBox comboBox;

    private final int IMGWIDTH;
    private final int IMGHEIGTH;
    private PixelWriter pw;

    CellularAutomatonPane(int width, int height) {
        IMGWIDTH = width;
        IMGHEIGTH = height;

        initImage();
        initComboBox();
        initRules();
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
     * Metode som fjerner tidligere diagram.
     */
    private void clearImage() {
        for (int x = 0; x < IMGWIDTH; x++) {
            for (int y = 0; y < IMGHEIGTH; y++) {
                pw.setColor(x, y, Color.WHITESMOKE);
            }
        }
    }

    /**
     * Metode som initierer kontrollere til diagrammet.
     */
    private void initComboBox() {
        comboBox = new ComboBox();
        comboBox.getItems().add(30);
        comboBox.getItems().add(54);
        comboBox.getItems().add(60);
        comboBox.getItems().add(62);
        comboBox.getItems().add(90);
        comboBox.getItems().add(102);
        comboBox.getItems().add(110);
        comboBox.getItems().add(122);
        comboBox.getItems().add(126);
        comboBox.getItems().add(150);
        comboBox.getItems().add(158);
        comboBox.getItems().add(182);
        comboBox.getItems().add(188);
        comboBox.getItems().add(190);
        comboBox.getItems().add(220);
        comboBox.getItems().add(222);
        comboBox.getItems().add(250);
        comboBox.setPrefWidth(100);
        comboBox.setOnAction(e -> changeRules());

        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(10));
        hBox.getChildren().addAll(new Label("Select ruleset"), comboBox);
        this.getChildren().add(hBox);
    }

    /**
     * Metode som tegner et nytt utsnitt basert på objektets variabler.
     */
    private void draw() {
        int[] prevGen = new int[IMGWIDTH];
        for (int i = 0; i < prevGen.length; i++) {
            if (i == prevGen.length / 2) {
                prevGen[i] = 1;
            } else {
                prevGen[i] = 0;
            }
        }

        for (int y = 0; y < IMGHEIGTH; y++) {
            drawGen(prevGen, y);
            int[] nextGen = getNextGen(prevGen);
            prevGen = nextGen;
        }

    }

    /**
     * Metode som tegner nåværende generasjon.
     */
    private void drawGen(int[] gen, int y) {
        for (int x = 0; x < gen.length; x++) {
            if (gen[x] == 1) {
                pw.setColor(x, y, Color.BLACK);
            }
        }
    }

    /**
     * Metode som kalkulerer neste generasjon.
     */
    private int[] getNextGen(int[] prevGen) {
        int[] nextGen = new int[prevGen.length];

        for (int i = 1; i < prevGen.length - 1 /*Må kanskje fikse noe her.. */; i++) {
            int left = prevGen[i - 1];
            int me = prevGen[i];
            int right = prevGen[i + 1];

            nextGen[i] = rules(left, me, right);
        }

        return nextGen;
    }

    private int rules(int left, int me, int right) {
        String abc = "" + left + me + right;

        switch (abc) {
            case "111":
                return ruleset[0];
            case "110":
                return ruleset[1];
            case "101":
                return ruleset[2];
            case "100":
                return ruleset[3];
            case "011":
                return ruleset[4];
            case "010":
                return ruleset[5];
            case "001":
                return ruleset[6];
            case "000":
                return ruleset[7];
        }
        return 0;
    }

    /**
     * Metode som initierer regler som brukeren kan velge mellom.
     */
    private void initRules() {
        ruleset30 = new int[]{0, 0, 0, 1, 1, 1, 1, 0};   // rule 30
        ruleset54 = new int[]{0, 0, 1, 1, 0, 1, 1, 0};   // rule 54
        ruleset60 = new int[]{0, 0, 1, 1, 1, 1, 0, 0};   // rule 60
        ruleset62 = new int[]{0, 0, 1, 1, 1, 1, 1, 0};   // rule 62
        ruleset90 = new int[]{0, 1, 0, 1, 1, 0, 1, 0};   // rule 90
        ruleset94 = new int[]{0, 1, 0, 1, 1, 1, 1, 0};   // rule 94
        ruleset102 = new int[]{0, 1, 1, 0, 0, 1, 1, 0};   // rule 102
        ruleset110 = new int[]{0, 1, 1, 0, 1, 1, 1, 0};   // rule 110
        ruleset122 = new int[]{0, 1, 1, 1, 1, 0, 1, 0};   // rule 122
        ruleset126 = new int[]{0, 1, 1, 1, 1, 1, 1, 0};   // rule 126
        ruleset150 = new int[]{1, 0, 0, 1, 0, 1, 1, 0};   // rule 150
        ruleset158 = new int[]{1, 0, 0, 1, 1, 1, 1, 0};   // rule 158
        ruleset182 = new int[]{1, 0, 1, 1, 0, 1, 1, 0};   // rule 182
        ruleset188 = new int[]{1, 0, 1, 1, 1, 1, 0, 0};   // rule 188
        ruleset190 = new int[]{1, 0, 1, 1, 1, 1, 1, 0};   // rule 190
        ruleset220 = new int[]{1, 1, 0, 1, 1, 1, 0, 0};   // rule 220
        ruleset222 = new int[]{1, 1, 0, 1, 1, 1, 1, 0};   // rule 222
        ruleset250 = new int[]{1, 1, 1, 1, 1, 0, 1, 0};   // rule 250

    }

    /**
     * Metode som bytter regelsett.
     */
    private void changeRules() {
        int selected = (int) comboBox.getValue();
        switch (selected) {
            case 30:
                ruleset = ruleset30;
                break;
            case 54:
                ruleset = ruleset54;
                break;
            case 60:
                ruleset = ruleset60;
                break;
            case 62:
                ruleset = ruleset62;
                break;
            case 90:
                ruleset = ruleset90;
                break;
            case 102:
                ruleset = ruleset102;
                break;
            case 110:
                ruleset = ruleset110;
                break;
            case 122:
                ruleset = ruleset122;
                break;
            case 126:
                ruleset = ruleset126;
                break;
            case 150:
                ruleset = ruleset150;
                break;
            case 158:
                ruleset = ruleset158;
                break;
            case 182:
                ruleset = ruleset182;
                break;
            case 188:
                ruleset = ruleset188;
                break;
            case 190:
                ruleset = ruleset190;
                break;
            case 220:
                ruleset = ruleset220;
                break;
            case 222:
                ruleset = ruleset222;
                break;
            case 250:
                ruleset = ruleset250;
                break;
        }
        clearImage();
        draw();
    }

}
