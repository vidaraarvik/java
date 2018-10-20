package kap7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Oppg4 {

    public static void main(String[] args) {
        List<String> c = new ArrayList<>();
        c.add("Yuka1");
        c.add("Zuka2");
        c.add("Duka3");

        printCollection(c);
    }

    public static void printCollection(List c) {
        ListIterator ltr = c.listIterator();
        
        print(ltr);
    }

    private static void print(Iterator it) {
        if (it.hasNext()) {
            
            String txt = it.next().toString();
            
            print(it);
            
            System.out.println(txt);
        }
    }

}
