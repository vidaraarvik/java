package comparable;

/**
 * Klasse som representerer en Person.
 * @author Vidar Årvik
 */
public class Person implements Comparable<Person> {

    protected String navn;
    protected Adresse adresse;
    protected int kundeNr;

    public Person(String navn, Adresse adresse, int kundeNr) {
        this.navn = navn;
        this.adresse = adresse;
        this.kundeNr = kundeNr;
    }

    @Override
    public String toString() {
        return kundeNr + ": " + navn + ",  " + adresse;
    }
    
    
    
    @Override
    public int compareTo(Person p) {
        return navn.compareTo(p.navn);
    }

}
