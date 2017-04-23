package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
/**
 * Created by laura on 23.4.2017.
 */
public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    Ostoskori kori;

    @Before
    public void setUp() {
        // luodaan ensin mock-oliot
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kori = mock(Ostoskori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        // määritellään että tuote numero 2 on leipä jonka hinta on 3 ja saldo 12
        when(varasto.saldo(2)).thenReturn(18);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 3));
        // määritellään että tuote numero 3 on olut jonka hinta on 6 ja saldo 0
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "olut", 6));

        // sitten testattava kauppa
        k = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));
    }

    @Test
    public void kahdenTuotteenOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(8));
    }

    @Test
    public void useammanSamanTuotteenOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("santeri", "98765");
        verify(pankki).tilisiirto(eq("santeri"), anyInt(), eq("98765"), anyString(), eq(10));
    }

    @Test
    public void loppuneenTuotteenOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(3));
    }

    @Test
    public void metodinAloitaAsiointiKutsuminenNollaaEdellisenOstoksenTiedot() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("lauri", "123123");
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(5));
        //Uusi ostos
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("laura", "14725");
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(3));
    }

    @Test
    public void pyydetaanUusiViiteJokaiselleMaksutapahtumalle() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("lauri", "123123");
        verify(viite, times(1)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("laura", "14725");
        verify(viite, times(2)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        verify(viite, times(3)).uusi();
    }
}
