package ohtu;

import ohtu.Komento.Erotus;
import ohtu.Komento.Komento;
import ohtu.Komento.Nollaa;
import ohtu.Komento.Summa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Tapahtumankuuntelija implements ActionListener {
    private JButton nollaa;
    private JButton undo;
    private Sovelluslogiikka sovellus;
    private Map<JButton, Komento> komennot;
    private Komento edellinen;

    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();
        komennot.put(plus, new Summa(sovellus, tuloskentta, syotekentta));
        komennot.put(miinus, new Erotus(sovellus, tuloskentta, syotekentta));
        komennot.put(nollaa, new Nollaa(sovellus, tuloskentta, syotekentta));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        Komento komento = komennot.get(ae.getSource());
        if  (komento!=null) {
            komento.suorita();
            edellinen = komento;
        } else {
            // toiminto oli undo
            edellinen.peru();
            edellinen = null;
        }

        nollaa.setEnabled(sovellus.tulos()!=0);
        undo.setEnabled(edellinen!=null);
    }
 
}