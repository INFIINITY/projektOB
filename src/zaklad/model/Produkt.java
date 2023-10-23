package zaklad.model;

public class Produkt {
    private String nazwa;
    private double cena;
    private int ilośćDostępnychSztuk;
    private String dataProdukcji;
    private String dataWażności;

    public Produkt(String nazwa, double cena, int ilośćDostępnychSztuk, String dataProdukcji, String dataWażności) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.ilośćDostępnychSztuk = ilośćDostępnychSztuk;
        this.dataProdukcji = dataProdukcji;
        this.dataWażności = dataWażności;
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

    public void dodajSztuki(int ilość) {
        this.ilośćDostępnychSztuk += ilość;
    }

    public void odejmijSztuki(int ilość) {
        if (ilośćDostępnychSztuk >= ilość) {
            this.ilośćDostępnychSztuk -= ilość;
        } else {
            System.out.println("Brak wystarczającej ilości produktu.");
        }
    }

}


