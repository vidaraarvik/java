package eksamen2015;

public class BulkVogn extends Vogn {

    private String vareType;
    private int vekt;    // i kilo

    public BulkVogn(int nettoVekt, String vareType, int vekt) {
        super(nettoVekt);
        this.vareType = vareType;
        this.vekt = vekt;
    }

    @Override
    public int godsVekt() {
        return vekt;
    }

    @Override
    public int vekt() {
        return nettoVekt() + godsVekt();
    }
    
}
