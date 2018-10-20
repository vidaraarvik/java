package eksamen2016_3;

public class FlervalgSporsmaal extends Sporsmaal {

    private String[] alternativer;
    private char riktig;

    public FlervalgSporsmaal(String[] alternativer, char riktig, String spTekst) {
        super(spTekst);
        this.alternativer = alternativer;
        this.riktig = riktig;
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
            } else if (c != riktig) {
                poeng -= 1;
            }
        }
        return poeng;
    }

    public String getA() {
        return alternativer[0];
    }

    public String getB() {
        return alternativer[1];
    }

    public String getC() {
        return alternativer[2];
    }

    public String getD() {
        return alternativer[3];
    }

}
