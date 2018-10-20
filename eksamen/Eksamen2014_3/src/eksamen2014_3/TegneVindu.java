package eksamen2014_3;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TegneVindu extends Pane {

    private ContainerModell cm;
    
    private VBox root, infoBox;
    private HBox rectBox;
    

    public TegneVindu(ContainerModell cm) {
        this.cm = cm;
        
        root = new VBox(100);
        rectBox = new HBox(30);
        rectBox.setPrefWidth(200);
        infoBox = new VBox();
        
        

        ArrayList<Container> ledige = cm.getLedige();

        for (int i = 0; i < ledige.size(); i++) {
            Rectangle r = new Rectangle(40, 40, Color.BLUE);
            Label l = new Label("" + ledige.get(i).getVolum());
            l.setStyle("-fx-text-fill: white;");
            
            StackPane sp = new StackPane();
            sp.getChildren().addAll(r, l);
            rectBox.getChildren().add(sp);
        }
        
        int dagOppdrag = 0;
        int timeOppdrag = 0;
        for (Oppdrag op : cm.getOppdrag()) {
            if (op instanceof DagOppdrag) {
                dagOppdrag++;
            } else {
                timeOppdrag++;
            }
        }
        
        Label antDagOppdrag = new Label("Antall dag-oppdrag = " + dagOppdrag);
        Label antTimeOppdrag = new Label("Antall time-oppdrag = " + timeOppdrag);
        Label antContainere = new Label("Antall containere = " + ledige.size());

        infoBox.getChildren().addAll(antContainere, antDagOppdrag, antTimeOppdrag);
        root.getChildren().addAll(rectBox, infoBox);
        this.getChildren().add(root);
    }

}
