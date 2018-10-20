package objektsamling;

public class ObjektSamling {

    private Element[] tabell;
    private int nesteLedige;

    public ObjektSamling(int antall) {
        tabell = new Element[antall];
        nesteLedige = 0;
    }

    // Finner element, gitt nøkkel.
    // Returnerer null hvis objektet ikke finnes.
    public Element finn(String nøkkel) {
        int pos = finnPos(nøkkel);
        if (pos >= 0) // funnet!
        {
            return tabell[pos];
        } else {
            return null;
        }
    }

    // Setter inn et element bakerst. Returnerer false
    // hvis en element med samme nøkkel finnes,
    // eller det ikke er ledig plass, og true ellers.
    public boolean settInn(Element e) {
        boolean ny = finnPos(e.nøkkel()) == -1;
        if (ny && nesteLedige < tabell.length) {
            tabell[nesteLedige] = e;
            nesteLedige++;
            return true;
        } else {
            return false;
        }
    }

    // Sletter et element, gitt nøkkel. Returnerer
    // false hvis objektet ikke finnes, true ellers.
    public boolean slett(String nøkkel) {
        int pos = finnPos(nøkkel);
        if (pos >= 0) {
            nesteLedige--;
            tabell[pos] = tabell[nesteLedige];
            tabell[nesteLedige] = null;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String svar = "";
        for (int i = 0; i < nesteLedige; i++) {
            svar += tabell[i].toString() + "\n";
        }

        return svar;
    }

    // Finner posisjonen til et element, gitt en
    // nøkkel. Leverer -1 hvis objektet ikke er der.
    private int finnPos(String nøkkel) {
        boolean funnet = false;
        int pos = 0;
        while (pos < nesteLedige && !funnet) {
            if (tabell[pos].nøkkel().equals(nøkkel)) {
                funnet = true;
            } else {
                pos++;
            }
        }
        if (funnet) {
            return pos;
        } else {
            return -1;
        }
    }
}
