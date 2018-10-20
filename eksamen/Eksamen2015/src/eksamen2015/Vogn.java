package eksamen2015;

public abstract class Vogn implements HarVekt{
    
    private int nettoVekt;

    public Vogn(int nettoVekt) {
        this.nettoVekt = nettoVekt;
    }
    
    public int nettoVekt() {
        return nettoVekt;
    }

}
