package eksamen2016_2;

public class FlervalgSporsmaal extends Sporsmaal {

    private String[] alternativer;
    private char riktig;

    public FlervalgSporsmaal(String[] alternativer, char riktig, String spTekst) {
        super(spTekst);
        this.alternativer = alternativer;
        this.riktig = riktig;
    }

    public String[] getAlternativer() {
        return alternativer;
    }

    public char getRiktig() {
        return riktig;
    }

    @Override
    public String spørsmålstype() {
        return "Flervalg";
    }

    @Override
    public int poeng(String svar) {
        int poeng = 0;

        for (char c : svar.toCharArray()) {
            if (c == riktig) {
                poeng += 3;
            } else {
                poeng--;
            }
        }
        return poeng;
    }

}
