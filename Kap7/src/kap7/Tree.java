package kap7;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Tree {

    drawBranch draw;
    Canvas canvas;
    GraphicsContext g;
    Random rnd;
    public ArrayList<drawBranch> branches = new ArrayList<>();
    ;

    private double startX, startY, width;
    public int count = 0, iterations;
    public double vinkel2 = 20;

    public Tree(Canvas canvas, double startX, double startY) {
        this.canvas = canvas;
        this.startX = startX;
        this.startY = startY;

        rnd = new Random();
        g = canvas.getGraphicsContext2D();
    }

    public void widthLine(double width) {

        for (int i = 1; i < width; i++) {
            this.width = width;
        }
    }

    public void branch(double x1, double y1, double vinkel, int nivå, double length) {
        vinkel2 = vinkel - 50;
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        branches.add(new drawBranch(startX, startY, -90, nivå, 200));
    }

    public class drawBranch {

        public drawBranch(double x1, double y1, double vinkel, int nivå, double length) {
            drawing(x1, y1, vinkel, nivå, length);
        }

        public void drawing(double x1, double y1, double vinkel, int nivå, double length) {

            double x2 = x1 + (int) (Math.cos(Math.toRadians(vinkel)) * length);
            double y2 = y1 + (int) (Math.sin(Math.toRadians(vinkel)) * length);

            if (nivå == 0) {
            } else if (nivå <= 3) {
                g.strokeLine(x1, y1, x2, y2);
                g.setLineWidth(width / 1.6);
                g.setStroke(Color.GREEN);
                g.strokeLine(x1, y1, x2, y2);
                branches.add(new drawBranch(x2, y2, (int) Math.ceil(Math.random() * 100) - vinkel - vinkel2, nivå - 1, length / 1.35));
                branches.add(new drawBranch(x2, y2, (int) Math.ceil(Math.random() * 100) + vinkel + vinkel2, nivå - 1, length / 1.35));
            } else if (10 == nivå) {
                g.setLineWidth(width * 5);
                g.setStroke(Color.ROSYBROWN);
                g.strokeLine(x1, y1, x2, y2);
                branches.add(new drawBranch(x2, y2, vinkel + vinkel2, nivå - 1, length / 1.35));
                branches.add(new drawBranch(x2, y2, vinkel - vinkel2, nivå - 1, length / 1.35));

            } else {
                g.setLineWidth(width);
                g.setStroke(Color.BROWN);
                g.strokeLine(x1, y1, x2, y2);
                branches.add(new drawBranch(x2, y2, vinkel + vinkel2, nivå - 1, length / 1.40));
                branches.add(new drawBranch(x2, y2, vinkel - vinkel2, nivå - 1, length / 1.35));

            }
           

        }

    }
}
