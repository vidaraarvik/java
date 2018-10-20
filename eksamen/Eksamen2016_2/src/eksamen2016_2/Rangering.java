package eksamen2016_2;

import java.util.TreeSet;

public class Rangering {
    
    private TreeSet<Resultat> resultater;

    public Rangering() {
        Resultat r1 = new Resultat("Ole");
        r1.setPoengsum(20);
        Resultat r2 = new Resultat("Dole");
        r2.setPoengsum(35);
        Resultat r3 = new Resultat("Doffen");
        r3.setPoengsum(25);
        
        resultater = new TreeSet();
        resultater.add(r1);
        resultater.add(r2);
        resultater.add(r3);
    }

    public void leggTilResultat(Resultat res) {
        resultater.add(res);
    }
    
    public void printResultater() {
        for (Resultat res : resultater) {
            System.out.println(res.toString());
        }
    }
}
