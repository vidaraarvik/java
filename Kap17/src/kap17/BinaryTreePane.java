package kap17;

/**
 * Klasse som representerer et Pane for å tegne binærtre på.
 * @author Vidar Årvik
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;

import java.util.TreeSet;

public class BinaryTreePane extends Pane {

    BinarySearchTree tree;
    Pane canvas;
    int xRes;
    int yRes;

    /**
     * Konstruktør
     * @param width bredde på vinduet
     * @param height høyde på vinduet
     */
    public BinaryTreePane(int width, int height) {
        canvas = new Pane();
        getChildren().add(canvas);
        this.xRes = width / 10;     // resolution of
        this.yRes = height / 7;     // the binary-tree

        initControls();

        tree = new BinarySearchTree();
        try {
            tree.insert(40);
            tree.insert(20);
            tree.insert(60);
            tree.insert(10);
            tree.insert(30);
            tree.insert(50);
            tree.insert(70);

            tree.setXY();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        draw(tree.root);
    }

    /**
     * Metode som initierer kontrollere til applikasjonen
     */
    private void initControls() {
        Button demoBtn = new Button("Demo");
        demoBtn.setPrefSize(80, 40);
        demoBtn.setStyle("-fx-background-color: lightblue; -fx-font-weight: bold; -fx-text-fill: white;");

        Label nyTxt = new Label("     Ny:");
        Label slettTxt = new Label("     Slett:");
        Label feilTxt = new Label("     Feilmelding:");

        nyTxt.setStyle("-fx-font-weight: bold");
        slettTxt.setStyle("-fx-font-weight: bold");
        feilTxt.setStyle("-fx-font-weight: bold");

        TextField nyTF = new TextField();
        nyTF.setPrefWidth(60);
        TextField slettTF = new TextField();
        slettTF.setPrefWidth(60);
        TextField feilTF = new TextField();
        feilTF.setEditable(false);

        nyTF.setStyle("-fx-border-color: green");
        slettTF.setStyle("-fx-border-color: red");
        feilTF.setStyle("-fx-border-color: yellow");

        demoBtn.setOnAction(e -> {
            feilTF.setText("");
            tree = new BinarySearchTree();
            try {
                TreeSet<Integer> numbers = new TreeSet();
                for (int i = 0; i < 7; i++) {
                    int a = (int) (Math.random() * 100);
                    numbers.add(a);
                }

                for (int n : numbers) {
                    tree.insert(n);
                }

                tree.setXY();
            } catch (Exception e1) {
                feilTF.setText(e1.toString());
            }
            canvas.getChildren().clear();
            draw(tree.root);
        });

        nyTF.setOnAction(e -> {
            try {
                int element = Integer.parseInt(nyTF.getText());
                tree.insert(element);
                tree.setXY();
            } catch (Exception e1) {
                feilTF.setText(e1.toString());
            }
            nyTF.setText("");
            canvas.getChildren().clear();
            draw(tree.root);
        });

        slettTF.setOnAction(e -> {
            try {
                int element = Integer.parseInt(slettTF.getText());
                tree.remove(element);
                tree.setXY();
            } catch (Exception e1) {
                feilTF.setText(e1.toString());
            }
            slettTF.setText("");
            canvas.getChildren().clear();
            draw(tree.root);
        });


        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setPadding(new Insets(15));
        hBox.getChildren().addAll(demoBtn, nyTxt, nyTF, slettTxt, slettTF, feilTxt, feilTF);
        getChildren().add(hBox);

    }

    /**
     * Metode som tegner et binærtre som starter ved node 't'
     * @param t noden som skal tegnes
     */
    private void draw(BinaryNode t) {
        if (t != null) {
            draw(t.left);   // draws left subtree
            draw(t.right);  // draws right subtree

            double x = t.x * xRes;           // current node's-
            double y = (t.y + 2) * yRes;     // x and y values

            // draws element
            Label lbl = new Label(t.element.toString());
            lbl.setTranslateX(x);
            lbl.setTranslateY(y);

            // draws circle
            Circle c = new Circle(x + 7, y + 8, 15);
            c.setFill(null);
            c.setStroke(Color.BLACK);


            // draws line from the node to its parent
            BinaryNode parent = t.parent;
            if (parent != null) {
                double pX = parent.x * xRes;
                double pY = (parent.y + 2) * yRes;
                Line line = new Line(x + 3, y - 8, pX + 3, pY + 24);
                canvas.getChildren().add(line);
            }

            canvas.getChildren().addAll(c, lbl);

        }
    }
}