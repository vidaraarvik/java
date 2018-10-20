
package eksamen2014;


public class DagOppdrag extends Oppdrag {
    
    private int minsteVolum;
    private final int kubikkmeterPris = 500; 

    public DagOppdrag(String adresse, int minsteVolum) {
        super(adresse);
    }

    @Override
    public int minsteVolum() {
        return minsteVolum;
    }

    @Override
    public double pris() {
        return kubikkmeterPris * minsteVolum;
    }

    @Override
    public String toString() {
        return "DagOppdrag{" + "adresse=" + adresse + ", container=" + container.getLøpeNr() + ", pris=" + pris() + '}';
    }

    @Override
    public String type() {
        return "dag";
    }
    
}
