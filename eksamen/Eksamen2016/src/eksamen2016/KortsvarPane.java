package eksamen2016;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class KortsvarPane extends Pane {

    private TextField svar = new TextField();

    public KortsvarPane() {
        this.getChildren().add(svar);
    }

    public void removeSvar() {
        svar.setText("");
    }
    
    public String getSvar() {
        return svar.getText();
    }

}
