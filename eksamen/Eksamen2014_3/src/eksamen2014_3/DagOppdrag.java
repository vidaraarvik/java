package eksamen2014_3;

public class DagOppdrag extends Oppdrag {

    private int minVolum;
    private final int PRIS = 500;

    public DagOppdrag(int minVolum, String adresse) {
        super(adresse);
        this.minVolum = minVolum;
    }

    @Override
    public int minsteVolum() {
        return minVolum;
    }

    @Override
    public double pris() {
        return container.getVolum() * PRIS;
    }

    @Override
    public String toString() {
        return "Dag-oppdrag: adresse =" + adresse + ", container=" + container.getLøpeNr() + ", pris=" + pris();
    }

}
