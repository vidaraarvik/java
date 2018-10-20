package eksamen2016_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Prove {

    private ArrayList<Sporsmaal> spmListe;
    private int teller;
    private final String FILNAVN = "spm.txt";

    public Prove() {
        spmListe = new ArrayList();
        teller = 0;
    }

    public void lesFraFil() {
        File fil;
        FileReader fr;
        BufferedReader br;

        try {
            fil = new File(FILNAVN);
            fr = new FileReader(fil);
            br = new BufferedReader(fr);

            String linje = br.readLine();
            while (linje != null) {
                if (linje.equals("FLERVALG")) {
                    String spmTekst = br.readLine();
                    String[] alt = new String[4];
                    alt[0] = br.readLine();
                    alt[1] = br.readLine();
                    alt[2] = br.readLine();
                    alt[3] = br.readLine();
                    char riktig = br.readLine().charAt(0);

                    Sporsmaal spm = new FlervalgSporsmaal(alt, riktig, spmTekst);
                    spmListe.add(spm);
                }

                if (linje.equals("KORTSVAR")) {
                    String spmTekst = br.readLine();
                    String fasit = br.readLine();

                    Sporsmaal spm = new KortsvarSporsmaal(fasit, spmTekst);
                    spmListe.add(spm);
                }
                linje = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Finner ikke " + FILNAVN + " " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Feil ved lesing av fil " + FILNAVN + " " + ex.getMessage());
        }
    }

    public void visKortversjon() {
        for (Sporsmaal s : spmListe) {
            System.out.println(s.kortVersjon());
        }
    }

    public Sporsmaal neste() {
        if (teller < spmListe.size()) {
            return spmListe.get(teller++);
        } else {
            return null;
        }
    }

}
