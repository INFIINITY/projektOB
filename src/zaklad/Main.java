package zaklad;

import zaklad.model.*;
import zaklad.model.produktyZakladu.*;
import zaklad.serwis.*;

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
                    System.out.print("Podaj adres dostawcy: ");
                    String adresDostawcy = scanner.next();
                    System.out.print("Podaj numer kontaktowy dostawcy: ");
                    String numerKontaktowyDostawcy = scanner.next();

                    Dostawca dostawca = new Dostawca(nazwaDostawcy, adresDostawcy, numerKontaktowyDostawcy);
                    zakład.DodajDostawcę(dostawca);
                    System.out.println("Dostawca dodany.");
                    break;
                case 3:
                    // Dodawanie klienta
                    System.out.print("Podaj imię klienta: ");
                    String imięKlienta = scanner.next();
                    System.out.print("Podaj nazwisko klienta: ");
                    String nazwiskoKlienta = scanner.next();
                    System.out.print("Podaj adres klienta: ");
                    String adresKlienta = scanner.next();
                    System.out.print("Podaj numer kontaktowy klienta: ");
                    String numerKontaktowyKlienta = scanner.next();

                    Klient klient = new Klient(imięKlienta, nazwiskoKlienta, adresKlienta, numerKontaktowyKlienta);
                    zakład.DodajKlienta(klient);
                    System.out.println("Klient dodany.");
                    break;
                case 4:
                    // Składanie zamówienia
                    Klient wybranyKlient = null;

                    if (zakład.getKlienci().isEmpty()) {
                        System.out.println("Brak dostępnych klientów. Czy chcesz stworzyć konto klienta? (tak/nie)");
                        String odpowiedź = scanner.next();

                        if (odpowiedź.equalsIgnoreCase("tak")) {
                            System.out.print("Podaj imię klienta: ");
                            String noweImięKlienta = scanner.next();
                            System.out.print("Podaj nazwisko klienta: ");
                            String noweNazwiskoKlienta = scanner.next();
                            System.out.print("Podaj adres klienta: ");
                            String nowyAdresKlienta = scanner.next();
                            System.out.print("Podaj numer kontaktowy klienta: ");
                            String nowyNumerKontaktowyKlienta = scanner.next();

                            Klient nowyKlient = new Klient(noweImięKlienta, noweNazwiskoKlienta, nowyAdresKlienta, nowyNumerKontaktowyKlienta);
                            zakład.DodajKlienta(nowyKlient);
                            System.out.println("Nowy profil klienta utworzony.");
                            wybranyKlient = nowyKlient;
                        } else {
                            System.out.println("Nie utworzono zamówienia. Brak dostępnych klientów.");
                            break;
                        }
                    } else {
                        System.out.println("Dostępni klienci:");
                        for (Klient klient2 : zakład.getKlienci()) {
                            System.out.println(klient2.getImie() + " " + klient2.getNazwisko());
                        }

                        System.out.print("Podaj imię i nazwisko klienta lub 'nowy' aby utworzyć nowe konto: ");
                        String imięNazwisko = scanner.next();

                        if (imięNazwisko.equalsIgnoreCase("nowy")) {
                            System.out.print("Podaj imię klienta: ");
                            String noweImięKlienta = scanner.next();
                            System.out.print("Podaj nazwisko klienta: ");
                            String noweNazwiskoKlienta = scanner.next();
                            System.out.print("Podaj adres klienta: ");
                            String nowyAdresKlienta = scanner.next();
                            System.out.print("Podaj numer kontaktowy klienta: ");
                            String nowyNumerKontaktowyKlienta = scanner.next();

                            Klient nowyKlient = new Klient(noweImięKlienta, noweNazwiskoKlienta, nowyAdresKlienta, nowyNumerKontaktowyKlienta);
                            zakład.DodajKlienta(nowyKlient);
                            System.out.println("Nowy profil klienta utworzony.");
                            wybranyKlient = nowyKlient;
                        } else {
                            for (Klient klient3 : zakład.getKlienci()) {
                                if ((klient3.getImie() + " " + klient3.getNazwisko()).equalsIgnoreCase(imięNazwisko)) {
                                    wybranyKlient = klient3;
                                    break;
                                }
                            }

                            if (wybranyKlient == null) {
                                System.out.println("Klient o podanym imieniu i nazwisku nie istnieje.");
                                break;
                            }
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