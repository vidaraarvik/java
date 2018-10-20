package eksamen2015_3;

public class BulkVogn extends Vogn {

    private String varetype;
    private int kilo;

    public BulkVogn(String varetype, int kilo, int nettovekt) {
        super(nettovekt);
        this.varetype = varetype;
        this.kilo = kilo;
    }

    @Override
    public int godsVekt() {
        return kilo;
    }

}
