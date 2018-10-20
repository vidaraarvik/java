
package mvc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;


public class View extends FlowPane {
    
    private Button plussEn = new Button("+1");
    private Label status = new Label("Verdi");
    
    public View() {
        this.getChildren().add(plussEn);
        this.getChildren().add(status);
    }
    
    public void setListener(EventHandler<ActionEvent> lytter) {
        plussEn.setOnAction(lytter);
    }
    
    public void setStatus(int verdi) {
        status.setText("Verdi: " + verdi);
    }
    
}
