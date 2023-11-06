package zaklad;

import zaklad.model.*;
import zaklad.serwis.*;

import java.util.Scanner;

import static zaklad.model.Produkt.sprawdźPoprawnośćDaty;

public class Main {
    public static void main(String[] args) {
        ZakładPrzetwórstwa zakład = new ZakładPrzetwórstwa();
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
            System.out.println("0. Wyjdź z programu");

            System.out.print("Wybierz opcję: ");
            int wybór = scanner.nextInt();

            switch (wybór) {
                case 1:
                    // Dodawanie produktu
                    System.out.print("Podaj nazwę produktu: ");
                    String nazwaProduktu = scanner.next();
                    double cenaProduktu;
                    while (true) {
                        System.out.print("Podaj cenę produktu: ");
                        if (scanner.hasNextDouble()) {
                            cenaProduktu = scanner.nextDouble();
                            break;
                        } else {
                            System.out.println("Nieprawidłowa cena produktu. Wprowadź liczbę.");
                            scanner.next();
                        }
                    }
                    System.out.print("Podaj ilość dostępnych sztuk: ");
                    int ilośćSztuk = scanner.nextInt();
                    String dataProdukcji, dataWażności;
                    while (true) {
                        System.out.print("Podaj datę produkcji (RRRR-MM-DD): ");
                        dataProdukcji = scanner.next();
                        System.out.print("Podaj datę ważności (RRRR-MM-DD): ");
                        dataWażności = scanner.next();

                        if (sprawdźPoprawnośćDaty(dataProdukcji) && sprawdźPoprawnośćDaty(dataWażności)) {
                            if (dataProdukcji.compareTo(dataWażności) <= 0) {
                                break;
                            } else {
                                System.out.println("Data produkcji nie może być późniejsza niż data ważności.");
                            }
                        } else {
                            System.out.println("Nieprawidłowy format daty. Poprawny format to RRRR-MM-DD.");
                        }
                    }
                    Produkt produkt = new Produkt(nazwaProduktu, cenaProduktu, ilośćSztuk, dataProdukcji, dataWażności);
                    zakład.DodajProdukt(produkt);
                    System.out.println("Produkt dodany.");
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