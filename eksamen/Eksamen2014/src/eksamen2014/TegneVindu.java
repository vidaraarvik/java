package eksamen2014;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TegneVindu extends Pane {

    ContainerModell cm = new ContainerModell();

    public TegneVindu() {
        setPrefSize(500, 400);

        HBox box1 = new HBox(20);
        VBox box2 = new VBox(5);

        for (Container c : cm.getLedigeListe()) {
            
            Rectangle r = new Rectangle(40, 40, Color.BLUE);
            
            box1.getChildren().addAll(r, new Label(Integer.toString(c.getVolum())));
        }
        
        

        getChildren().add(box1);
    }

}
