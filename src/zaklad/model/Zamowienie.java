package zaklad.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Zamowienie {
    private static int kolejneID = 1;
    private int id;
    private Klient klient;
    private Map<Produkt, Integer> produktyIZamowienia;
    private Date dataZlozenia;
    private StatusZamowienia status;

    public Zamowienie(Klient klient) {
        this.id = kolejneID++;
        this.klient = klient;
        this.produktyIZamowienia = new HashMap<>();
        this.dataZlozenia = new Date();
        this.status = StatusZamowienia.NOWE;
    }

    public int getId() {
        return id;
    }

    public void dodajPozycjeZamowienia(Produkt produkt, int ilość) {
        if (produktyIZamowienia.containsKey(produkt)) {
            int staraIlosc = produktyIZamowienia.get(produkt);
            produktyIZamowienia.put(produkt, staraIlosc + ilość);
        } else {
            produktyIZamowienia.put(produkt, ilość);
        }
    }

    public void usuńPozycjęZamowienia(Produkt produkt) {
        produktyIZamowienia.remove(produkt);
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

    public Map<Produkt, Integer> getProduktyIZamowienia() {
        return produktyIZamowienia;
    }

    public double obliczKwoteZamowienia() {
        double suma = 0.0;
        for (Map.Entry<Produkt, Integer> entry : produktyIZamowienia.entrySet()) {
            Produkt produkt = entry.getKey();
            int ilosc = entry.getValue();
            suma += produkt.getCena() * ilosc;
        }
        return suma;
    }
}
