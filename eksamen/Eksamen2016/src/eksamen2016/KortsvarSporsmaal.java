package eksamen2016;

public class KortsvarSporsmaal extends Sporsmaal {
    
    String fasit;

    public KortsvarSporsmaal(String spTekst, String fasit) {
        super(spTekst);
        this.fasit = fasit;
    }

    @Override
    public String kortVersjon() {
        return spørsmålstype() + ": " + getSpTekst();
    }

    @Override
    public String spørsmålstype() {
        return "Kortsvar";
    }

    @Override
    public int poeng(String svar) {
        if (svar.equals(fasit)) {
            return 3;
        } else if (svar.equals("")) {
            return 0;
        } else {
            return -1;
        }
    }
    
}
