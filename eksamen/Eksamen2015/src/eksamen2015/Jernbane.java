package eksamen2015;

import java.util.ArrayList;

public class Jernbane {

    public static ArrayList<Togsett> hentAlleTogsett() {
        ArrayList<Gods> gods = new ArrayList<>();
        gods.add(new Gods(1, 14));
        gods.add(new Gods(2, 3));

        BulkVogn bv = new BulkVogn(7500, "Kull", 9900);
        StykkGodsVogn sgv = new StykkGodsVogn(6000, gods);
        PassasjerVogn pv = new PassasjerVogn(10200, 24);

        ArrayList<Vogn> vognsett1 = new ArrayList<>();
        vognsett1.add(bv);
        vognsett1.add(sgv);

        ArrayList<Vogn> vognsett2 = new ArrayList<>();
        vognsett2.add(pv);

        Togsett sett1 = new Togsett(vognsett1, "Oslo", 1, 2);
        Togsett sett2 = new Togsett(vognsett2, "Bø", 2, 1);

        ArrayList<Togsett> alleTogsett = new ArrayList<>();
        alleTogsett.add(sett1);
        alleTogsett.add(sett2);
        
        return alleTogsett;
    }

}
