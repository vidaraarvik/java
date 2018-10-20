package eksamen2015;

public class PassasjerVogn extends Vogn {

    private int sitteplasser;

    public PassasjerVogn(int nettoVekt, int sitteplasser) {
        super(nettoVekt);
        this.sitteplasser = sitteplasser;
    }

    @Override
    public int godsVekt() {
        return sitteplasser * 80;
    }

    @Override
    public int vekt() {
        return nettoVekt() + godsVekt();
    }
}
