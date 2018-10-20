package flyreise;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Modellklasse for å representere et flyselskap.
 *
 */
public class Flyselskap {

    private String navn;
    private ArrayList<Flyrute> flyruter;
    private HashSet<Passasjer> passasjerer;

    public Flyselskap(String navn) {
        this.navn = navn;
        this.flyruter = new ArrayList<>();
        this.passasjerer = new HashSet<>();
    }

    public void nyFlyrute(Flyrute flyrute) {
        flyruter.add(flyrute);
    }

    public ArrayList<Flyrute> finnFlyruter(String fraBy, String tilBy) {
        ArrayList<Flyrute> ruterFunnet = new ArrayList<Flyrute>();
        
        for (Flyrute flyrute : flyruter) {
            if (fraBy.equals(flyrute.getAvreise())
                    && tilBy.equals(flyrute.getAnkomst()))
                ruterFunnet.add(flyrute);
        }
        
        return ruterFunnet;
    }

    public Passasjer finnPassasjer(String navn, Dato fDato) {
        Passasjer søk = new Passasjer(navn, fDato);
        for (Passasjer p : passasjerer) {
            if (p.equals(søk))
                return p;
        }
        return null;
    }

    public void opprettPassasjer(Passasjer p) {
        passasjerer.add(p);
    }
    
    public HashSet<Passasjer> getPassasjerer() {
        return passasjerer;
    }

    @Override
    public String toString() {
        String ut = navn + "\n";
        for (Flyrute f : flyruter) {
            ut += f + "\n";
        }
        ut += "Passasjerer:\n";
        for (Passasjer p : passasjerer) {
            ut += p + "\n ";
        }
        return ut;
    }
}
