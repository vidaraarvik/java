package grensesnitt;


public class GrensesnittDemo {


    public static void main(String[] args) {
        
        Hilser hund1 = new Hund();
        System.out.println( hund1.hilsen() );
        
        Hilser menneske1 = new Menneske();
        System.out.println( menneske1.hilsen() );
        
        
        Hilser[] tabell = new Hilser[10];
        tabell[0] = hund1;
        tabell[1] = menneske1;
    }
    
}
