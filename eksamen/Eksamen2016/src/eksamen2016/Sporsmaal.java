package eksamen2016;

public abstract class Sporsmaal {

    private String spTekst;

    public Sporsmaal(String spTekst) {
        this.spTekst = spTekst;
    }

    public abstract String kortVersjon();

    public abstract String spørsmålstype();
    
    public abstract int poeng(String svar);

    public String getSpTekst() {
        return spTekst;
    }

}
