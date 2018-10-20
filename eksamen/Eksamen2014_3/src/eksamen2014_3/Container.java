package eksamen2014_3;

public class Container {
    
    private int løpeNr;
    private int volum;
    private double pris;

    public Container(int løpeNr, int volum, double pris) {
        if (volum < 0 || pris < 0) {
            throw new IllegalArgumentException("Negativ volum/pris");
        }
        this.løpeNr = løpeNr;
        this.volum = volum;
        this.pris = pris;
    }

    public int getLøpeNr() {
        return løpeNr;
    }

    public int getVolum() {
        return volum;
    }

    public double getPris() {
        return pris;
    }
    
    
    
}
