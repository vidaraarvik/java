package flyreise;

import java.util.ArrayList;

/**
 * Hovedklasse som simulerer brukergrensesnittet.
 *
 */
public class Flyreise {

    private static BestillFlyreise bf;

    public static void main(String[] args) {
        bf = new BestillFlyreise();
        startBestilling();
    }

    // Her kan vi tenke oss at bruker har skrevet inn opplysninger
    // om ønsket flyreise, og klikker på en knapp "Bestill flyreise".
    public static void startBestilling() {
        String fraBy = "Oslo";
        String tilBy = "London";
        Dato dato = new Dato("05.11.2013");
        String seteklasse = "økonomi";
        bf.bestill(fraBy, tilBy, dato, seteklasse);
    }

    // Systemet har funnet aktuelle flyruter og ber bruker om å velge.
    // I denne testversjonen velger brukeren alltid den første i listen.
    public static Flyrute velgFlyrute(ArrayList<Flyrute> flyruter) {
        return flyruter.get(0);
    }

    // Bruker har valgt å bestille flyreise, og systemet spør bruker
    // om navn. I denne testversjonen er navnet hardkodet.
    public static String hentNavn() {
        return "Ola Nordmann";
    }

    // Bruker har valgt å bestille flyreise, og systemet spør bruker
    // om fødselsdato. I denne testversjonen er fødselsdato hardkodet.
    public static Dato hentFødselsdato() {
        return new Dato("22.07.1982");
    }
    
    public static String hentAdresse() {
        return "Mogata 2";
    }
    
    public static String hentTlf() {
        return "87654321";
    }
    
    public static String hentKortnr() {
        return "3333444455556666";
    }
}
