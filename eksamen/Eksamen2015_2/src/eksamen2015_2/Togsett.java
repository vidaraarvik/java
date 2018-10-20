package eksamen2015_2;

import java.util.ArrayList;

public class Togsett implements HarVekt {

    private ArrayList<Vogn> vogner;
    private String sistSett;
    private int id;

    public Togsett(ArrayList<Vogn> vogner, String sistSett, int id) {
        this.vogner = vogner;
        this.sistSett = sistSett;
        this.id = id;
    }

    public ArrayList<Vogn> getVogner() {
        return vogner;
    }

    public void setVogner(ArrayList<Vogn> vogner) {
        this.vogner = vogner;
    }

    public String getSistSett() {
        return sistSett;
    }

    public void setSistSett(String sistSett) {
        this.sistSett = sistSett;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int antallPlasser() {
        int plasser = 0;
        for (Vogn v : vogner) {
            if (v instanceof PassasjerVogn) {
                plasser += ((PassasjerVogn) v).getSitteplasser();
            }
        }
        return plasser;
    }

    @Override
    public int vekt() {
        int vekt = 0;
        for (Vogn v : vogner) {
            vekt += v.vekt();
        }
        return vekt;
    }

    public Gods finnGods(int godsId) {
        for (Vogn v : vogner) {
            if (v instanceof StykkGodsVogn) {
                for (Gods g : ((StykkGodsVogn) v).getGods()) {
                    if (g.getId() == godsId) {
                        return g;
                    }
                }
            }
        }
        return null;
    }

    public boolean harGods(int godsId) {
        return (finnGods(godsId) != null);
    }

    @Override
    public String toString() {
        return "Togsett " + getId() + ": antall vogner=" + vogner.size() + ", vekt=" + vekt() + ", sted=" + sistSett;
    }

}
