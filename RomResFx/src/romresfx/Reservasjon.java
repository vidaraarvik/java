package romresfx;

/* Klasse for å representere en reservasjon.
 *
 * En reservasjon inneholder romkode, dato, klokkeslett for start og slutt, samt
 * navn på kontaktperson.
 */
public class Reservasjon extends Object {

    private final String SKILLETEGN = ";";

    private String romkode;
    private Dato møtedato;
    private KlSlett start;
    private KlSlett slutt;
    private String kontakt;

    /* Konstruktør for å opprette en reservasjon fra dato, start, slutt og
     * kontaktperson.
     */
    public Reservasjon(String romkode, Dato møtedato, KlSlett start, KlSlett slutt, String kontakt)
            throws IllegalArgumentException {
        this.romkode = romkode;
        this.møtedato = møtedato;
        this.start = start;
        this.slutt = slutt;
        this.kontakt = kontakt;
        sjekk();
    }

    /* Konstruktør for å opprette en reservasjon fra en tekststreng.
     */
    public Reservasjon(String linje) {
        String tab[] = linje.split(SKILLETEGN);
        this.romkode = tab[0];
        this.møtedato = new Dato(tab[1]);
        this.start = new KlSlett(tab[2]);
        this.slutt = new KlSlett(tab[3]);
        this.kontakt = tab[4];
    }

    /* Avgjør om to reservasjoner kolliderer, som vil si at de er lagt til
     * samme dag, samme rom og overlapper i start/slutt-tid.
     */
    public boolean kolliderer(Reservasjon r) {
        boolean kolliderer = true;

        if ((!møtedato.equals(r.møtedato))
                || (!romkode.equals(r.romkode))
                || (slutt.før(r.start) || r.slutt.før(start))) {
            kolliderer = false;
        }

        return kolliderer;
    }

    /* Returnerer en tekstlig representasjon av reservasjonen.
     */
    @Override
    public String toString() {
        final String SKILLETEGN = ";";

        return (romkode + SKILLETEGN
                + møtedato.toString() + SKILLETEGN
                + start.toString() + SKILLETEGN
                + slutt.toString() + SKILLETEGN
                + kontakt);
    }

    private void sjekk() {
        if (slutt.før(start)) {
            throw new IllegalArgumentException("Slutt-tid kan ikke være før start-tid.");
        }
    }
}
