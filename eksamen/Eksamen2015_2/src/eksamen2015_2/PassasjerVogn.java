package eksamen2015_2;

public class PassasjerVogn extends Vogn {

    private int sitteplasser;
    private int passasjerVekt;

    public PassasjerVogn(int sitteplasser, int tomVekt) {
        super(tomVekt);
        this.sitteplasser = sitteplasser;
    }

    @Override
    public int godsVekt() {
        return sitteplasser * passasjerVekt;
    }

    public int getSitteplasser() {
        return sitteplasser;
    }

}
