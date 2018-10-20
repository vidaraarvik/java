package eksamen;

public abstract class Konto implements Comparable<Konto> {

    private final int kontoNr;
    protected double rente;
    protected double saldo;
    private static int kontoTeller = 1;

    public Konto() {
        this.saldo = 0;
        this.kontoNr = kontoTeller++;
    }

    public abstract String kontotype();

    public boolean equals(Konto k) {
        return this.kontoNr == k.kontoNr;
    }

    @Override
    public String toString() {
        return kontotype() + ": kontonr=" + kontoNr + ", saldo=" + saldo;
    }

    public void settInn(double beløp) {
        saldo += beløp;
    }

    public void taUt(double beløp) throws IllegalArgumentException {
        if (beløp <= saldo) {
            saldo = saldo - beløp;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public int getKontoNr() {
        return kontoNr;
    }

    @Override
    public int compareTo(Konto k) {
        if (kontoNr > k.kontoNr) {
            return 1;
        } else if (kontoNr < k.kontoNr) {
            return -1;
        } else {
            return 0;
        }
    }

}
