package yatzy;

import java.util.Random;
import javafx.scene.paint.Color;

/**
 *
 * @author speedy
 */
public class Terning {

    private int verdi;
    private Color farge;

    public Terning(Color farge) {
        this.kast();
        this.farge = farge;
    }

    public int getVerdi() {
        return verdi;
    }

    public void setVerdi(int verdi) {
        this.verdi = verdi;
    }

    public Color getFarge() {
        return farge;
    }

    public void setFarge(Color farge) {
        this.farge = farge;
    }
    
    

    private void kast() {
        Random r = new Random();
        verdi = r.nextInt((6 - 1) + 1) + 1;
    }

}
