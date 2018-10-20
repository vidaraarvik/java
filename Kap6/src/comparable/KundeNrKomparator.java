package comparable;

import java.util.Comparator;

/**
 * Klasse som håndterer sammenligning mellom to person-objekt basert på kundenummer.
 *
 * @author Vidar Årvik
 */
public class KundeNrKomparator implements Comparator<Person> {

    /**
     *
     * @param p1 Person som skal sammenlignes.
     * @param p2 Person det skal sammenlignes mot.
     * @return Negativt tall hvis p1 er mindre enn p2. Positivt tall hvis p1 er større enn p2. 0 hvis de er like.
     */
    @Override
    public int compare(Person p1, Person p2) {
        return p1.kundeNr - p2.kundeNr;
    }

}
