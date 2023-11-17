package zaklad;

import zaklad.model.*;
import zaklad.model.produktyZakladu.*;
import zaklad.serwis.*;

import java.util.List;
import java.util.Scanner;

import static zaklad.model.Produkt.sprawdźPoprawnośćDaty;

public class Main {
    public static void main(String[] args) {
        ZakladPrzetworstwa zakład = new ZakladPrzetworstwa();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Dodaj produkt");
            System.out.println("2. Dodaj dostawcę");
            System.out.println("3. Dodaj klienta");
            System.out.println("4. Złóż zamówienie");
            System.out.println("5. Aktualizuj status zamówienia");
            System.out.println("6. Generuj raport");
            System.out.println("7. Oblicz zyski");
            System.out.println("8. Pokaż dostępne produkty");
            System.out.println("9. Pokaż listę klientów");
            System.out.println("10. Pokaż listę dostawców");
            System.out.println("0. Wyjdź z programu");

            System.out.print("Wybierz opcję: ");
            int wybór = scanner.nextInt();

            switch (wybór) {
                case 1:
                    // Dodawanie produktu
                    System.out.print("Podaj nazwę produktu (OWOC LUB WARZYWO): ");
                    String nazwaProduktu = scanner.next().toLowerCase();

                    // Sprawdź, czy produkt już istnieje
                    boolean produktIstnieje = false;
                    for (Produkt p : zakład.getProdukty()) {
                        if (p.getNazwa().equals(nazwaProduktu)) {
                            p.setIlośćDostępnychSztuk(p.getIlośćDostępnychSztuk() + 1);
                            produktIstnieje = true;
                            break;
                        }
                    }

                    // Jeśli produkt nie istnieje, dodaj nowy
                    if (!produktIstnieje) {
                        double cenaProduktu;
                        while (true) {
                            System.out.print("Podaj cenę produktu za 1kg: ");
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

                        zakład.DodajProdukt(produkt);
                        System.out.println("Produkt dodany.");
                    } else {
                        System.out.println("Ilość produktu zwiększona o 1.");
                    }
                    break;
                case 2:
                    // Dodawanie dostawcy
                    System.out.print("Podaj nazwę dostawcy: ");
                    String nazwaDostawcy = scanner.next();

                    // Sprawdzenie istnienia dostawcy o podanej nazwie
                    if (zakład.sprawdzDostawcę(nazwaDostawcy)) {
                        System.out.println("Dostawca o podanej nazwie już istnieje.");
                        break;
                    }

                    System.out.print("Podaj adres dostawcy: ");
                    scanner.nextLine(); // Czyść bufor przed wprowadzeniem adresu
                    String adresDostawcy = scanner.nextLine();

                    // Wyświetlanie dostępnych krajów (numery kierunkowe)
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

                    // Sprawdzenie istnienia klienta o podanych imieniu i nazwisku
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
                    Klient wybranyKlient = null;

                    if (zakład.getKlienci().isEmpty()) {
                        System.out.println("Brak dostępnych klientów. Proszę utworzyć konto klienta.");
                        break; // Powrót do głównego menu
                    } else {
                        System.out.println("Dostępni klienci:");
                        for (Klient klient2 : zakład.getKlienci()) {
                            System.out.println("ID: " + klient2.getKlientId() + ", " + klient2.getImie() + " " + klient2.getNazwisko());
                        }

                        System.out.print("Podaj ID klienta: ");
                        int clientId = scanner.nextInt();

                        boolean klientExists = false;
                        for (Klient klient : zakład.getKlienci()) {
                            if (klient.getKlientId() == clientId) {
                                wybranyKlient = klient;
                                klientExists = true;
                                break;
                            }
                        }

                        if (!klientExists) {
                            System.out.println("Klient o podanym ID nie istnieje.");
                            break;
                        }
                    }

                    Zamowienie noweZamowienie = new Zamowienie(wybranyKlient);
                    zakład.PrzyjmijZamówienie(noweZamowienie);
                    System.out.println("Zamówienie zostało złożone.");
                    break;
                case 5:
                    // Aktualizacja statusu zamówienia
                    // ...
                    break;
                case 6:
                    // Generowanie raportu
                    // ...
                    break;
                case 7:
                    // Obliczanie zysków
                    // ...
                    break;
                case 8:
                    // Wyświetlanie produktów
                    if (zakład.getProdukty().isEmpty()) {
                        System.out.println("Nie posiadamy produktów.");
                    } else {
                        System.out.println("Lista produktów:");
                        for (Produkt produktowocwarzywo : zakład.getProdukty()) {
                            System.out.println("Nazwa: " + produktowocwarzywo.getNazwa() +
                                    ", Typ: " + (produktowocwarzywo instanceof Owoc ? "Owoc" : "Warzywo") +
                                    ", Cena za 1kg: " + produktowocwarzywo.getCena() +
                                    "zł, Data produkcji: " + produktowocwarzywo.getDataProdukcji() +
                                    ", Data ważności: " + produktowocwarzywo.getDataWażności() +
                                    ", Ilość dostępnych sztuk: " + produktowocwarzywo.getIlośćDostępnychSztuk());
                        }
                    }
                    break;
                case 9:
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
                case 10:
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