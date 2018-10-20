package eksamen2015;

import java.util.ArrayList;

public class Togsett implements HarVekt {

    private ArrayList<Vogn> vogner;
    private String sistRegistrert;
    private int togsettId;
    private int plasser;

    public Togsett(ArrayList<Vogn> vogner, String sistRegistrert, int togsettId, int plasser) {
        this.vogner = vogner;
        this.sistRegistrert = sistRegistrert;
        this.togsettId = togsettId;
        this.plasser = plasser;
    }

    public ArrayList<Vogn> getVogner() {
        return vogner;
    }

    public void setVogner(ArrayList<Vogn> vogner) {
        this.vogner = vogner;
    }

    public String getSistRegistrert() {
        return sistRegistrert;
    }

    public void setSistRegistrert(String sistRegistrert) {
        this.sistRegistrert = sistRegistrert;
    }

    public int getTogsettId() {
        return togsettId;
    }

    public void setTogsettId(int togsettId) {
        this.togsettId = togsettId;
    }

    public int antallPlasser() {
        return plasser;
    }

    @Override
    public int godsVekt() {
        int godsVekt = 0;
        for (Vogn v : vogner) {
            godsVekt += v.godsVekt();
        }
        return godsVekt;
    }

    @Override
    public int vekt() {
        int vekt = 0;
        for (Vogn v : vogner) {
            vekt += v.nettoVekt();
        }
        return vekt + godsVekt();
    }

    // Leverer gods med gitt godsId hvis det finnes, ellers null.
    public Gods finnGods(int godsId) {
        for (Vogn v : vogner) {

            try {
                StykkGodsVogn sgv = (StykkGodsVogn) v;
                ArrayList<Gods> gods = sgv.getGods();

                for (Gods g : gods) {
                    if (g.id == godsId) {
                        return g;
                    }
                }
            } catch (ClassCastException ex) {

            }
        }
        return null;
    }

    // Har togsettet gods med gitt godsId?
    public boolean harGods(int godsId) {
        return finnGods(godsId) != null;
    }

    @Override
    public String toString() {
        int vekt = 0;
        for (Vogn v : vogner) {
            vekt += v.vekt();
        }
        return "Togsett" + togsettId + ": antall vogner=" + plasser + ", vekt=" + vekt + ", sted=" + sistRegistrert;
    }

}
