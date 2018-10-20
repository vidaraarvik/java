package eksamen2015;

public class Gods implements HarVekt {

    public int id;
    public int vekt;    // i kilo

    public Gods(int id, int vekt) {
        this.id = id;
        this.vekt = vekt;
    }

    private int getId() {
        return id;
    }

    public double getVekt() {
        return vekt;
    }

    @Override
    public int godsVekt() {
        return vekt;
    }

    @Override
    public int vekt() {
        return godsVekt();
    }

    @Override
    public String toString() {
        return "Gods{" + "id=" + id + ", vekt=" + vekt + '}';
    }

    
    
}
