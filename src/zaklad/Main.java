package zaklad;

import zaklad.model.*;
import zaklad.model.produktyZakladu.*;
import zaklad.serwis.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static zaklad.model.StatusZamowienia.wyswietlStatusy;

public class Main {
    public static void main(String[] args) {
        ZakladPrzetworstwa zakład = new ZakladPrzetworstwa();
        Scanner scanner = new Scanner(System.in);

        // PRZYKLADOWE DANE DO DEBUGOWANIA:
        Klient klient01 = new Klient("Patryk", "Gorczyca", "ul. Twarożkowa", "+48 232296292");
        Dostawca dostawca01 = new Dostawca("PatrixonCorp", "ul. Sezamkowa", "232296292", "polska");
        Klient klient02 = new Klient("Piotr", "Łata", "ul. Samojaka", "+48 131231213");
        Dostawca dostawca02 = new Dostawca("KamilCO", "ul. Dziwaczna", "1231231312", "chiny");
        zakład.DodajDostawcę(dostawca01);
        zakład.DodajKlienta(klient01);
        zakład.DodajDostawcę(dostawca02);
        zakład.DodajKlienta(klient02);


        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Dodaj produkt");
            System.out.println("2. Dodaj dostawcę");
            System.out.println("3. Dodaj klienta");
            System.out.println("4. Złóż zamówienie");
            System.out.println("5. Pokaż zamówienia");
            System.out.println("6. Aktualizuj status zamówienia");
            System.out.println("7. Generuj raport");
            System.out.println("8. Oblicz zyski");
            System.out.println("9. Pokaż dostępne produkty");
            System.out.println("10. Pokaż listę klientów");
            System.out.println("11. Pokaż listę dostawców");
            System.out.println("12. Easter Egg");
            System.out.println("0. Wyjdź z programu");

            System.out.print("Wybierz opcję: ");
            int wybór = scanner.nextInt();

            switch (wybór) {
                case 1:
                    if (zakład.getListaDostawcow().isEmpty()) {
                        System.out.println("Nie ma żadnych dostawców. Najpierw dodaj dostawcę.");
                        break;
                    }

                    System.out.println("Dostępni dostawcy:");
                    for (Dostawca dostawca : zakład.getListaDostawcow()) {
                        System.out.println("ID: " + dostawca.getId() + " Nazwa: " + dostawca.getNazwa());
                    }

                    System.out.print("Podaj ID dostawcy dla produktu: ");
                    int idDostawcy = scanner.nextInt();

                    Dostawca wybranyDostawca = zakład.znajdzDostawce(idDostawcy, zakład.getListaDostawcow());
                    if (wybranyDostawca == null) {
                        System.out.println("Nie ma dostawcy o podanym ID.");
                        break;
                    }

                    System.out.print("Podaj nazwę produktu (OWOC LUB WARZYWO): ");
                    String nazwaProduktu = scanner.next().toLowerCase();

                    boolean produktIstnieje = false;
                    for (Produkt p : zakład.getProdukty()) {
                        if (p.getNazwa().equals(nazwaProduktu) && p.pobierzDostawcę().getId() == idDostawcy) {
                            p.setIlośćDostępnychSztuk(p.getIlośćDostępnychSztuk() + 1);
                            produktIstnieje = true;
                            break;
                        }
                    }

                    if (!produktIstnieje) {
                        double cenaProduktu;
                        while (true) {
                            System.out.print("Podaj cenę produktu za 1 sztukę: ");
                            if (scanner.hasNextDouble()) {
                                cenaProduktu = scanner.nextDouble();
                                break;
                            } else {
                                System.out.println("Nieprawidłowa cena produktu. Wprowadź liczbę.");
                                scanner.next();
                            }
                        }

                        String dataProdukcji, dataWażności;
                        while (true) {
                            System.out.print("Podaj datę produkcji (RRRR-MM-DD): ");
                            dataProdukcji = scanner.next();
                            System.out.print("Podaj datę ważności (RRRR-MM-DD): ");
                            dataWażności = scanner.next();

                            if (Produkt.sprawdźPoprawnośćDaty(dataProdukcji) && Produkt.sprawdźPoprawnośćDaty(dataWażności)) {
                                if (dataProdukcji.compareTo(dataWażności) <= 0) {
                                    break;
                                } else {
                                    System.out.println("Data produkcji nie może być późniejsza niż data ważności.");
                                }
                            } else {
                                System.out.println("Nieprawidłowy format daty. Poprawny format to RRRR-MM-DD.");
                            }
                        }

                        System.out.print("Podaj typ produktu (owoc/warzywo): ");
                        String typProduktu = scanner.next().toLowerCase();

                        Produkt produkt;
                        if (typProduktu.equals("owoc")) {
                            produkt = new Owoc(nazwaProduktu, cenaProduktu, dataProdukcji, dataWażności);
                        } else if (typProduktu.equals("warzywo")) {
                            produkt = new Warzywo(nazwaProduktu, cenaProduktu, dataProdukcji, dataWażności);
                        } else {
                            System.out.println("Nieprawidłowy typ produktu. Dodanie produktu przerwane.");
                            break;
                        }

                        produkt.przypiszDostawcę(wybranyDostawca);
                        zakład.DodajProdukt(produkt);
                        System.out.println("Produkt dodany.");
                    } else {
                        System.out.println("Ilość produktu zwiększona o 1.");
                    }
                    break;
                case 2:
                    System.out.print("Podaj nazwę dostawcy: ");
                    String nazwaDostawcy = scanner.next();

                    if (zakład.sprawdzDostawcę(nazwaDostawcy)) {
                        System.out.println("Dostawca o podanej nazwie już istnieje.");
                        break;
                    }

                    System.out.print("Podaj adres dostawcy: ");
                    scanner.nextLine();
                    String adresDostawcy = scanner.nextLine();

                    System.out.println("Dostępne kraje (numery kierunkowe):");
                    for (String kraj : Klient.krajeIKody.keySet()) {
                        System.out.println(kraj);
                    }
                    System.out.print("Wybierz kraj dostawcy (numer kierunkowy): ");
                    String krajDostawcy = scanner.next();

                    boolean dodanoDostawce = false;

                    do {
                        System.out.print("Podaj numer kontaktowy dostawcy: ");
                        String numerKontaktowyDostawcy = scanner.next();

                        int maksymalnaDlugosc = Klient.maksymalnaDlugoscNumeru(krajDostawcy);
                        if (maksymalnaDlugosc != 0 && numerKontaktowyDostawcy.length() > maksymalnaDlugosc) {
                            System.out.println("Numer kontaktowy jest zbyt długi dla wybranego kraju.");
                            System.out.println("Maksymalna długość numeru dla tego kraju to: " + maksymalnaDlugosc);
                            System.out.println("Proszę wprowadzić ponownie numer telefonu ");
                        } else {
                            Dostawca dostawca = new Dostawca(nazwaDostawcy, adresDostawcy, numerKontaktowyDostawcy, krajDostawcy);
                            zakład.DodajDostawcę(dostawca);
                            System.out.println("Dostawca dodany.");
                            dodanoDostawce = true;
                        }
                    } while (!dodanoDostawce);
                    break;
                case 3:
                    boolean dodanoKlienta = false;

                    System.out.print("Podaj imię klienta: ");
                    String imięKlienta = scanner.next();
                    System.out.print("Podaj nazwisko klienta: ");
                    String nazwiskoKlienta = scanner.next();

                    if (zakład.sprawdzKlienta(imięKlienta, nazwiskoKlienta)) {
                        System.out.println("Klient o podanych danych już istnieje.");
                        break;
                    }

                    System.out.print("Podaj adres klienta: ");
                    scanner.nextLine();
                    String adresKlienta = scanner.nextLine();

                    System.out.println("Dostępne kraje:");
                    for (String kraj : Klient.krajeIKody.keySet()) {
                        System.out.println(kraj);
                    }
                    System.out.print("Wybierz kraj klienta: ");
                    String krajKlienta = scanner.next();

                    do {
                        System.out.print("Podaj numer kontaktowy klienta: ");
                        String numerKontaktowyKlienta = scanner.next();

                        int maksymalnaDlugosc = Klient.maksymalnaDlugoscNumeru(krajKlienta);
                        if (maksymalnaDlugosc != 0 && numerKontaktowyKlienta.length() > maksymalnaDlugosc) {
                            System.out.println("Numer kontaktowy jest zbyt długi dla wybranego kraju.");
                            System.out.println("Maksymalna długość numeru dla tego kraju to: " + maksymalnaDlugosc);
                            System.out.println("Proszę wprowadzić ponownie numer telefonu ");
                        } else {
                            Klient klient = new Klient(imięKlienta, nazwiskoKlienta, adresKlienta, numerKontaktowyKlienta);
                            klient.setNumerKontaktowy(numerKontaktowyKlienta, krajKlienta);
                            zakład.DodajKlienta(klient);
                            System.out.println("Klient dodany.");
                            dodanoKlienta = true;
                        }
                    } while (!dodanoKlienta);
                    break;
                case 4:
                    if (zakład.getKlienci().isEmpty()) {
                        System.out.println("Brak dostępnych klientów. Dodaj klienta, aby kontynuować.");
                        break;
                    }

                    if (zakład.getProdukty().isEmpty()) {
                        System.out.println("Brak dostępnych produktów. Powrót do menu.");
                        break;
                    }

                    System.out.println("Dostępni klienci:");
                    for (Klient klient : zakład.getKlienci()) {
                        System.out.println("ID: " + klient.getKlientId() + ", Imię i nazwisko: " + klient.getImie() + " " + klient.getNazwisko());
                    }

                    System.out.print("Podaj ID klienta, który składa zamówienie: ");
                    int idKlienta = scanner.nextInt();

                    Klient wybranyKlient = null;
                    for (Klient klient : zakład.getKlienci()) {
                        if (klient.getKlientId() == idKlienta) {
                            wybranyKlient = klient;
                            break;
                        }
                    }

                    if (wybranyKlient == null) {
                        System.out.println("Nie ma klienta o podanym ID.");
                        break;
                    }

                    Zamowienie noweZamowienie = new Zamowienie(wybranyKlient);
                    do {
                        if (zakład.getProdukty().isEmpty()) {
                            System.out.println("Brak dostępnych produktów. Powrót do menu.");
                            break;
                        }

                        System.out.println("Dostępne produkty:");
                        for (Produkt produkt : zakład.getProdukty()) {
                            System.out.println("ID: " + produkt.getId() +
                                    ", Nazwa: " + produkt.getNazwa() +
                                    ", Typ: " + (produkt instanceof Owoc ? "Owoc" : "Warzywo") +
                                    ", Cena za 1 sztukę: " + produkt.getCena() +
                                    "zł, Data produkcji: " + produkt.getDataProdukcji() +
                                    ", Data ważności: " + produkt.getDataWażności() +
                                    ", Ilość dostępnych sztuk: " + produkt.getIlośćDostępnychSztuk() +
                                    ", Dostawca: " + produkt.pobierzDostawcę().getNazwa());
                        }

                        System.out.print("Podaj ID produktu do zamówienia: ");
                        int idProduktu = scanner.nextInt();

                        Produkt wybranyProdukt = null;
                        for (Produkt produkt : zakład.getProdukty()) {
                            if (produkt.getId() == idProduktu) {
                                wybranyProdukt = produkt;
                                break;
                            }
                        }

                        if (wybranyProdukt == null) {
                            System.out.println("Nie ma produktu o podanym ID.");
                            continue;
                        }

                        int ilosc = 0;
                        boolean poprawnaIlosc = false;
                        do {
                            System.out.print("Podaj ilość produktu '" + wybranyProdukt.getNazwa() + "': ");
                            ilosc = scanner.nextInt();

                            if (ilosc < 0 || ilosc > wybranyProdukt.getIlośćDostępnychSztuk()) {
                                System.out.println("Nieprawidłowa ilość.");
                            } else {
                                poprawnaIlosc = true;
                            }
                        } while (!poprawnaIlosc);

                        if (poprawnaIlosc) {
                            noweZamowienie.dodajPozycjeZamowienia(wybranyProdukt, ilosc);
                            wybranyProdukt.setIlośćDostępnychSztuk(wybranyProdukt.getIlośćDostępnychSztuk() - ilosc);

                            System.out.print("Czy chcesz dodać kolejny produkt? (T/N): ");
                            String kontynuacja = scanner.next().toUpperCase();
                            if (!kontynuacja.equals("T")) {
                                zakład.PrzyjmijZamówienie(noweZamowienie);
                                System.out.println("Zamówienie złożone.");
                                System.out.println("Łączna kwota zamówienia: " + noweZamowienie.obliczKwoteZamowienia() + " zł");
                                break;
                            }
                        }
                    } while (true);
                    break;
                case 5:
                    System.out.println("Lista wszystkich zamówień:");
                    zakład.wyswietlZamowienia();
                    break;
                case 6:
                    System.out.println("Aktualizacja statusu zamówienia");
                    if (zakład.getZamowienia().isEmpty()) {
                        System.out.println("Brak zamówień do aktualizacji.");
                        break;
                    }

                    System.out.println("Dostępne zamówienia:");
                    zakład.wyswietlZamowienia();

                    System.out.print("Podaj ID zamówienia do aktualizacji statusu: ");
                    int idZamowienia = scanner.nextInt();

                    Zamowienie wybraneZamowienie = null;
                    for (Zamowienie zamowienie : zakład.getZamowienia()) {
                        if (zamowienie.getId() == idZamowienia) {
                            wybraneZamowienie = zamowienie;
                            break;
                        }
                    }

                    if (wybraneZamowienie != null) {
                        StatusZamowienia.wyswietlStatusy();
                        System.out.print("Wybierz nowy status zamówienia (1-4): ");
                        int wybranyStatus = scanner.nextInt();

                        switch (wybranyStatus) {
                            case 1:
                                zakład.AktualizujStatusZamówienia(wybraneZamowienie, StatusZamowienia.NOWE);
                                break;
                            case 2:
                                zakład.AktualizujStatusZamówienia(wybraneZamowienie, StatusZamowienia.W_REALIZACJI);
                                break;
                            case 3:
                                zakład.AktualizujStatusZamówienia(wybraneZamowienie, StatusZamowienia.DOSTARCZONE);
                                break;
                            case 4:
                                zakład.AktualizujStatusZamówienia(wybraneZamowienie, StatusZamowienia.ANULOWANE);
                                break;
                            default:
                                System.out.println("Nieprawidłowy wybór.");
                        }
                    } else {
                        System.out.println("Nie ma zamówienia o podanym ID.");
                    }
                    break;
                case 7:
                    int liczbaKlientow = zakład.getKlienci().size();
                    int liczbaDostawcow = zakład.getDostawcy().size();
                    int liczbaZamowien = zakład.getZamowienia().size();
                    int liczbaProduktow = zakład.getProdukty().size();

                    System.out.println("===================================");
                    System.out.println("            Raport");
                    System.out.println("===================================");
                    System.out.println(String.format("%-25s %s", "Liczba klientów:", liczbaKlientow));
                    System.out.println(String.format("%-25s %s", "Liczba dostawców:", liczbaDostawcow));
                    System.out.println(String.format("%-25s %s", "Liczba zamówień:", liczbaZamowien));
                    System.out.println(String.format("%-25s %s", "Liczba produktów:", liczbaProduktow));
                    System.out.println("===================================");
                    break;
                case 8:
                    double sumaKwotZamowien = 0;

                    for (Zamowienie zamowienie : zakład.getZamowienia()) {
                        sumaKwotZamowien += zamowienie.obliczKwoteZamowienia();
                    }

                    System.out.println("Łączna kwota wszystkich zamówień: " + sumaKwotZamowien + " zł");
                    break;
                case 9:
                    if (zakład.getProdukty().isEmpty()) {
                        System.out.println("Nie posiadamy produktów.");
                    } else {
                        System.out.println("Lista produktów:");
                        for (Produkt produkt : zakład.getProdukty()) {
                            Dostawca dostawcaProduktu = produkt.pobierzDostawcę();
                            if (dostawcaProduktu != null && dostawcaProduktu.getId() == produkt.pobierzDostawcę().getId()) {
                                System.out.println("ID: " + produkt.getId() +
                                        ", Nazwa: " + produkt.getNazwa() +
                                        ", Typ: " + (produkt instanceof Owoc ? "Owoc" : "Warzywo") +
                                        ", Cena za 1 sztukę: " + produkt.getCena() +
                                        "zł, Data produkcji: " + produkt.getDataProdukcji() +
                                        ", Data ważności: " + produkt.getDataWażności() +
                                        ", Ilość dostępnych sztuk: " + produkt.getIlośćDostępnychSztuk() +
                                        ", Dostawca: " + produkt.pobierzDostawcę().getNazwa());
                            } else {
                                System.out.println("Nie znaleziono dostawcy dla produktu: " + produkt.getNazwa());
                            }
                        }
                    }
                    break;
                case 10:
                    List<Klient> klienci = zakład.getKlienci();
                    if (klienci.isEmpty()) {
                        System.out.println("Nie posiadamy żadnych klientów.");
                    } else {
                        System.out.println("Lista klientów:");
                        for (Klient klient : klienci) {
                            System.out.println("ID: " + klient.getKlientId() +
                                    ", Imię: " + klient.getImie() +
                                    ", Nazwisko: " + klient.getNazwisko() +
                                    ", Adres: " + klient.getAdres() +
                                    ", Numer kontaktowy: " + klient.getNumerKontaktowy());
                        }
                    }
                    break;
                case 11:
                    List<Dostawca> dostawcy = zakład.getDostawcy();
                    if (dostawcy.isEmpty()) {
                        System.out.println("Nie posiadamy żadnych dostawców.");
                    } else {
                        System.out.println("Lista dostawców:");
                        for (Dostawca dostawca : dostawcy) {
                            System.out.println("Nazwa: " + dostawca.getNazwa() +
                                    ", Adres: " + dostawca.getAdres() +
                                    ", Numer kontaktowy: " + dostawca.getNumerKontaktowy());
                        }
                    }
                    break;
                case 12:
                        System.out.println("Czacha już mi dymi.");
                        Dodatek.odpalZdjecie("palisie.jpg");
                    break;
                case 0:
                    System.out.println("Dziękujemy za skorzystanie z programu.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
            }
        }
    }
}