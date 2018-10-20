
package mvc;


public class Model {
    private int verdi;
    
    Model() {
        verdi = 0;
    }
    
    public void plussEn() {
        verdi++;
    }
    
    public int getVerdi() {
        return verdi;
    }
}
