
package mvc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Controller implements EventHandler<ActionEvent> {
    
    private Model m;
    private View v;

    public Controller(Model m, View v) {
        this.m = m;
        this.v = v;
        v.setListener(this);
    }

    @Override
    public void handle(ActionEvent event) {
        m.plussEn();
        v.setStatus(m.getVerdi());
    }
    
    
    
}
