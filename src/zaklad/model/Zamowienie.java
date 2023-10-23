package zaklad.model;

import java.util.ArrayList;
import java.util.Date;

public class Zamowienie {
    private Klient klient;
    private ArrayList<PozycjaZamowienia> pozycjeZamowienia;
    private Date dataZlozenia;
    private StatusZamowienia status;

    public Zamowienie(Klient klient) {
        this.klient = klient;
        this.pozycjeZamowienia = new ArrayList<>();
        this.dataZlozenia = new Date();
        this.status = StatusZamowienia.NOWE;
    }

    public void dodajPozycjeZamowienia(Produkt produkt, int ilość) {
        PozycjaZamowienia pozycja = new PozycjaZamowienia(produkt, ilość);
        pozycjeZamowienia.add(pozycja);
    }

    public void usuńPozycjęZamowienia(PozycjaZamowienia pozycja) {
        pozycjeZamowienia.remove(pozycja);
    }

    public Klient getKlient() {
        return klient;
    }

    public Date getDataZlozenia() {
        return dataZlozenia;
    }

    public StatusZamowienia getStatus() {
        return status;
    }

    public void setStatus(StatusZamowienia status) {
        this.status = status;
    }

}
