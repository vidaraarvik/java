package flyreise;

import java.util.TreeSet;

public class PassasjerListeTest {

    public static void main(String[] args) {
            
        Passasjer p1 = new Passasjer("Vidar Årvik", new Dato(26,2,1993));
        Passasjer p2 = new Passasjer("Christine Årvik", new Dato(14,8,1995));
        Passasjer p3 = new Passasjer("Tida Madelen Årvik", new Dato(20,05,2018));
        
        TreeSet<Passasjer> pListe = new TreeSet<>();
        pListe.add(p1);
        pListe.add(p2);
        pListe.add(p3);
       
        for (Passasjer p : pListe) {
            System.out.println(p.toString());
        }
        
        
        
        
      
    }
}
