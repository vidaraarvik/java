package collection;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class SkrivBaklengs {

    public static void main(String[] args) {
        List<String> c = new ArrayList<>();
        c.add("Yuka1");
        c.add("Zuka2");
        c.add("Duka3");
        
        skrivBaklengs(c);
    }

    private static void skrivBaklengs(List c) {
        ListIterator ltr = c.listIterator();
        
        String[] tab = new String[c.size()];
        
        int t = 0;
        while(ltr.hasNext()) {
            tab[t] = ltr.next().toString();
            t++;
        }
        
        for(int i=tab.length-1; i>=0; i--) {
            System.out.println(tab[i]);
        }
    }
}
