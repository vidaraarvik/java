package eksamen;

import java.util.ArrayList;
import java.util.TreeSet;

public class Bank {

    private final TreeSet<Konto> kontoer;
    private ArrayList<Transaksjon> transaksjoner;

    public Bank() {
        kontoer = new TreeSet();
        transaksjoner = new ArrayList();

        Konto k1 = new Lonnskonto();
        Konto k2 = new Sparekonto(1.5, 10);
        Konto k3 = new Sparekonto(2.5, 10);
        k1.settInn(1 + Math.random() * 10000);
        k2.settInn(1 + Math.random() * 10000);
        k3.settInn(1 + Math.random() * 10000);
        kontoer.add(k1);
        kontoer.add(k2);
        kontoer.add(k3);
        
        Logg.getLogg().setNivå(2);
    }

    public Konto finnKonto(int kontoNr) {
        for (Konto k : kontoer) {
            if (k.getKontoNr() == kontoNr) {
                return k;
            }
        }
        return null;
    }
    
    public void nyTransaksjon(Transaksjon tr) {
        transaksjoner.add(tr);
    }
    
    // Kjører alle transaksjoner og returnerer antallet som ble kjørt (ikke avvist)
    public int kjørTransaksjoner() {
        int kjørt = 0;
        
        for (Transaksjon tr : transaksjoner) {
            tr.kjør();
            
            if (tr.getTilstand().equals("Kjørt")) {
                kjørt++;
            }
            
            if (tr.getTilstand().equals("Avvist")) {
                Logg.getLogg().skriv(tr.toString(), 5);
            }
        }
        
        transaksjoner = new ArrayList();
        return kjørt;
    }

    public int getAntTransaksjoner() {
        return transaksjoner.size();
    }
    
}
