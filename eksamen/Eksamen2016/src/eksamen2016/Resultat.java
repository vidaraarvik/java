package eksamen2016;

public class Resultat {
    
    private String kallenavn;
    private int poengsum = 0;

    public Resultat(String kallenavn) {
        this.kallenavn = kallenavn;
    }
    
    public void addPoeng(int poeng) {
        this.poengsum += poeng;
    }

}
