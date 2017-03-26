package ohtu.verkkokauppa;

/**
 * Created by laura on 26.3.2017.
 */
public interface VarastoInterface {

    Tuote haeTuote(int id);

    int saldo(int id);

    void otaVarastosta(Tuote t);

    void palautaVarastoon(Tuote t);
}
