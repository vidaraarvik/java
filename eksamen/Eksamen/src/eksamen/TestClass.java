package eksamen;

// Klasse brukt under testing
public class TestClass {

    public static void main(String[] args) {

        Bank bank = new Bank();

        Konto k2 = bank.finnKonto(2);
        Konto k1 = bank.finnKonto(1);

        Transaksjon tr = new Transaksjon(k2, k1, 10600);
        bank.nyTransaksjon(tr);

        bank.kjørTransaksjoner();

    }
}
