package eksamen2016_3;

public class KortsvarSporsmaal extends Sporsmaal {

    private String fasit;

    public KortsvarSporsmaal(String fasit, String spTekst) {
        super(spTekst);
        this.fasit = fasit;
    }

    @Override
    public String spørsmålstype() {
        return "Kortsvar";
    }

    @Override
    public int poeng(String svar) {
        if (svar.equals(fasit)) {
            return 3;
        } else if (svar.isEmpty()) {
            return 0;
        } else {
            return -1;
        }
    }
    
    
}
