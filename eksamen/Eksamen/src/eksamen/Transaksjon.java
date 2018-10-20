package eksamen;

public class Transaksjon {

    private final Konto fraKonto;
    private final Konto tilKonto;
    private final double beløp;
    private String tilstand;

    public Transaksjon(Konto fraKonto, Konto tilKonto, double beløp) {
        this.fraKonto = fraKonto;
        this.tilKonto = tilKonto;
        this.beløp = beløp;
        this.tilstand = "Opprettet";
    }

    public void kjør() {
        try {
            fraKonto.taUt(beløp);
        } catch (IllegalArgumentException ex) {
            tilstand = "Avvist";
        }

        if (tilstand.equals("Opprettet")) {
            tilKonto.settInn(beløp);
            tilstand = "Kjørt";
        }
        
        // Brukt under testing, 
        // skriver ut informasjon om overføringen til konsollet
        System.out.println("" + fraKonto + " ---> " + tilKonto + " " + beløp + " " + tilstand);
    }

    public String getTilstand() {
        return tilstand;
    }

    @Override
    public String toString() {
        return "Transaksjon: fraKonto=" + fraKonto + ", tilKonto=" + tilKonto + ", beløp=" + beløp;
    }

}
