package eksamen2014_3;

public class TimeOppdrag extends Oppdrag {

    private int leieTid;

    public TimeOppdrag(int leieTid, String adresse) {
        super(adresse);
        this.leieTid = leieTid;
    }

    @Override
    public int minsteVolum() {
        return 0;
    }

    @Override
    public double pris() {
        return container.getPris() * leieTid;
    }

    @Override
    public String toString() {
        return "Time-oppdrag: adresse =" + adresse + ", container=" + container.getLøpeNr() + ", pris=" + pris();
    }

}
