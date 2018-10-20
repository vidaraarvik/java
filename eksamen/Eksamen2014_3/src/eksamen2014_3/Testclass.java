package eksamen2014_3;

import java.io.IOException;

public class Testclass {

    public static void main(String[] args) {

        ContainerModell cm = new ContainerModell();
        
        Oppdrag o = new TimeOppdrag(2, "innbjoa");
        
        cm.regOppdrag(o);
        
        try {
            cm.avsluttOppdrag("innbjoa");
        } catch (IOException ex) {
            System.out.println("kunne ikke skrive til fil...");
        }
        
    }
}
