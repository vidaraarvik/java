/**
 * Vidar Årvik, 10-2017
 * vidaraarvik@gmail.com
 *
 * Brett.java:
 * En klasse som tar vare på brett-objektet og inneholder logikk for
 * å gjennomføre et sjakktrekk.
 */
package sjakk;

class Brett {

    protected final int BRETTSTORRELSE = 8;
    private int spillNr;
    protected Brikke[][] brikkene;

    // Konstruktør
    public Brett(int nyttSpillNr) {
        spillNr = nyttSpillNr;
        brikkene = new Brikke[BRETTSTORRELSE][BRETTSTORRELSE];

        // Oppretter brikker til et nytt sjakkbrett og plasserer
        // de i sstartposisjon.
        for (int rad = 0; rad < BRETTSTORRELSE; rad++) {
            for (int kol = 0; kol < BRETTSTORRELSE; kol++) {
                char k = (char) (kol + 97);
                String rute = "" + k + "" + (rad + 1);

                if (rad == 1) {
                    brikkene[rad][kol] = new Bonde(this, rute, "bs");
                }
                if (rad == 6) {
                    brikkene[rad][kol] = new Bonde(this, rute, "bh");
                }

                if (rad == 0) {
                    if (kol == 0 || kol == 7) {
                        brikkene[rad][kol] = new Taarn(this, rute, "ts");
                    }
                    if (kol == 2 || kol == 5) {
                        brikkene[rad][kol] = new Loper(this, rute, "ls");
                    }
                    if (kol == 4) {
                        brikkene[rad][kol] = new Konge(this, rute, "ks");
                    }
                }

                if (rad == 7) {
                    if (kol == 0 || kol == 7) {
                        brikkene[rad][kol] = new Taarn(this, rute, "th");
                    }
                    if (kol == 2 || kol == 5) {
                        brikkene[rad][kol] = new Loper(this, rute, "lh");
                    }
                    if (kol == 4) {
                        brikkene[rad][kol] = new Konge(this, rute, "kh");
                    }
                }
            }
        }
    }

    // Sjekker om oppgit rutenavn er lovligt (a-h)+(1-8)
    public static boolean erLovligRutenavn(String rutenavn) {
        rutenavn = rutenavn.toLowerCase();
        int rad = hentRad(rutenavn);
        int kol = hentKol(rutenavn);
        return rad >= 0 && rad <= 7 && kol >= 0 && kol <= 7;
    }

    // Returnerer brikken som står i oppgitt rute, null hvis ruta er tom.
    public Brikke getBrikke(String rutenavn) {
        if (erLovligRutenavn(rutenavn)) {
            int rad = hentRad(rutenavn);
            int kol = hentKol(rutenavn);
            return brikkene[rad][kol];
        }
        return null;
    }

    // Flytter en brikke fraRute -> tilRute
    // Returnerer true hvis flytting blir gjennoført, false hvis ikke.
    public boolean flyttBrikke(String fraRute, String tilRute) {
        int fraRad = hentRad(fraRute);
        int fraKol = hentKol(fraRute);
        int tilRad = hentRad(tilRute);
        int tilKol = hentKol(tilRute);
        Brikke brikke = getBrikke(fraRute);
        Brikke ukjent = getBrikke(tilRute);
        if (brikke.erLovligTrekk(tilRute)) { // lovlig trekk?
            if (ukjent == null) {   // tomt felt?
                brikke.flyttTil(tilRute); // flytt brikken
                brikkene[tilRad][tilKol] = brikke;    //oppdaterer brikke tabell
                brikkene[fraRad][fraKol] = null;      // -..-
                return true;
            } else if (ukjent.getFarge() != null && ukjent.getFarge().charAt(1) != brikke.getFarge().charAt(1)) { // felt opptatt av brikke med motsatt farge?
                brikke.flyttTil(tilRute); // flytt brikken
                brikkene[tilRad][tilKol] = null;      //oppdaterer brikke tabell
                brikkene[tilRad][tilKol] = brikke;    //-..-
                brikkene[fraRad][fraKol] = null;      //-..-
                return true;
            }
        }
        return false;
    }

    // Returnerer spill-nummer
    public int getSpillNr() {
        return spillNr;
    }

    // Returnerer kolonne-verdi (0-7) i et rutenavn
    public static int hentKol(String rutenavn) {
        rutenavn = rutenavn.toLowerCase();
        int kol = (int) rutenavn.charAt(0);
        kol = kol - 97; //Gjør om fra char-verdi til kolonneNr
        return kol;
    }

    // Returnerer rad-verdi (0-7) i et rutenavn
    public static int hentRad(String rutenavn) {
        rutenavn = rutenavn.toLowerCase();
        int rad = Integer.parseInt(rutenavn.substring(1));
        rad = rad - 1;
        return rad;
    }

}
