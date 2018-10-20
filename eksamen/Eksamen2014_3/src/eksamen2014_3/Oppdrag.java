package eksamen2014_3;

public abstract class Oppdrag {
    
    protected String adresse;
    protected Container container;

    public Oppdrag(String adresse) {
        this.adresse = adresse;
    }

    public void setContainer(Container container) {
        this.container = container;
    }
    
    public abstract int minsteVolum();
    
    public abstract double pris();
}
