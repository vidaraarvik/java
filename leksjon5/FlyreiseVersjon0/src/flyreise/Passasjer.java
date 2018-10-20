package flyreise;

import java.util.Objects;

/**
 * Modellklasse for å representere en flypassasjer.
 *
 * TODO Denne klassen bør "klargjøres" for å kunne legges inn i en sortert
 * Collection.
 *
 */
public class Passasjer implements Comparable<Passasjer> {

    private String navn;
    private Dato fdato;
    private String adr;
    private String tlf;
    private String kortnr;

    public Passasjer(String navn, Dato fdato) {
        this.navn = navn;
        this.fdato = fdato;
    }

    public Passasjer(String navn, Dato fdato, String adr, String tlf, String kortnr) {
        this(navn, fdato);
        this.adr = adr;
        this.tlf = tlf;
        this.kortnr = kortnr;
    }

    @Override
    public String toString() {
        return navn + " født: " + fdato.toString() + " "
                + " adresse: " + adr + " " + " tlf: " + tlf
                + " kredittkortnr: " + kortnr;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Passasjer other = (Passasjer) obj;
        if (Objects.equals(this.navn, other.navn) && Objects.equals(this.fdato, other.fdato)) {
            return true;
        }
        return true;
    }

    @Override
    public int compareTo(Passasjer p) {
        return this.navn.compareTo(p.navn);
    }


}
