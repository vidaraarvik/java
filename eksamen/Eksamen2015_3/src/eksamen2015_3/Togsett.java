package eksamen2015_3;

import java.util.ArrayList;

public class Togsett implements HarVekt {

    private ArrayList<Vogn> vognListe;
    private String sistSett;
    private int id;

    public Togsett(ArrayList<Vogn> vognListe, String sistSett, int id) {
        this.vognListe = vognListe;
        this.sistSett = sistSett;
        this.id = id;
    }

    public ArrayList<Vogn> getVognListe() {
        return vognListe;
    }

    public void setVognListe(ArrayList<Vogn> vognListe) {
        this.vognListe = vognListe;
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
        int sitteplasser = 0;
        for (Vogn v : vognListe) {
            if (v instanceof PassasjerVogn) {
                sitteplasser += ((PassasjerVogn) v).getSitteplasser();
            }
        }
        return sitteplasser;
    }

    @Override
    public int vekt() {
        int vekt = 0;
        for (Vogn v : vognListe) {
            vekt += v.vekt();
        }
        return vekt;
    }

    public Gods finnGods(int godsId) {
        for (Vogn v : vognListe) {
            if (v instanceof StykkGodsVogn) {
                for (Gods g : ((StykkGodsVogn) v).getGodsListe()) {
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
        return "Togsett " + id + ": antall vogner=" + vognListe.size() + ", vekt=" + vekt() + ", sted=" + sistSett;
    }

}
