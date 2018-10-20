package pong;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author speedy
 */
public class PongApp extends Application {

    private AnimationTimer timer;

    private Pane root;

    private Node ball;

    private int dx = 5, dy = 5;

    private Node paddle1, paddle2;

    private Label label1 = new Label("0"), label2 = new Label("0");

    private Boolean wKeyDown = false,
            sKeyDown = false,
            upKeyDown = false,
            downKeyDown = false;

    private Parent createContent() {
        root = new Pane();
        root.setStyle("-fx-background-color: black;");
        root.setPrefSize(800, 600);

        ball = initBall();

        paddle1 = initPaddle(0, 250);
        paddle2 = initPaddle(785, 250);

        initScoreLabels();

        root.getChildren().addAll(ball, paddle1, paddle2, label1, label2);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private Node initBall() {
        Circle circle = new Circle(10, Color.WHITE);
        circle.setTranslateX(100);
        circle.setTranslateY(100);

        return circle;
    }

    private Node initPaddle(double x, double y) {
        Rectangle rect = new Rectangle(10, 60, Color.WHITE);
        rect.setTranslateX(x);
        rect.setTranslateY(y);

        return rect;
    }

    private void initScoreLabels() {
        label1.setTranslateX(250);
        label1.setFont(Font.font(48));
        label2.setTranslateX(550);
        label2.setFont(Font.font(48));
    }

    private void onUpdate() {
        ball.setTranslateX(ball.getTranslateX() + dx);
        ball.setTranslateY(ball.getTranslateY() + dy);

        paddleAI();

        checkInput();

        checkState();
    }

    private void checkState() {
        if (paddle1.getBoundsInParent().intersects(ball.getBoundsInParent()) || paddle2.getBoundsInParent().intersects(ball.getBoundsInParent())) {
            dx *= -1;
        }

        if (ball.getTranslateX() > root.getWidth() - 10 || ball.getTranslateX() < 10) {
            if (ball.getTranslateX() > root.getWidth() - 10) {
                addPoints(label1);
            }
            if (ball.getTranslateX() < 10) {
                addPoints(label2);
            }
            ball.setTranslateX(400);
            ball.setTranslateY(300);

            dy *= -1;
            dx *= -1;
        }

        if (ball.getTranslateY() > root.getHeight() - 10 || ball.getTranslateY() < 10) {
            dy *= -1;
        }
    }

    private void checkInput() {
        if (wKeyDown) {
            paddle1.setTranslateY(paddle1.getTranslateY() - 10);
        }
        if (sKeyDown) {
            paddle1.setTranslateY(paddle1.getTranslateY() + 10);
        }
        if (upKeyDown) {
            paddle2.setTranslateY(paddle2.getTranslateY() - 10);
        }
        if (downKeyDown) {
            paddle2.setTranslateY(paddle2.getTranslateY() + 10);
        }
    }

    private void paddleAI() {
        paddle2.setTranslateY(ball.getTranslateY());
    }

    private void addPoints(Label label) {
        int points = Integer.parseInt(label.getText());
        points++;

        label.setText(String.valueOf(points));
    }

    @Override
    public void start(Stage stage) {

        stage.setScene(new Scene(createContent()));

        stage.getScene().setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    wKeyDown = true;
                    break;
                case S:
                    sKeyDown = true;
                    break;
                case UP:
                    upKeyDown = true;
                    break;
                case DOWN:
                    downKeyDown = true;
                    break;
                default:
                    break;
            }
        });

        stage.getScene().setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case W:
                    wKeyDown = false;
                    break;
                case S:
                    sKeyDown = false;
                    break;
                case UP:
                    upKeyDown = false;
                    break;
                case DOWN:
                    downKeyDown = false;
                    break;
                default:
                    break;
            }
        });

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
