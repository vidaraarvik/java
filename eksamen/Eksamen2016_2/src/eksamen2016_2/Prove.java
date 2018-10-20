package eksamen2016_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Prove {

    private ArrayList<Sporsmaal> spmTab;
    private int spmTeller;
    private String FILNAVN = "spm.txt";

    public Prove() {
        this.spmTab = new ArrayList();
        this.spmTeller = 0;
    }

    public Sporsmaal neste() {
        if (spmTeller < spmTab.size()) {
            return spmTab.get(spmTeller++);
        } else {
            return null;
        }
    }

    public void visKortversjon() {
        for (Sporsmaal spm : spmTab) {
            System.out.println(spm.kortVersjon());
        }
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
                    String[] alternativer = new String[4];
                    alternativer[0] = br.readLine();
                    alternativer[1] = br.readLine();
                    alternativer[2] = br.readLine();
                    alternativer[3] = br.readLine();
                    char riktig = br.readLine().charAt(0);

                    Sporsmaal spm = new FlervalgSporsmaal(alternativer, riktig, spmTekst);
                    spmTab.add(spm);
                }

                if (linje.equals("KORTSVAR")) {
                    String spmTekst = br.readLine();
                    String fasit = br.readLine();

                    Sporsmaal spm = new KortsvarSporsmaal(fasit, spmTekst);
                    spmTab.add(spm);
                }

                linje = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
