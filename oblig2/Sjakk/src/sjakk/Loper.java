package sjakk;

/**
 *
 * @author speedy
 */
public class Loper extends Brikke {

    public Loper(Brett brett, String rute, String farge) {
        super(brett, rute, farge);
    }

    @Override
    public boolean erLovligTrekk(String rutenavn) {
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
