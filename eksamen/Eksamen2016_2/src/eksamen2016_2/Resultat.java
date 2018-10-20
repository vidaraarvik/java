package eksamen2016_2;

public class Resultat {
    
    private String kallenavn;
    private int poengsum;

    public Resultat(String kallenavn) {
        this.kallenavn = kallenavn;
        this.poengsum = 0;
    }

    public void setPoengsum(int poengsum) {
        this.poengsum += poengsum;
    }

    @Override
    public String toString() {
        return "Kallenavn=" + kallenavn + ", poengsum=" + poengsum;
    }

    
}
