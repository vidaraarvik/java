package eksamen2014_2;

public class Container {

    private int løpeNr;
    private int volum;
    private double prisPrTime;

    public Container(int løpeNr, int volum, double prisPrTime) {
        if (volum < 0 || prisPrTime < 0) {
            throw new IllegalArgumentException("Kun positive tall!");
        }
        this.løpeNr = løpeNr;
        this.volum = volum;
        this.prisPrTime = prisPrTime;
    }

    public int getLøpeNr() {
        return løpeNr;
    }

    public int getVolum() {
        return volum;
    }

    public double getPrisPrTime() {
        return prisPrTime;
    }

}
