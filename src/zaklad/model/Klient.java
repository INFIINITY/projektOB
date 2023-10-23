package zaklad.model;

public class Klient {
    private String imie;
    private String nazwisko;
    private String adres;
    private String numerKontaktowy;

    public Klient(String imie, String nazwisko, String adres, String numerKontaktowy) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.numerKontaktowy = numerKontaktowy;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public String getNumerKontaktowy() {
        return numerKontaktowy;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setNumerKontaktowy(String numerKontaktowy) {
        this.numerKontaktowy = numerKontaktowy;
    }
}

