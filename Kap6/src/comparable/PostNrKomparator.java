package comparable;

import java.util.Comparator;

/**
 * Klasse som håndterer sammenligning mellom to person-objekt basert på postnummer.
 * @author Vidar Årvik
 */
public class PostNrKomparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return p1.adresse.getPostNr().compareTo(p2.adresse.getPostNr());
    }

}
