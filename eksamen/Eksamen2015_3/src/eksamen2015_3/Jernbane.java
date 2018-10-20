package eksamen2015_3;

import java.util.ArrayList;

public class Jernbane {
    
    public static ArrayList<Togsett> hentAlleTogsett() {
        ArrayList<Gods> godsListe = new ArrayList();
        godsListe.add(new Gods(1, 4));
        godsListe.add(new Gods(2, 9));
        
        ArrayList<Vogn> vogner = new ArrayList();
        vogner.add(new PassasjerVogn(32, 8000));
        vogner.add(new BulkVogn("Kull", 6000, 10000));
        ArrayList<Vogn> vogner2 = new ArrayList();
        vogner2.add(new StykkGodsVogn(godsListe, 9000));
        
        ArrayList<Togsett> alleTogsett = new ArrayList();
        alleTogsett.add(new Togsett(vogner, "Oslo", 1));
        alleTogsett.add(new Togsett(vogner2, "Bergen", 2));
        
        return alleTogsett;
    }
    
}
