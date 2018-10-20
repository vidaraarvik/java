package eksamen2016_3;

public class Resultat {
    
    private String kallenavn;
    private int poengsum;

    public Resultat(String kallenavn, int poengsum) {
        this.kallenavn = kallenavn;
        this.poengsum = poengsum;
    }

    @Override
    public String toString() {
        return "Kallenavn:" + kallenavn + ", poengsum=" + poengsum + '}';
    }

}
