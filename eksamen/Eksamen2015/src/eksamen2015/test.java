package eksamen2015;

import java.util.ArrayList;

public class test {

    public static void main(String[] args) {

        ArrayList<Togsett> togsettListe = Jernbane.hentAlleTogsett();

        int id = 1;

        for (Togsett ts : togsettListe) {
            if (ts.harGods(id)) {
                System.out.println("fant gods");
            } else {
                System.out.println("fant ikke gods");
            }

        }
        
        
        
        
        
        
        
        
        
        
    }
}
