package eksamen2015;

import java.util.ArrayList;

public class StykkGodsVogn extends Vogn {
    
    private ArrayList<Gods> gods;

    public StykkGodsVogn(int nettoVekt, ArrayList<Gods> gods) {
        super(nettoVekt);
        this.gods = gods;
    }

    public ArrayList<Gods> getGods() {
        return gods;
    }

    @Override
    public int godsVekt() {
        int totalvekt = 0;
        for (Gods g : gods) {
            totalvekt += g.getVekt();
        }
        return totalvekt;
    }

    @Override
    public int vekt() {
        return nettoVekt() + godsVekt();
    }

}
