package eksamen2015_2;

import java.util.ArrayList;

public class Jernbane {

    public static ArrayList<Togsett> hentAlleTogsett() {
        ArrayList<Gods> godsliste = new ArrayList();
        godsliste.add(new Gods(1, 8));
        godsliste.add(new Gods(4, 5));

        ArrayList<Vogn> vogner = new ArrayList();
        ArrayList<Vogn> vogner2 = new ArrayList();
        vogner.add(new PassasjerVogn(32, 9000));
        vogner.add(new BulkVogn("Olje", 6000, 12000));
        vogner2.add(new StykkGodsVogn(godsliste, 10000));

        ArrayList<Togsett> alleTogsett = new ArrayList();
        alleTogsett.add(new Togsett(vogner, "Bø", 1));
        alleTogsett.add(new Togsett(vogner2, "Oslo", 2));
        
        return alleTogsett;
    }

}
