/**
 * Vidar Årvik, 10-2017
 * vidaraarvik@gmail.com
 *
 * Brikke.java:
 * En generell klasse for hver av brikketypene. Hver brikke
 * arver denne klassens attributter og metoder - men overstyrer
 * metoden erLovligTrekk() da denne er forskjellig fra hver brikke.
 */
package sjakk;

public abstract class Brikke {

    protected Brett brett;
    private String rute;
    private String farge;   // variabelen farge innholder brikkens type+farge, f.eks bh (bonde hvit).

    // Konstruktør
    public Brikke(Brett brett, String rute, String farge) {
        this.brett = brett;
        this.rute = rute;
        this.farge = farge;
    }

    public abstract boolean erLovligTrekk(String rutenavn);

    public abstract String brikkenavn();

    public abstract void flyttTil(String rutenavn);

    public String getRute() {
        return rute;
    }

    public void setRute(String rute) {
        this.rute = rute;
    }

    public String getFarge() {
        return farge;
    }

    public void setFarge(String farge) {
        this.farge = farge;
    }

}
