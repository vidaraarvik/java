package eksamen2014_2;

import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {
        
        ContainerModell cm = new ContainerModell();
        
        Oppdrag o = new TimeOppdrag(25, "ølen");
        
        cm.regOppdrag(o);
        
        cm.avsluttOppdrag(o.adresse);
        
        System.out.println(o.toString());
        
        
    }
    
}
