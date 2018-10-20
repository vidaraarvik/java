package kalkulator;

public class KalkulatorTest {

    public static void main(String[] args) {
        Kalkulator k = new Kalkulator();
        k.siffer(1);
        k.siffer(2);
        k.oper('+');
        k.siffer(3);
        k.erLik();
        System.out.println("Verdi: " + k.hentTall());

    }
}
