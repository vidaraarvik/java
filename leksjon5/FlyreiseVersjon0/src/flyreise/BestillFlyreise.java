package flyreise;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Fra denne klassen opprettes kontrollobjektet, med ansvar for
 * handlingsforløpet i bestilling av en flybillett.
 *
 */
public class BestillFlyreise {

    private ArrayList<Flyselskap> flyselskaper;
    private TreeSet<Bestilling> bestillinger;

    public BestillFlyreise() {
        lagDummyData();
        dumpData();
    }

    public void bestill(String fraBy, String tilBy, Dato dato, String seteklasse) {

        // FORENKLING: I denne versjonen er det kun mulig å reservere
        // seter på flyrutene til det første flyselskapet i listen!
        // Løsning på dette er ikke en del av den obligatoriske oppgaven.
        // I praksis vil man vel ønske å hente ut aktuelle flyruter fra
        // alle flyselskapene, og presentere dette for brukeren?
        Flyselskap fs = flyselskaper.get(0);

        // Sammenlign denne koden med sekvensdiagrammet (løsningsforslaget)!
        ArrayList<Flyrute> flyruter = fs.finnFlyruter(fraBy, tilBy);
        Flyrute flyrute = Flyreise.velgFlyrute(flyruter);
        Sete sete = flyrute.finnLedigSete(seteklasse, dato);
        int pris = sete.beregnPris(dato);
        String navn = Flyreise.hentNavn();
        Dato fdato = Flyreise.hentFødselsdato();
        Passasjer passasjer = fs.finnPassasjer(navn, fdato);

        if (passasjer == null) {
            String adr = Flyreise.hentAdresse();
            String tlf = Flyreise.hentTlf();
            String kortnr = Flyreise.hentKortnr();
            passasjer = new Passasjer(navn, fdato, adr, tlf, kortnr);
            Bestilling bestilling = new Bestilling(passasjer, sete, dato);
            sete.reserver();
            bestillinger.add(bestilling);
            System.out.println("Bestillingen er registrert!");
        } else {
            Bestilling bestilling = new Bestilling(passasjer, sete, dato);
            sete.reserver();
            bestillinger.add(bestilling);
            System.out.println("Bestillingen er registrert!");
        }
        dumpData();
    }

    // Slår sammen passasjerlistene til alle flyselskapene til
    // en stor liste.
    private TreeSet<Passasjer> allePassasjerer() {
        TreeSet<Passasjer> allePassasjerer = new TreeSet<>();
        for (Flyselskap fs : flyselskaper) {
            allePassasjerer.addAll(fs.getPassasjerer());
        }
        return allePassasjerer;
    }

    // Bygger opp datastrukturen med flyselskaper, flyruter, bestillinger
    // og passasjerer. Dette vil typisk bli lest inn fra fil eller database.
    private void lagDummyData() {

        Dato d1 = new Dato("05.11.2013");

        Sete sete1 = new Sete(1, Sete.ØKONOMI);
        Sete sete2 = new Sete(2, Sete.ØKONOMI);
        Sete sete3 = new Sete(3, Sete.BUSINESS);

        ArrayList<Sete> seteliste1 = new ArrayList<>();
        seteliste1.add(sete1);
        seteliste1.add(sete2);
        seteliste1.add(sete3);

        Flyrute fr1 = new Flyrute("Oslo", "London", d1, seteliste1);

        Dato d2 = new Dato("12.11.2013");

        Sete sete4 = new Sete(1, Sete.BUSINESS);
        Sete sete5 = new Sete(2, Sete.ØKONOMI);
        Sete sete6 = new Sete(3, Sete.BUSINESS);

        ArrayList<Sete> seteliste2 = new ArrayList<>();
        seteliste2.add(sete4);
        seteliste2.add(sete5);
        seteliste2.add(sete6);

        Flyrute fr2 = new Flyrute("Oslo", "London", d2, seteliste2);

        Passasjer p1 = new Passasjer("Ola Nordmann", new Dato("22.07.1982"), "Mogata 2",
                "87654321", "3333444455556666");
        Passasjer p2 = new Passasjer("Kari Mo", new Dato("02.10.1988"), "Liveien 2",
                "11223344", "1111222233334444");

        Bestilling b1 = new Bestilling(p1, sete1, d1);
        sete1.reserver();
        Bestilling b2 = new Bestilling(p2, sete4, d2);
        sete4.reserver();

        bestillinger = new TreeSet<>();
        bestillinger.add(b1);
        bestillinger.add(b2);

        Flyselskap fs1 = new Flyselskap("SAS");
        fs1.nyFlyrute(fr1);
        fs1.opprettPassasjer(p1);

        Flyselskap fs2 = new Flyselskap("Norwegian");
        fs2.nyFlyrute(fr2);
        fs2.opprettPassasjer(p2);

        flyselskaper = new ArrayList<>();
        flyselskaper.add(fs1);
        flyselskaper.add(fs2);
    }

    private void dumpData() {
        System.out.println("===================================");
        System.out.println("FLYREISE-DATA:");

        for (Flyselskap fs : flyselskaper) {
            System.out.println(fs.toString());
        }

        for (Bestilling b : bestillinger) {
            System.out.println(b.toString());
        }

        // Viser utskrift av samtlige passasjerer.
        TreeSet<Passasjer> passListe = allePassasjerer();
        if (passListe != null) {
            System.out.println("ALLE PASSASJERER:");
            System.out.println(passListe.toString());
        } else {
            System.out.println("INGEN PASSASJERER ER REGISTRERT! (???)");
        }

        System.out.println("===================================");
    }
}
