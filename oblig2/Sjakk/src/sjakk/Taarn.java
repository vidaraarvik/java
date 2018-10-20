package sjakk;

public class Taarn extends Brikke {

    public Taarn(Brett brett, String rute, String farge) {
        super(brett, rute, farge);
    }

    @Override
    public boolean erLovligTrekk(String rutenavn) {
        int fraRad = Brett.hentRad(this.getRute());
        int tilRad = Brett.hentRad(rutenavn);
        int fraKol = Brett.hentKol(this.getRute());
        int tilKol = Brett.hentKol(rutenavn);
        boolean ledig = true;
        // Samme rad:
        if (fraRad != tilRad && fraKol != tilKol) {
            return false;
        }
        if (fraRad == tilRad) {
            if (fraKol > tilKol) {
                for (int kol = fraKol - 1; kol > tilKol; kol--) {
                    ledig = brett.brikkene[fraRad][kol] == null;
                    if (!ledig) {
                        return false;
                    }
                }
            } else {
                for (int kol = fraKol + 1; kol < tilKol; kol++) {
                    ledig = brett.brikkene[fraRad][kol] == null;
                    if (!ledig) {
                        return false;
                    }
                }
            }
        } else if (fraKol == tilKol) {
            if (fraRad > tilRad) {
                for (int rad = fraRad - 1; rad > tilRad; rad--) {
                    ledig = brett.brikkene[rad][fraKol] == null;
                    if (!ledig) {
                        return false;
                    }
                }
            } else {
                for (int rad = fraRad + 1; rad < tilRad; rad++) {
                    ledig = brett.brikkene[rad][fraKol] == null;
                    if (!ledig) {
                        return false;
                    }
                }
            }
        }
        return ledig;
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
