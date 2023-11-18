package zaklad.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Produkt {
    private static int kolejneID = 1;
    private int id; // Dodane pole ID dla produktu
    private String nazwa;
    private double cena;
    private int ilośćDostępnychSztuk;
    private String dataProdukcji;
    private String dataWażności;
    private Dostawca dostawcaProduktu; // Dodane pole przechowujące informację o dostawcy

    public Produkt(String nazwa, double cena, String dataProdukcji, String dataWażności) {
        this.id = kolejneID++;
        this.nazwa = nazwa;
        this.cena = cena;
        this.ilośćDostępnychSztuk = 1;
        this.dataProdukcji = dataProdukcji;
        this.dataWażności = dataWażności;
    }

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getCena() {
        return cena;
    }

    public int getIlośćDostępnychSztuk() {
        return ilośćDostępnychSztuk;
    }

    public void setIlośćDostępnychSztuk(int ilośćDostępnychSztuk) {
        this.ilośćDostępnychSztuk = ilośćDostępnychSztuk;
    }

    public String getDataProdukcji() {
        return dataProdukcji;
    }

    public String getDataWażności() {
        return dataWażności;
    }

    public void dodajSztukę() {
        this.ilośćDostępnychSztuk++;
    }

    public void odejmijSztukę() {
        if (ilośćDostępnychSztuk > 0) {
            this.ilośćDostępnychSztuk--;
        } else {
            System.out.println("Brak dostępnych sztuk produktu.");
        }
    }

    public static boolean sprawdźPoprawnośćDaty(String data) {
        if (data.matches("\\d{4}-\\d{2}-\\d{2}")) {
            try {
                LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    // Dodana metoda do przypisywania dostawcy do produktu
    public void przypiszDostawcę(Dostawca dostawca) {
        this.dostawcaProduktu = dostawca;
    }

    // Dodana metoda do pobierania dostawcy produktu
    public Dostawca pobierzDostawcę() {
        return dostawcaProduktu;
    }
}
