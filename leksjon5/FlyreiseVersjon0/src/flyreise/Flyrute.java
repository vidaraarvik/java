package flyreise;

import java.util.ArrayList;

/**
 * Modellklasse for å representere en flyrute.
 *
 * Denne klassen inneholder både generelle opplysninger om en "rute", men også
 * en dato og konkrete flyseter (som kan være reservert og ikke) på en "flight"
 * - en gjennomføring av flyruten. Det ville dermed være aktuelt å splitte denne
 * klassen i Rute og Flight (men her er det valgt å akseptere noe redundans).
 *
 */
public class Flyrute {

    private String fraBy;
    private String tilBy;
    private Dato dato;
    private ArrayList<Sete> seter;

    public Flyrute(String fraBy, String tilBy, Dato dato, ArrayList<Sete> seter) {
        this.fraBy = fraBy;
        this.tilBy = tilBy;
        this.dato = dato;
        this.seter = seter;
    }

    public Sete finnLedigSete(String seteklasse, Dato dato) {
        if (this.dato.equals(dato)) {
            for (Sete s : seter) {
                if (!s.erReservert() && s.getSeteklasse().equals(seteklasse)) {
                    return s;
                }
            }
        }
        return null;
    }

    public String getAvreise() {
        return fraBy;
    }

    public String getAnkomst() {
        return tilBy;
    }

    @Override
    public String toString() {
        String ut = "Flyrute: Fra " + fraBy + " Til " + tilBy + " - ";
        ut += "Dato: " + dato;

        ut += "\nSeter:\n";
        for (Sete s : seter) {
            ut += s.toString() + "\n";
        }
        return ut;
    }
}
