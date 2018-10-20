package eksamen2015_2;

public class BulkVogn extends Vogn {

    private String vareType;
    private int kiloLast;

    public BulkVogn(String vareType, int kiloLast, int tomVekt) {
        super(tomVekt);
        this.vareType = vareType;
        this.kiloLast = kiloLast;
    }

    @Override
    public int godsVekt() {
        return kiloLast;
    }
    
}
