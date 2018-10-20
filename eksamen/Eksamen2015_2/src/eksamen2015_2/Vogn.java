package eksamen2015_2;

public abstract class Vogn implements HarVekt {

    private int nettoVekt;

    public Vogn(int nettoVekt) {
        this.nettoVekt = nettoVekt;
    }

    public abstract int godsVekt();

    public int nettoVekt() {
        return nettoVekt;
    }

    @Override
    public int vekt() {
        return godsVekt() + nettoVekt();
    }

}
