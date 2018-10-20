package tegne;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class TegneApp extends Application {
    
    private RadioButton btnSirkel;
    private Pane lerret;
    private double x1, x2, y1, y2;
    
    @Override
    public void start(Stage primaryStage) {

        lerret = new Pane();

        lerret.setOnMousePressed(new StartLytter());
        lerret.setOnMouseReleased(new SluttLytter());

        Scene scene = new Scene(lerret, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    class StartLytter implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            x1 = event.getX();
            y1 = event.getY();
        }

    }

    class SluttLytter implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            x2 = event.getX();
            y2 = event.getY();
            
            if (x1 > x2) {
                double tmp = x1;
                x1 = x2;
                x2 = tmp;
            }
            
            if (y1 > y2) {
                double tmp = y1;
                y1 = y2;
                y2 = tmp;
            }
            
            
            
            //if (btnSirkel.isSelected()) {
                Shape e = new Ellipse((x1+x2)/2, (y1+y2)/2, (x2-x1)/2, (y2-y1)/2);
            //}
            // else if...
            
            lerret.getChildren().add(e);
        }

    }

}
