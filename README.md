# 🚀 Projekt Zarządzania Zakładem Przetwórstwa 🌟

Autor: [Patryk Gorczyca]
Data: [18.11.2023]
Wersja Javy: [JAVA SDK 20.0.2 Amazon Corretto]

---

## 📚 Spis Treści
1. [Główne Cele Projektu](#główne-cele-projektu)
2. [Kluczowe Klasy i Metody](#kluczowe-klasy-i-metody)
3. [Szczegóły Funkcjonalne](#szczegóły-funkcjonalne)
4. [Wykorzystane Technologie](#wykorzystane-technologie)
5. [Licencja](#licencja)

---

## 🌟 Główne Cele Projektu

### 📦 Zarządzanie Dostawcami i Klientami:
- Dodawanie, sprawdzanie istnienia oraz aktualizacja danych dostawców i klientów.
- Przechowywanie informacji o nazwie, adresie, numerze kontaktowym, kraju.

### 🌽 Zarządzanie Produktami:
- Dodawanie produktów - zarówno owoców, jak i warzyw - z ich szczegółami (nazwa, cena, data produkcji, ważności, dostępność).
- Przypisywanie produktów do odpowiednich dostawców.

### 📦 Zarządzanie Zamówieniami:
- Składanie zamówień przez klientów - wybór produktów, ilości i proces zamówienia.
- Aktualizacja statusów zamówień - od nowego zamówienia po dostarczone lub anulowane.
- Generowanie raportów - podsumowanie liczby klientów, dostawców, zamówień i produktów.

---

## 🌟 Kluczowe Klasy i Metody
### Klasy:
- ZakladPrzetworstwa: Centralna klasa zarządzająca dostawcami, klientami, produktami i zamówieniami.
- Klient, Dostawca: Klasa reprezentująca dane o klientach i dostawcach.
- Produkt, Owoc, Warzywo: Klasy związane z produktami, ich typem (owoc/warzywo) i szczegółami.
- Zamowienie: Przechowuje informacje o zamówieniach.

### Metody:
- Dodawanie (DodajDostawcę, DodajKlienta, DodajProdukt), sprawdzanie (sprawdzDostawcę, sprawdzKlienta) i aktualizacja danych.
- Obsługa zamówień (składanieZamówienia), aktualizacja statusów zamówień (AktualizujStatusZamówienia), generowanie raportów (raport).
- Interakcja z użytkownikiem za pomocą interaktywnego menu (main()).

---

## 🌟 Szczegóły Funkcjonalne

### 📦 Dodawanie Dostawców i Klientów:
- Umożliwia dodawanie nowych dostawców i klientów, weryfikację ich istnienia i aktualizację danych kontaktowych.

### 🌽 Zarządzanie Produktami:
- Dodawanie owoców i warzyw z informacjami o cenie, dacie produkcji, ważności.
- Przypisywanie produktów do odpowiednich dostawców.

### 📦 Obsługa Zamówień:
- Składanie zamówień przez klientów poprzez wybór produktów, ich ilości i proces zamówienia.
- Aktualizacja statusów zamówień: od nowych zamówień po dostarczone lub anulowane.
- Generowanie raportów podsumowujących kluczowe statystyki dotyczące działalności zakładu.

---

## 🌟 Wykorzystane Technologie
Projekt oparty jest na języku Java. Nie wykorzystuje zewnętrznych bibliotek czy narzędzi, koncentrując się na podstawowych mechanizmach tego języka.

---

## 🚀 Licencja
Bierzcie i korzystajcie z tego wszyscy 🌟

---

![Obrazek owoców](https://freepngimg.com/download/fruit/174275-fresh-fruits-free-hd-image.png)
