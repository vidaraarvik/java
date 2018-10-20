package eksamen2015_3;

public abstract class Vogn implements HarVekt {

    private int nettovekt;

    public Vogn(int nettovekt) {
        this.nettovekt = nettovekt;
    }

    public abstract int godsVekt();

    @Override
    public int vekt() {
        return godsVekt() + nettovekt;
    }

}
