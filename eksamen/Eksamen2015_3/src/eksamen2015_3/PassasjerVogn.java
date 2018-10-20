package eksamen2015_3;

public class PassasjerVogn extends Vogn {
    
    private int sitteplasser;
    private final int PASSASJERVEKT = 80;

    public PassasjerVogn(int sitteplasser, int nettovekt) {
        super(nettovekt);
        this.sitteplasser = sitteplasser;
    }

    @Override
    public int godsVekt() {
        return sitteplasser * PASSASJERVEKT;
    }

    public int getSitteplasser() {
        return sitteplasser;
    }
    
}
