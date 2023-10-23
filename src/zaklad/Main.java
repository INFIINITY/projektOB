package zaklad;

import java.util.Scanner;

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
                    // ...
                    break;
                case 2:
                    // Dodawanie dostawcy
                    // ...
                    break;
                case 3:
                    // Dodawanie klienta
                    // ...
                    break;
                case 4:
                    // Składanie zamówienia
                    // ...
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
