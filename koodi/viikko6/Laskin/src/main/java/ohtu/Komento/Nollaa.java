package ohtu.Komento;

import ohtu.Sovelluslogiikka;

import javax.swing.*;

/**
 * Created by laura on 2.5.2017.
 */
public class Nollaa implements Komento {
    Sovelluslogiikka sovellus;
    JTextField tuloskentta;
    JTextField syotekentta;

    public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void suorita() {

        sovellus.nollaa();
        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
    }

    @Override
    public void peru() {

    }
}
