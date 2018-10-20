package eksamen2015_2;

import java.util.ArrayList;

public class StykkGodsVogn extends Vogn {

    private ArrayList<Gods> gods;

    public StykkGodsVogn(ArrayList<Gods> gods, int tomVekt) {
        super(tomVekt);
        this.gods = gods;
    }

    @Override
    public int godsVekt() {
        int vekt = 0;
        for (Gods g : gods) {
            vekt += g.vekt();
        }
        return vekt;
    }

    public ArrayList<Gods> getGods() {
        return gods;
    }

}
