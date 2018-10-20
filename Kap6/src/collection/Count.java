package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Count {

    public static void main(String[] args) {
        
        
        Collection<String> c1 = new ArrayList<>();
        c1.add("Yuka");
        c1.add("Zuka");
        c1.add("Duka");
        Collection<String> c2 = new ArrayList<>();
        c2.add("Yuka");
        c2.add("Kia");
        c2.add("Kine");
        Collection<String> c3 = new ArrayList<>();
        c3.add("Lola");
        c3.add("Lola");
        c3.add("Lola");
        
        
        Collection<Collection<String>> c = new ArrayList<>();
        c.add(c1);
        c.add(c2);
        c.add(c3);
        
        int antall = count(c, "Yuka");
        System.out.println(antall);
        
        
        
        
        
    }

    private static int count(Collection<Collection<String>> c, String str) {
        int antall = 0;
        for (Collection<String> coll : c) {
            for (String s : coll) {
                if (s.equals("Yuka")) {
                    antall++;
                }
            }
        }
        return antall;
    }
    
}
