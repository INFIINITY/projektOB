package zaklad.model;

public class PozycjaZamowienia {
    private Produkt produkt;
    private int ilość;

    public PozycjaZamowienia(Produkt produkt, int ilość) {
        this.produkt = produkt;
        this.ilość = ilość;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public int getIlość() {
        return ilość;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public void setIlość(int ilość) {
        this.ilość = ilość;
    }
}

