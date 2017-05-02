
package ohtu.intjoukkosovellus;

public class intJoukko {

    public final static int KAPASITEETTI = 2, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujoukko;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public intJoukko() {
        this.luoJoukko(KAPASITEETTI, OLETUSKASVATUS);
    }

    public intJoukko(int kapasiteetti) {
        kapasiteettiJaKasvatuskokoPositiivisia(kapasiteetti, OLETUSKASVATUS);
        this.luoJoukko(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public intJoukko(int kapasiteetti, int kasvatuskoko) {
        kapasiteettiJaKasvatuskokoPositiivisia(kapasiteetti, kasvatuskoko);
        this.luoJoukko(kapasiteetti, kasvatuskoko);
    }

    public void luoJoukko(int kapasiteetti, int kasvatuskoko) {
        lukujoukko = new int[kapasiteetti];
        for (int i = 0; i < lukujoukko.length; i++) {
            lukujoukko[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public void kapasiteettiJaKasvatuskokoPositiivisia(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti ei voi olla negatiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Ei voi kasvattaa negatiivisella määrällä");
        }
    }

    public boolean lisaa(int luku) {
        if (!sisaltaaLuvun(luku)) {
            lisaaHelper(luku);
            kasvataTaulukkoa();
            return true;
        }
        return false;
    }

    public void lisaaHelper(int luku) {
        lukujoukko[alkioidenLkm] = luku;
        alkioidenLkm++;
    }

    public void kasvataTaulukkoa() {
        if (alkioidenLkm % lukujoukko.length == 0) {
            int[] vanhaTaulukko = lukujoukko;
            lukujoukko = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(vanhaTaulukko, lukujoukko);
        }
    }

    public boolean sisaltaaLuvun(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujoukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        if(sisaltaaLuvun(luku)) {
            poistaTaulukosta(luku);
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    public void poistaTaulukosta(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujoukko[i]) {
                lukujoukko[i] = 0;
                siirraLukujaVasemmalle(i);
            }
        }
    }

    public void siirraLukujaVasemmalle(int alkaen) {
        int apu;
        for (int j = alkaen; j < alkioidenLkm - 1; j++) {
            apu = lukujoukko[j];
            lukujoukko[j] = lukujoukko[j + 1];
            lukujoukko[j + 1] = apu;
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int alkioidenLukumaara() { return alkioidenLkm; }


    @Override
    public String toString() {
        String string = "";
        if(alkioidenLkm != 0) {
            string = toStringHelper(string);
        }
        return "{" + string + "}";
    }

    public String toStringHelper(String string) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if(i != alkioidenLkm - 1) {
                string += lukujoukko[i] + ", ";
            }
        }
        return string + lukujoukko[alkioidenLkm - 1];
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujoukko[i];
        }
        return taulu;
    }
   

    public static intJoukko yhdiste(intJoukko a, intJoukko b) {
        for (int i = 0; i < b.alkioidenLkm; i++) {
            a.lisaa(b.lukujoukko[i]);
        }
        return a;
    }

    public static intJoukko leikkaus(intJoukko a, intJoukko b) {
        for (int i = 0; i < a.alkioidenLkm; i++) {
            if(!b.sisaltaaLuvun(a.lukujoukko[i])) {
                b.lisaa(a.lukujoukko[i]);
            }
        }
        return b;
    }
    
    public static intJoukko erotus(intJoukko a, intJoukko b) {
        for (int i = 0; i < a.alkioidenLkm; i++) {
            if(b.sisaltaaLuvun(a.lukujoukko[i])) {
                a.poista(a.lukujoukko[i]);
            }
        }
        return a;
    }
        
}