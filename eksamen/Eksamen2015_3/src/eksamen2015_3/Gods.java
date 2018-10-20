package eksamen2015_3;

class Gods implements HarVekt {
    
    private int id;
    private int kilo;

    public Gods(int id, int kilo) {
        this.id = id;
        this.kilo = kilo;
    }

    public int getId() {
        return id;
    }

    @Override
    public int vekt() {
        return kilo;
    }

    @Override
    public String toString() {
        return "Gods{" + "id=" + id + ", kilo=" + kilo + '}';
    }
    
}
