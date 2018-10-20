/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kaosprosjekt;




import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.paint.Color;



/**
 *
 * @author Magnus
 */
public class mandelTab extends Pane {
    private int numIter = 200;
    private double zoom = 100;
    private double initialZoom;
    private double zoomIncrease = 130;
    private int colorIter = 20;
    private Canvas canvas;
    
    GraphicsContext gc;
    private double zx, zy, cx, cy, temp;
    private int xMove, yMove = 0;
    Scene scene;
    Scene scene2;
    BorderPane tester;
    
    
    
    private TabPane tabPane;
    private HBox bottom;
    
       Button ctrlknapp1;
       Button ctrlknapp2;
       Button ctrlknapp3;
       Button ctrlknapp4;
       Button ctrlknapp5;
       Button ctrlknapp6;
       
       
    
    
     
    public BorderPane init(){
        
      
        ctrlknapp1 = new Button("up");
        ctrlknapp2 = new Button("down");
        ctrlknapp3 = new Button ("left");
        ctrlknapp4 = new Button("right");
        ctrlknapp5 = new Button("+");
        ctrlknapp6 = new Button("-");
      
        ctrlknapp1.setOnAction((new knappeKontroll1()));
        ctrlknapp2.setOnAction((new knappeKontroll2()));
        ctrlknapp3.setOnAction((new knappeKontroll3()));
        ctrlknapp4.setOnAction((new knappeKontroll4()));
        ctrlknapp5.setOnAction((new knappeKontroll5()));
        ctrlknapp6.setOnAction((new knappeKontroll6()));
        
        canvas = new Canvas(600,600);
        gc = canvas.getGraphicsContext2D();
        bottom = new HBox(30);
        bottom.setPadding(new Insets(10));
        bottom.setAlignment(Pos.CENTER);
        
        bottom.getChildren().addAll(ctrlknapp1,ctrlknapp2,ctrlknapp3,ctrlknapp4,ctrlknapp5,ctrlknapp6);
        tester = new BorderPane();
        tester.setTop(bottom);
        tester.setCenter(canvas);
        
        
        return tester;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
      public void plotPoints(){
        for (int y = 0; y < canvas.getHeight(); y++) {
            for (int x = 0; x < canvas.getWidth(); x++) {
                zx = zy = 0;
                cx = (x - canvas.getWidth()/2+xMove) / zoom;
                cy = (y - canvas.getHeight()/2+yMove) / zoom;
                int iter = numIter;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    temp = zx * zx - zy * zy + cx;
                    zy = 2 * zx * zy + cy;
                    zx = temp;
                    iter--;
                  
                    
                }
                
                gc.setFill(Color.rgb(iter,iter ,iter));
                gc.fillRect(x, y, 1, 1);
                
            }
        }
    }
      private class knappeKontroll1 implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
           yMove-=canvas.getHeight()/4;
        plotPoints();    
      }
    }
       private class knappeKontroll2 implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
           yMove+= canvas.getHeight()/4;
        plotPoints();    
      }
    }
        private class knappeKontroll3 implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
           xMove-=canvas.getWidth()/4;
        plotPoints();    
      }
    }
         private class knappeKontroll4 implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
           xMove+=canvas.getWidth()/4;
        plotPoints();    
      }
    }
          private class knappeKontroll5 implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            initialZoom = zoom;
            zoom+=zoomIncrease;
            zoomIncrease*=2;
            xMove*=2;
            yMove*=2;
        plotPoints();    
      }
    }
           private class knappeKontroll6 implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            
            zoom-=100;
            
            
           
            
        plotPoints();    
      }
    }
     
     

    
}
