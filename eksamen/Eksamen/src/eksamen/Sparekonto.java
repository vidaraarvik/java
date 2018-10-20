package eksamen;

public class Sparekonto extends Konto {

    private int antLovligeUttak;
    private int antUttak;

    public Sparekonto(double rente, int antallUttak) {
        if (rente >= 0) {
            this.rente = rente;
            this.antLovligeUttak = antallUttak;
            this.antUttak = 0;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String kontotype() {
        return "Sparekonto";
    }

    private void nullstill() {
        antUttak = antLovligeUttak;
    }

    @Override
    public void taUt(double beløp) throws IllegalArgumentException {
        if (beløp <= saldo && antUttak < antLovligeUttak) {
            saldo = saldo - beløp;
            antUttak++;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
