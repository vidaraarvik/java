package no.hit.kart;

/* Klasse for å representere Punkt (koordinater).
 *
 * Objekter kan lages på en måte:
 *
 *   Punkt p = new Punkt(int,int);
 *
 * Klassen inneholder også metode for å avgjøre avstand mellom 
 * to punkt og for å konvertere et punkt til tekstlig form.
 *
 */
public class Punkt {

    private int x;
    private int y;

    // Konstruktør
    public Punkt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ";" + y;
    }

    // Returnerer avstand mellom to punkt
    public int avstand(Punkt p) {
        return (int) java.lang.Math.sqrt(Math.pow(-x + p.x, 2) + Math.pow(-y + p.y, 2));
    }

}
