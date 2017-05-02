package ohtu.Komento;

import ohtu.Sovelluslogiikka;

import javax.swing.*;

/**
 * Created by laura on 2.5.2017.
 */
public class Erotus implements Komento{
    Sovelluslogiikka sovellus;
    JTextField tuloskentta;
    JTextField syotekentta;
    int edellinen;

    public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void suorita() {
        this.edellinen = sovellus.tulos();
        int arvo = 0;
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }

        sovellus.miinus(arvo);
        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
    }

    @Override
    public void peru() {
        sovellus.palauta(this.edellinen);
        tuloskentta.setText("" + this.edellinen);
    }
}
