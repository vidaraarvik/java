package eksamen2014_2;

public class TimeOppdrag extends Oppdrag {

    private int antallTimer;

    public TimeOppdrag(int antallTimer, String adresse) {
        super(adresse);
        this.antallTimer = antallTimer;
    }

    @Override
    public int minsteVolum() {
        return 0;
    }

    @Override
    public double pris() {
        return antallTimer * container.getPrisPrTime();
    }

    @Override
    public String toString() {
        return "Time-oppdrag: adresse=" + adresse + ", container=" + container.getLøpeNr();
    }



}
