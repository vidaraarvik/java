package eksamen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logg {

    private static Logg logg;
    private final String FILNAVN = "logg.txt";
    private int nivå;
    private File fil;
    private FileWriter fw;
    private BufferedWriter bw;

    public static Logg getLogg() {
        if (logg == null) {
            logg = new Logg();
        }
        return logg;
    }

    public void setNivå(int nivå) {
        this.nivå = nivå;
    }

    public void skriv(String melding, int nivå) {
        if (nivå >= this.nivå) {
            try {
                fil = new File(FILNAVN);
                fw = new FileWriter(fil, true);
                bw = new BufferedWriter(fw);

                bw.write(melding);
                bw.newLine();

            } catch (IOException ex) {
                System.out.println("Kunne ikke skrive til loggfil!");
            }
        }
    }

    public void lukk() {
        if (bw != null) {
            try {
                bw.close();
            } catch (IOException ex) {
                System.out.println("Kunne ikke lukke loggfilen!");
            }
        }
    }

}
