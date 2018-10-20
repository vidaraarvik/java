package eksamen2016_3;

public abstract class Sporsmaal {

    protected String spTekst;

    public Sporsmaal(String spTekst) {
        this.spTekst = spTekst;
    }

    public String kortVersjon() {
        return spørsmålstype() + " " + spTekst;
    }

    public abstract String spørsmålstype();
    
    public abstract int poeng(String svar);

    
}
