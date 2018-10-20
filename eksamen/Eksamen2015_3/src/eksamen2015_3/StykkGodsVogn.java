package eksamen2015_3;

import java.util.ArrayList;

public class StykkGodsVogn extends Vogn {
    
    private ArrayList<Gods> godsListe;

    public StykkGodsVogn(ArrayList<Gods> godsListe, int nettovekt) {
        super(nettovekt);
        this.godsListe = godsListe;
    }

    @Override
    public int godsVekt() {
        int vekt = 0;
        for (Gods g : godsListe) {
            vekt += g.vekt();
        }
        return vekt;
    }

    public ArrayList<Gods> getGodsListe() {
        return godsListe;
    }
    
}
