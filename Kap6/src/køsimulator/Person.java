package køsimulator;

public class Person {

    private String navn;
    private long startKø;
    private long sluttKø;

    public Person(String navn) {
        this.navn = navn;
    }

    public long stillIKø() {
        startKø = System.currentTimeMillis();
        return startKø;
    }

    public long betjen() {
        sluttKø = System.currentTimeMillis() - startKø;
        return sluttKø;
    }

    public String getNavn() {
        return navn;
    }

    public long getKøTid() {
        return System.currentTimeMillis() - startKø;
    }

    @Override
    public String toString() {
        return "Person{" + "navn=" + navn + ", startK\u00f8=" + startKø + ", sluttK\u00f8=" + sluttKø + '}';
    }

}
