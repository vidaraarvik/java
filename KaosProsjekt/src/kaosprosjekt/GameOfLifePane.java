package kaosprosjekt;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Klasse som representerer et Game of Life spill i form av en javafx-pane.
 *
 * @author Vidar Årvik
 */
public class GameOfLifePane extends Pane {

    // Animation
    private AnimationTimer timer;
    private boolean running;
    private long lastUpdate = 0;
    private long delay = 200000000;

    // Grids and cells
    private Pane gamePane;
    private int resolution = 40;
    private int cols;
    private int rows;
    int[][] grid;

    private final int WIDTH;
    private final int HEIGTH;

    GameOfLifePane(int width, int height) {
        this.WIDTH = width; // Må gjøres dynamisk
        this.HEIGTH = height;// --..--

        initArray();
        initAnimControls();
    }

    /**
     * Metode som initierer kontrollere til spillet.
     */
    private void initAnimControls() {
        // Button to Start/Stopp the animation
        Button btn = new Button("Start/Stopp");
        btn.setOnAction(e -> {
            if (running) {
                timer.stop();
                running = false;
            } else {
                timer.start();
                running = true;
            }
        });
        running = false;

        // Execute Animation
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= delay) {
                    draw();
                    lastUpdate = now;
                }
            }
        };

        // Slider to control animation speed
        Slider slider = new Slider(0, 500, 250);
        slider.setPrefWidth(WIDTH / 10);
        slider.valueProperty().addListener(e -> delay = (long) (slider.getValue() * 1000000));

        // ComboBox to control the game-resolution
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().add("5px");
        comboBox.getItems().add("10px");
        comboBox.getItems().add("20px");
        comboBox.getItems().add("40px");
        comboBox.getItems().add("60px");
        comboBox.setOnAction(e -> changeResolution(comboBox));

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(btn, comboBox, slider);
        this.getChildren().add(hBox);
    }

    /**
     * Metode som bytter oppløsning på spillets celler.
     *
     * @param comboBox Hvilken oppløsning som er valgt i menyen.
     */
    private void changeResolution(ComboBox comboBox) {

        switch ((String) comboBox.getValue()) {
            case "5px":
                resolution = 5;
                break;
            case "10px":
                resolution = 10;
                break;
            case "20px":
                resolution = 20;
                break;
            case "40px":
                resolution = 40;
                break;
            case "60px":
                resolution = 60;
                break;
        }
        this.getChildren().clear();
        initArray();
        initAnimControls();
    }

    /**
     * Metode som initierer en 2D array med celler.
     */
    private void initArray() {
        gamePane = new Pane();
        this.getChildren().add(gamePane);

        cols = WIDTH / resolution;
        rows = HEIGTH / resolution;

        grid = new int[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = (int) (Math.random() * 2);
            }
        }
        draw();
    }

    /**
     * Metode som tegner et nytt utsnitt basert på spill-objektets variabler.
     */
    private void draw() {
        gamePane.getChildren().clear();

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                int x = i * resolution;
                int y = j * resolution;
                if (grid[i][j] == 1) {
                    Rectangle rect = new Rectangle(x, y, resolution - 1, resolution - 1);
                    rect.setFill(Color.BLACK);
                    gamePane.getChildren().add(rect);
                }
            }
        }

        int[][] next = new int[cols][rows];

        // Compute next based on grid
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                int state = grid[i][j];

                // Count alive neighbors
                int sum = 0;
                int neighbors = countNeighbors(grid, i, j);

                if (state == 0 && neighbors == 3) {
                    next[i][j] = 1;
                } else if (state == 1 && (neighbors < 2 || neighbors > 3)) {
                    next[i][j] = 0;
                } else {
                    next[i][j] = state;
                }

            }
        }

        grid = next;
    }

    /**
     * Metode som teller antall naboer rundt en gitt celle.
     * @param grid Spillbrettet
     * @param x x posisjonen til cellen
     * @param y y posisjonen til cellen
     * @return Antall naboer
     */
    private int countNeighbors(int[][] grid, int x, int y) {
        int sum = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int col = (x + i + cols) % cols;
                int row = (y + j + rows) % rows;

                sum += grid[col][row];
            }
        }
        sum -= grid[x][y];
        return sum;
    }

}
