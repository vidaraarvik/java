package eksamen2014_3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ContainerModell {

    private ArrayList<Container> ledige; // ledige containere
    private ArrayList<Oppdrag> oppdrag; // aktive oppdrag
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

    public ArrayList<Container> getLedige() {
        return ledige;
    }

    public ArrayList<Oppdrag> getOppdrag() {
        return oppdrag;
    }

    public String regOppdrag(Oppdrag o) {
        int str = 0;
        if (o instanceof DagOppdrag) {
            str = o.minsteVolum();
        }
        Container c = finnLedig(str);

        if (c != null) {
            ledige.remove(c);
            o.setContainer(c);
            oppdrag.add(o);
            return "Oppdrag lagt til";
        } else {
            return "Ingen ledige containere";
        }
    }

    public String avsluttOppdrag(String adresse) throws IOException {        
        Oppdrag o = finnOppdrag(adresse);
        
        if (o != null) {
            skrivLogg(o.toString());
            ledige.add(o.container);
            o.setContainer(null);
            oppdrag.remove(o);
            
            return "Oppdrag avsluttet!";
        } else {
            return "Fant ikke oppdrag";
        }
        
    }

    private Container finnLedig(int str) {
        Container minst = null;
        for (Container c : ledige) {
            if (minst == null && c.getVolum() > str) {
                minst = c;
            } else if (minst != null) {
                if (c.getVolum() < minst.getVolum() && c.getVolum() > str) {
                    minst = c;
                }
            }
        }
        return minst;
    }

    private Oppdrag finnOppdrag(String adresse) {
        for (Oppdrag o : oppdrag) {
            if (o.adresse.equals(adresse)) {
                return o;
            }
        }
        return null;
    }

    private void skrivLogg(String str) {
        File fil;
        FileWriter fw;
        BufferedWriter bw;
        
        try {
            fil = new File(FILNAVN);
            fw = new FileWriter(fil, true);
            bw = new BufferedWriter(fw);
            
            bw.write(str);
            bw.newLine();
            
            bw.close();
        } catch (IOException ex) { 
            System.out.println("Kunne ikke skrive til fil");
        }
    }
}
