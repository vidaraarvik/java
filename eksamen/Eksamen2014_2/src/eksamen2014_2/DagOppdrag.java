package eksamen2014_2;

public class DagOppdrag extends Oppdrag {

    private int minimumVolum;
    private final int PRIS = 500;

    public DagOppdrag(int minimumVolum, String adresse) {
        super(adresse);
        this.minimumVolum = minimumVolum;
    }

    @Override
    public int minsteVolum() {
        return minimumVolum;
    }

    @Override
    public double pris() {
        return container.getVolum() * PRIS;
    }

    @Override
    public String toString() {
        return "Dag-oppdrag: adresse=" + adresse + ", container=" + container.getLøpeNr();
    }

}
