package comparable;

/**
 * Klasse som representerer en Adresse.
 *
 * @author Vidar Årvik
 */
class Adresse {

    private String gateAdr;
    private String postNr;
    private String postSted;

    public Adresse(String gateAdr, String postNr, String postSted) {
        this.gateAdr = gateAdr;
        this.postNr = postNr;
        this.postSted = postSted;
    }

    /**
     * Returnerer en gateadresse.
     *
     * @return En String som inneholder adressen.
     */
    public String getGateAdr() {
        return gateAdr;
    }

    /**
     * Returnerer et postnummer.
     *
     * @return En String som inneholder postnummeret.
     */
    public String getPostNr() {
        return postNr;
    }

    /**
     * Returnerer et poststed.
     *
     * @return En String som inneholder poststedet.
     */
    public String getPostSted() {
        return postSted;
    }

    /**
     * Returnerer en tekstlig representasjon av dette objektet.
     *
     * @return En String som inneholder en tekstlig representajson av dette obbjektet.
     */
    @Override
    public String toString() {
        return gateAdr + ", " + postNr + " " + postSted;
    }

}
