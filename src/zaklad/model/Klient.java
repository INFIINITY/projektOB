package zaklad.model;

import java.util.HashMap;
import java.util.Map;

public class Klient {
    private int klientId;
    private String imie;
    private String nazwisko;
    private String adres;
    private String numerKontaktowy;

    private static int kolejneId = 1;

    public static final Map<String, String> krajeIKody = new HashMap<>();

    static {
        krajeIKody.put("polska", "+48");
        krajeIKody.put("chiny", "+86");
        krajeIKody.put("inny_kraj", "kod_kraju");
    }

    public Klient(String imie, String nazwisko, String adres, String numerKontaktowy) {
        this.klientId = kolejneId++;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.setNumerKontaktowy(numerKontaktowy, "");
    }

    public int getKlientId() {
        return klientId;
    }

    public void setKlientId(int klientId) {
        this.klientId = klientId;
    }

    public void setNumerKontaktowy(String numerKontaktowy, String kraj) {
        if (!kraj.isEmpty() && krajeIKody.containsKey(kraj.toLowerCase())) {
            String kodKraju = krajeIKody.get(kraj.toLowerCase());
            this.numerKontaktowy = kodKraju + " " + numerKontaktowy;
        } else {
            this.numerKontaktowy = numerKontaktowy;
        }
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
        if (numerKontaktowy.startsWith("+")) {
            return numerKontaktowy;
        } else {
            return krajeIKody.getOrDefault(numerKontaktowy.toLowerCase(), "") + " " + numerKontaktowy;
        }
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

    public static int maksymalnaDlugoscNumeru(String kraj) {
        if (krajeIKody.containsKey(kraj.toLowerCase())) {
            String kodKraju = krajeIKody.get(kraj.toLowerCase());
            switch (kodKraju) {
                case "+48":
                    return 9;
                case "+86":
                    return 11;
                case "kod_kraju":
                    return 15;
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }
}
