package eksamen2016;

public class FlervalgSporsmaal extends Sporsmaal {

    private String[] alternativer;
    private char riktig;

    public FlervalgSporsmaal(String spTekst, String[] alternativer, char riktig) {
        super(spTekst);
        this.alternativer = alternativer;
        this.riktig = riktig;
    }

    @Override
    public String kortVersjon() {
        return spørsmålstype() + ": " + getSpTekst();
    }

    @Override
    public String spørsmålstype() {
        return "Flervalg";
    }

    @Override
    public int poeng(String svar) {
        int sum = 0;
        for (char c : svar.toCharArray()) {
            if (c == riktig) {
                sum += 3;
            } else if (c != riktig) {
                sum += -1;
            }
        }
        return sum;
    }

    public String[] getAlternativer() {
        return alternativer;
    }

}
