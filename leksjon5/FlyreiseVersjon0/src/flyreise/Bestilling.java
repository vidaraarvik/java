package flyreise;

/**
 * Modellklasse for å representere en bestilling.
 *
 */
public class Bestilling implements Comparable<Bestilling> {
    
    private static int neste = 1;
    
    private int bnr;
    private Passasjer passasjer;
    private Sete sete;
    private Dato dato;
    
    public Bestilling(Passasjer passasjer, Sete sete, Dato dato) {
        this.passasjer = passasjer;
        this.sete = sete;
        this.dato = dato;
        this.bnr = neste;
        neste++;
    }
    
    @Override
    public String toString() {
        return "Bestilling: " + passasjer.toString() + " " 
                + sete.toString() 
                + " dato: " + dato.toString();
    }

    @Override
    public int hashCode() {
        return bnr;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bestilling)) {
            return false;
        }
        Bestilling b = (Bestilling) obj;
        return this.bnr == b.bnr;
    }
    
    @Override
    public int compareTo(Bestilling b) {
        return this.bnr - b.bnr;
    }
}
