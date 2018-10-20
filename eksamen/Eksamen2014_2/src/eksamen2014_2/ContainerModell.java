package eksamen2014_2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContainerModell {

    private ArrayList<Container> ledige;
    private ArrayList<Oppdrag> oppdrag;
    private final String FILNAVN = "logg.txt";

    public ContainerModell() {
        int[] volum = {7, 7, 9, 12, 15};
        double[] timepriser = {500, 550, 600, 900, 950};
        
        ledige = new ArrayList();
        ledige.add(new Container(1, volum[0], timepriser[0]));
        ledige.add(new Container(2, volum[1], timepriser[1]));
        ledige.add(new Container(3, volum[2], timepriser[2]));
        ledige.add(new Container(4, volum[3], timepriser[3]));
        ledige.add(new Container(5, volum[4], timepriser[4]));
        oppdrag = new ArrayList();
    }

    public String regOppdrag(Oppdrag oppdrag) {
        int minst = Integer.MAX_VALUE;
        Container container = null;

        for (Container c : ledige) {
            if (oppdrag instanceof DagOppdrag) {
                if (c.getVolum() >= oppdrag.minsteVolum() && c.getVolum() < minst) {
                    container = c;
                    minst = container.getVolum();
                }
            } else {
                if (c.getVolum() < minst) {
                    container = c;
                    minst = container.getVolum();
                }
            }
        }

        if (container != null) {
            oppdrag.setContainer(container);
            this.oppdrag.add(oppdrag);

            return "Oppdrag registrert";
        } else {
            return "Fant ingen ledige containere store nok for gitt oppdrag";
        }

    }

    public String avsluttOppdrag(String adresse) throws IOException {
        Oppdrag o = finnOppdrag(adresse);

        if (o != null) {
            skrivOppdragsLogg(o);
            
            Container c = o.container;
            o.setContainer(null);
            ledige.add(c);
            oppdrag.remove(o);
            
            return o.toString() + " avsluttet";
        } else {
            return "Finner ingen oppdrag på adressen: " + adresse;
        }
    }

    private Oppdrag finnOppdrag(String adresse) {
        for (Oppdrag o : oppdrag) {
            if (o.adresse == adresse) {
                return o;
            }
        }
        return null;
    }

    private void skrivOppdragsLogg(Oppdrag o) {
        File fil;
        FileWriter fw;
        BufferedWriter bw;
        
        try {
            fil = new File(FILNAVN);
            fw = new FileWriter(fil);
            bw = new BufferedWriter(fw);

            bw.append(o.toString() + "\n");
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(ContainerModell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
