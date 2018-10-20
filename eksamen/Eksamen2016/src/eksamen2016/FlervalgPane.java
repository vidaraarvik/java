package eksamen2016;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FlervalgPane extends Pane {

    private GridPane gp = new GridPane();

    private CheckBox a = new CheckBox(), b = new CheckBox(), c = new CheckBox(), d = new CheckBox();

    private Label al = new Label(), bl = new Label(), cl = new Label(), dl = new Label();

    public FlervalgPane() {
        gp.add(a, 0, 0);
        gp.add(b, 0, 1);
        gp.add(c, 0, 2);
        gp.add(d, 0, 3);

        gp.add(al, 1, 0);
        gp.add(bl, 1, 1);
        gp.add(cl, 1, 2);
        gp.add(dl, 1, 3);

        this.getChildren().add(gp);

    }

    public void setAl(String txt) {
        al.setText(txt);
    }

    public void setBl(String txt) {
        bl.setText(txt);
    }

    public void setCl(String txt) {
        cl.setText(txt);
    }

    public void setDl(String txt) {
        dl.setText(txt);
    }

    public CheckBox getA() {
        return a;
    }

    public CheckBox getB() {
        return b;
    }

    public CheckBox getC() {
        return c;
    }

    public CheckBox getD() {
        return d;
    }
    
    

}
