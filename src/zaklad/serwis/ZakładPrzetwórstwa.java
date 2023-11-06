package zaklad.serwis;

import zaklad.model.*;

import java.util.ArrayList;
import java.util.List;

public class ZakładPrzetwórstwa {
    private List<Produkt> produkty;
    private List<Dostawca> dostawcy;
    private List<Klient> klienci;
    private List<Zamowienie> zamowienia;

    public ZakładPrzetwórstwa() {
        produkty = new ArrayList<>();
        dostawcy = new ArrayList<>();
        klienci = new ArrayList<>();
        zamowienia = new ArrayList<>();
    }

    public void DodajProdukt(Produkt produkt) {
        produkty.add(produkt);
    }

    public void UsuńProdukt(Produkt produkt) {
        produkty.remove(produkt);
    }

    public void DodajDostawcę(Dostawca dostawca) {
        dostawcy.add(dostawca);
    }

    public void PrzyjmijZamówienie(Zamowienie zamowienie) {
        zamowienia.add(zamowienie);
    }

    public void DodajKlienta(Klient klient) {
        klienci.add(klient);
    }

    public void AktualizujStatusZamówienia(Zamowienie zamowienie, StatusZamowienia nowyStatus) {
        zamowienie.setStatus(nowyStatus);
    }

    public List<Produkt> getProdukty() {
        return produkty;
    }

    public void setProdukty(List<Produkt> produkty) {
        this.produkty = produkty;
    }

    public List<Dostawca> getDostawcy() {
        return dostawcy;
    }

    public void setDostawcy(List<Dostawca> dostawcy) {
        this.dostawcy = dostawcy;
    }

    public List<Klient> getKlienci() {
        return klienci;
    }

    public void setKlienci(List<Klient> klienci) {
        this.klienci = klienci;
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public List<Zamowienie> PobierzZamówieniaKlienta(Klient klient) {
        List<Zamowienie> zamówieniaKlienta = new ArrayList<>();
        for (Zamowienie zamowienie : zamowienia) {
            if (zamowienie.getKlient().equals(klient)) {
                zamówieniaKlienta.add(zamowienie);
            }
        }
        return zamówieniaKlienta;
    }

    public void WygenerujRaport() {
        System.out.println("Raport wygenerowany.");
    }

//    public double ObliczZyski() {
////        double zyski = 0.0;
////        for (Zamowienie zamowienie : zamowienia) {
////            for (PozycjaZamowienia pozycja : zamowienie.getPozycjZamowienia()) {
////                zyski += pozycja.getProdukt().getCena() * pozycja.getIlość();
////            }
////        }
////        return zyski;
//    }
}
