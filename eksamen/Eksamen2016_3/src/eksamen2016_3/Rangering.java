package eksamen2016_3;

import java.util.ArrayList;

public class Rangering {

    private ArrayList<Resultat> resultatListe;

    public Rangering() {
        resultatListe = new ArrayList();
        
        Resultat r1 = new Resultat("Ole", 10);
        Resultat r2 = new Resultat("Dole", 25);
        Resultat r3 = new Resultat("Doffen", 15);

        resultatListe.add(r1);
        resultatListe.add(r2);
        resultatListe.add(r3);
    }

    public void skrivUtListe() {
        for (Resultat res : resultatListe) {
            System.out.println(res.toString());
        }
    }

    public void leggTilResultat(Resultat res) {
        resultatListe.add(res);
    }
}
