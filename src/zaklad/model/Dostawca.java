package zaklad.model;

public class Dostawca {
    private String nazwa;
    private String adres;
    private String numerKontaktowy;

    public Dostawca(String nazwa, String adres, String numerKontaktowy) {
        this.nazwa = nazwa;
        this.adres = adres;
        this.numerKontaktowy = numerKontaktowy;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getAdres() {
        return adres;
    }

    public String getNumerKontaktowy() {
        return numerKontaktowy;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setNumerKontaktowy(String numerKontaktowy) {
        this.numerKontaktowy = numerKontaktowy;
    }
}

