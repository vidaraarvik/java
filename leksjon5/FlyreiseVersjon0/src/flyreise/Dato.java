package flyreise;

/**
 * Modellklasse for å representere datoer.
 *
 */
public class Dato extends Object {

    private int dag;
    private int mnd;
    private int år;

    public Dato(int dag, int mnd, int år) {
        this.dag = dag;
        this.mnd = mnd;
        this.år = år;
        sjekk();
    }

    public Dato(String strDato) {
        try {
            String[] dagTab = strDato.split("\\.");
            dag = Integer.parseInt(dagTab[0]);
            mnd = Integer.parseInt(dagTab[1]);
            år = Integer.parseInt(dagTab[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Dato " + strDato + " er ikke på lovlig form dd.mm.åååå");
        }
        sjekk();
    }

    public boolean før(Dato d) {
        boolean før = false;

        if (år < d.år) {
            før = true;
        } else if (år == d.år && mnd < d.mnd) {
            før = true;
        } else if (år == d.år && mnd == d.mnd && dag < d.dag) {
            før = true;
        }

        return før;
    }

    @Override
    public String toString() {
        String ut = "";
        if (dag < 10) {
            ut += "0";
        }
        ut += dag + ".";

        if (mnd < 10) {
            ut += "0";
        }
        ut += mnd + ".";

        ut += år;
        return ut;
    }

    private void sjekk() throws IllegalArgumentException {
        boolean ok = true;

        if (mnd < 1 || mnd > 12 || dag < 1 || dag > 31) {
            ok = false;
        } else if ((mnd == 4 || mnd == 6 || mnd == 9 || mnd == 11) && dag > 30) {
            ok = false;
        } else if (mnd == 2 && dag > 29) {
            ok = false;
        } else if (mnd == 2 && dag == 29 && !iSkuddår()) {
            ok = false;
        }

        if (!ok) {
            throw new IllegalArgumentException("Ulovlig dato: " + toString());
        }
    }

    private boolean iSkuddår() {
        return ((år % 4 == 0 && år % 100 != 0) || (år % 400 == 0));
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dato)) {
            return false;
        }
        Dato d = (Dato) obj;
        return år == d.år && mnd == d.mnd && dag == d.dag;
    }

}
