package zaklad.serwis;

import zaklad.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZakladPrzetworstwa {
    private List<Produkt> produkty;
    private List<Dostawca> dostawcy;
    private List<Klient> klienci;
    private List<Zamowienie> zamowienia;

    public ZakladPrzetworstwa() {
        produkty = new ArrayList<>();
        dostawcy = new ArrayList<>();
        klienci = new ArrayList<>();
        zamowienia = new ArrayList<>();
    }

    public void DodajProdukt(Produkt produkt) {
        boolean produktIstnieje = false;
        for (Produkt p : produkty) {
            if (p.getId() == produkt.getId()) {
                p.setIlośćDostępnychSztuk(p.getIlośćDostępnychSztuk() + 1);
                produktIstnieje = true;
                break;
            }
        }

        if (!produktIstnieje) {
            produkty.add(produkt);
        }
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

    public List<Dostawca> getListaDostawcow() {
        return new ArrayList<>(dostawcy);
    }

    public Dostawca znajdzDostawceProduktu(Produkt produkt, List<Dostawca> listaDostawcow) {
        for (Dostawca dostawca : listaDostawcow) {
            if (produkt.pobierzDostawcę().getId() == dostawca.getId()) {
                return dostawca;
            }
        }
        return null;
    }

    public Dostawca znajdzDostawce(int id, List<Dostawca> listaDostawcow) {
        for (Dostawca dostawca : listaDostawcow) {
            if (dostawca.getId() == id) {
                return dostawca;
            }
        }
        return null;
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

    public void wyswietlZamowienia() {
        for (Zamowienie zamowienie : zamowienia) {
            System.out.print("ID: " + zamowienie.getId() + ", ");
            System.out.print("Klient: " + zamowienie.getKlient().getImie() + " " + zamowienie.getKlient().getNazwisko() + ", ");
            System.out.print("Produkty: ");
            int counter = 0;
            for (Map.Entry<Produkt, Integer> entry : zamowienie.getProduktyIZamowienia().entrySet()) {
                Produkt produkt = entry.getKey();
                int ilosc = entry.getValue();
                System.out.print(produkt.getNazwa() + " x" + ilosc);
                counter++;
                if (counter < zamowienie.getProduktyIZamowienia().size()) {
                    System.out.print(", ");
                }
            }
            System.out.print(", Data: " + zamowienie.getDataZlozenia() + ", ");
            System.out.print("Cena Zamówienia: " + zamowienie.obliczKwoteZamowienia() + " zł, ");
            System.out.println("Status: " + zamowienie.getStatus());
        }
    }

    public boolean sprawdzKlienta(String imie, String nazwisko) {
        for (Klient klient : klienci) {
            if (klient.getImie().equalsIgnoreCase(imie) && klient.getNazwisko().equalsIgnoreCase(nazwisko)) {
                return true;
            }
        }
        return false;
    }

    public boolean sprawdzDostawcę(String nazwa) {
        for (Dostawca dostawca : dostawcy) {
            if (dostawca.getNazwa().equalsIgnoreCase(nazwa)) {
                return true;
            }
        }
        return false;
    }
}
