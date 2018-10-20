
package eksamen2014;


public class TimeOppdrag extends Oppdrag {
    
    private final int minsteVolum = 0;
    private double leietid;
    private int pris;

    public TimeOppdrag(String adresse, double leietid) {
        super(adresse);
        this.leietid = leietid;
    }

    @Override
    public int minsteVolum() {
        return minsteVolum;
    }

    @Override
    public double pris() {
        return leietid * container.getPris();
    }

    @Override
    public String toString() {
        return "TimeOppdrag{" + "adresse=" + adresse + ", container=" + container.getLøpeNr() + ", pris=" + pris() + '}';
    }

    @Override
    public String type() {
        return "time";
    }
    
}
