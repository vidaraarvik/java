package sjakk;

/**
 *
 * @author speedy
 */
public class Konge extends Brikke {

    public Konge(Brett brett, String rute, String farge) {
        super(brett, rute, farge);
    }

    @Override
    public boolean erLovligTrekk(String rutenavn) {
        int fraRad = Brett.hentRad(this.getRute());
        int tilRad = Brett.hentRad(rutenavn);
        int fraKol = Brett.hentKol(this.getRute());
        int tilKol = Brett.hentKol(rutenavn);
        if (tilRad > fraRad + 1 || tilRad < fraRad - 1) {
            return false;
        }
        if (tilKol > fraKol + 1 || tilKol < fraKol - 1) {
            return false;
        }
        return true;
    }

    @Override
    public String brikkenavn() {
        return this.getFarge();
    }

    @Override
    public void flyttTil(String rutenavn) {
        this.setRute(rutenavn);
    }

}
