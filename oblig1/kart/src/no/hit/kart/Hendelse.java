package no.hit.kart;

/* Klasse for å representere Hendelser.
 *
 * Objekter kan lages på en måte:
 *
 *   Punkt p = new Punkt(String);   // OBS, 4 linjer separert med komma, 
 *                                  // eksempelvis '21.03.1998;202;150;Vant et hundeløp'.
 *
 * Klassen inneholder også metode for å
 * konvertere en hendelse til tekstlig form.
 *
 */
public class Hendelse {

    private static int teller = 1; // Felles for alle Hendelse-objekter
    private static final String SKILLETEGN = ";";

    private int hNr;
    private Dato dato;
    private Punkt punkt;
    private String beskrivelse;

    // Konstruktør
    public Hendelse(String linje) {
        this.hNr = teller++;
        String tab[] = linje.split(SKILLETEGN);
        this.dato = new Dato(tab[0]);
        this.punkt = new Punkt(Integer.parseInt(tab[1]), Integer.parseInt(tab[2]));
        this.beskrivelse = tab[3];
    }

    public static int getTeller() {
        return teller;
    }

    public int gethNr() {
        return hNr;
    }

    public String getDato() {
        return dato.toString();
    }

    public void setDato(String dato) {
        this.dato = new Dato(dato);
    }

    public int getPunktX() {
        return punkt.getX();
    }

    public void setPunktX(int x) {
        int y = punkt.getY();
        punkt = new Punkt(x, y);
    }

    public int getPunktY() {
        return punkt.getY();
    }

    public void setPunktY(int y) {
        int x = punkt.getX();
        punkt = new Punkt(x, y);
    }

    public Punkt getPunkt() {
        return punkt;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    // Returnerer en tekstlig representasjon av hendelser
    @Override
    public String toString() {
        return (hNr + SKILLETEGN
                + dato.toString() + SKILLETEGN
                + punkt.toString() + SKILLETEGN
                + beskrivelse);
    }
}
