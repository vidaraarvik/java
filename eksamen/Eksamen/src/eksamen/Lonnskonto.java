package eksamen;

public class Lonnskonto extends Konto {

    private final double LONNSKONTORENTE = 1.5;

    public Lonnskonto() {
        rente = LONNSKONTORENTE;
    }

    @Override
    public String kontotype() {
        return "Lønnskonto";
    }

}
