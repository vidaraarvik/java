package eksamen2016;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Prove {

    private ArrayList<Sporsmaal> spmListe = new ArrayList<>();
    private final String FILNAVN = "logg.txt";
    private int teller = 0;

    public Sporsmaal neste() {
        if (teller < spmListe.size()) {
            return spmListe.get(teller++);
        } else {
            return null;
        }
    }

    public void visKortversjon() {
        for (Sporsmaal s : spmListe) {
            System.out.println(s.kortVersjon());
        }

    }

    public void lesFraFil() {

        BufferedReader leser;
        try {
            leser = new BufferedReader(new FileReader(FILNAVN));

            String linje = leser.readLine();
            while (linje != null) {

                if (linje.equals("FLERVALG")) {
                    String spm = leser.readLine();
                    String[] alternativer = new String[4];
                    alternativer[0] = leser.readLine();
                    alternativer[1] = leser.readLine();
                    alternativer[2] = leser.readLine();
                    alternativer[3] = leser.readLine();
                    String fasit = leser.readLine();

                    Sporsmaal fspm = new FlervalgSporsmaal(spm, alternativer, fasit.charAt(0));
                    spmListe.add(fspm);
                }

                if (linje.equals("KORTSVAR")) {
                    String spm = leser.readLine();
                    String fasit = leser.readLine();

                    KortsvarSporsmaal kspm = new KortsvarSporsmaal(spm, fasit);
                    spmListe.add(kspm);
                }
                
                linje = leser.readLine();
            }
            leser.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Finner ikke '" + FILNAVN + "'!");
        } catch (IOException ex) {
            System.out.println("Kan ikke lese '" + FILNAVN + "'!");
        }

    }
}
