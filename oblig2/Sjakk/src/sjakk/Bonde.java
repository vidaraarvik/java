package sjakk;

/**
 *
 * @author speedy
 */
public class Bonde extends Brikke {

    public Bonde(Brett brett, String rute, String farge) {
        super(brett, rute, farge);
    }

    @Override
    public boolean erLovligTrekk(String rutenavn) {
        int fraRad = Brett.hentRad(this.getRute());
        int tilRad = Brett.hentRad(rutenavn);
        int fraKol = Brett.hentKol(this.getRute());
        int tilKol = Brett.hentKol(rutenavn);
        if (fraKol == tilKol) {
            if (brett.getBrikke(rutenavn) != null) {
                return false;
            }
            if (fraRad < tilRad) {
                if (tilRad == fraRad + 2) {
                    return this.getRute().charAt(1) == '2';
                }
                if (tilRad == fraRad + 1) {
                    return this.getFarge().charAt(1) == 's';
                }
            } else if (fraRad > tilRad) {
                if (tilRad == fraRad - 2) {
                    return this.getRute().charAt(1) == '7';
                }
                if (tilRad == fraRad - 1) {
                    return this.getFarge().charAt(1) == 'h';
                }
            }
        }
        if (fraKol != tilKol) {
            if (fraRad < tilRad) {
                if (tilRad == fraRad + 1) {
                    return (this.getFarge().charAt(1) == 's'
                            && this.brett.getBrikke(rutenavn).getFarge().charAt(1) != 's');
                }
            } else if (fraRad > tilRad) {
                if (tilRad == fraRad - 1) {
                    return (this.getFarge().charAt(1) == 'h'
                            && this.brett.getBrikke(rutenavn).getFarge().charAt(1) != 'h');
                }
            }
        }
        return false;
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
