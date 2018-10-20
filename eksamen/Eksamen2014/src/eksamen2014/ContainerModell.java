package eksamen2014;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ContainerModell {

    private ArrayList<Container> ledigeListe = new ArrayList<>();    // ledige containere
    private ArrayList<Oppdrag> oppdragsListe = new ArrayList<>();     // aktive oppdrag

    private final String FILNAVN = "logg.txt";

    public ContainerModell() {
        int[] volum = {7, 7, 9, 12, 15};
        double[] timepriser = {500, 550, 600, 900, 950};

        for (int i = 0; i < volum.length && i < timepriser.length; i++) {
            try {
                ledigeListe.add(new Container(i, volum[i], timepriser[i]));
            } catch (Container.VolumPrisException ex) {
                System.out.println("Feil: Negativ pris eller volum!");
            }
        }
    }

    public String regOppdrag(Oppdrag oppdrag) {

        int minst = Integer.MAX_VALUE;
        int minsteVolum = oppdrag.minsteVolum();
        Container container = null;

        for (Container c : ledigeListe) {
            if (oppdrag.type().equals("dag")) {
                if (c.getVolum() >= minsteVolum && c.getVolum() < minst) {
                    minst = c.getVolum();
                    container = c;
                }
            } else if (oppdrag.type().equals("time")) {
                if (c.getVolum() < minst) {
                    minst = c.getVolum();
                    container = c;
                }
            }
        }
        if (container != null) {
            oppdrag.setContainer(container);
            ledigeListe.remove(container);
        }

        oppdragsListe.add(oppdrag);

        return "Oppdrag laget: " + oppdrag.toString();
    }

    public String avsluttOppdrag(String adresse) throws IOException {
        // Finn aktivt oppdrag på gitt adresse i listen oppdrag.
        // Det er aldri mer enn 1 oppdrag pr. adresse.

        for (Oppdrag o : oppdragsListe) {
            if (o.adresse.equals(adresse)) {
                // Legg containeren tilbake i listen med ledige containere.
                ledigeListe.add(o.container);
                // Frikoble containeren fra oppdraget.
                o.setContainer(null);
                // Fjern oppdraget fra listen oppdragsListe.
                oppdragsListe.remove(o);

                skrivLogg(o.toString());

                return "Oppdrag avsluttet " + o.toString();
            }
        }
        return "Ingen oppdrag berørt.";
    }

    public void skrivLogg(String txt) {

        try {
            try (BufferedWriter skriver = new BufferedWriter(new FileWriter(FILNAVN, true))) {
                skriver.write(txt);
                skriver.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Kunne ikke skrive til fil");
        }

    }

    public ArrayList<Container> getLedigeListe() {
        return ledigeListe;
    }

}
