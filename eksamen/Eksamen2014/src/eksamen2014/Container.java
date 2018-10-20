package eksamen2014;

public class Container {

    private int løpeNr;
    private int volum;
    private double pris;

    public Container(int løpeNr, int volum, double pris) throws VolumPrisException {
        if (volum < 0 || pris < 0) {
            throw new VolumPrisException();
        } else {
            this.løpeNr = løpeNr;
            this.volum = volum;
            this.pris = pris;
        }
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

    public static class VolumPrisException extends Exception {

        public VolumPrisException() {
            System.out.println("Ulovlig volum eller pris!");
        }
    }

}
