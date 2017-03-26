package ohtu.verkkokauppa;

/**
 * Created by laura on 26.3.2017.
 */
public interface PankkiInterface {


    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}
