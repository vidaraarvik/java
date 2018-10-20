package flyreise;

/**
 * Modellklasse for å representere et flysete.
 *
 */
public class Sete {

    public static final int ØKONOMI = 1;
    public static final int BUSINESS = 2;

    private static final int STANDARD_PRIS = 100;

    private int nr;
    private int seteklasse;
    private boolean reservert;

    public Sete(int nr, int seteklasse) {
        this.nr = nr;
        this.seteklasse = seteklasse;
        reservert = false;
    }

    public void reserver() {
        reservert = true;
    }

    public boolean erReservert() {
        return reservert;
    }

    public int beregnPris(Dato avreisedato) {
        // TODO Veldig spesiell løsning: Alle seter
        // i samme seteklasse koster det samme. Bør
        // utvides slik at hvert Sete-objekt kan ha
        // sin standardpris.

        if (seteklasse == ØKONOMI) {
            return STANDARD_PRIS;
        } else {
            return (int) (STANDARD_PRIS * 1.5);
        }
    }

    public String getSeteklasse() {
        if (this.seteklasse == 1) {
            return "økonomi";
        } else {
            return "business";
        }
    }

    @Override
    public String toString() {
        return "[Setenr " + nr + " "
                + seteklasseTekst(seteklasse)
                + " reservert: " + reservert + "]";
    }

    private String seteklasseTekst(int seteklasse) {
        if (seteklasse == ØKONOMI) {
            return "økonomi";
        } else if (seteklasse == BUSINESS) {
            return "business";
        } else {
            return "UKJENT SETEKLASSE";
        }
    }
}
