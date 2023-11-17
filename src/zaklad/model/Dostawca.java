package zaklad.model;

public class Dostawca {
    private static int aktualneID = 1;
    private int id;
    private String nazwa;
    private String adres;
    private String numerKontaktowy;

    public Dostawca(String nazwa, String adres, String numerKontaktowy, String kraj) {
        this.id = aktualneID++;
        this.nazwa = nazwa;
        this.adres = adres;
        if (Klient.krajeIKody.containsKey(kraj.toLowerCase())) {
            String kodKraju = Klient.krajeIKody.get(kraj.toLowerCase());
            this.numerKontaktowy = kodKraju + " " + numerKontaktowy;
        } else {
            this.numerKontaktowy = numerKontaktowy;
        }
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getAdres() {
        return adres;
    }

    public String getNumerKontaktowy() {
        if (numerKontaktowy.startsWith("+")) {
            return numerKontaktowy;
        } else {
            return Klient.krajeIKody.getOrDefault(numerKontaktowy.toLowerCase(), "") + " " + numerKontaktowy;
        }
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

