package zaklad.model;

public enum StatusZamowienia {
    NOWE,
    W_REALIZACJI,
    DOSTARCZONE,
    ANULOWANE;

    public static void wyswietlStatusy() {
        System.out.println("Dostępne statusy zamówienia:");
        for (StatusZamowienia status : StatusZamowienia.values()) {
            System.out.println((status.ordinal() + 1) + ". " + status);
        }
    }
}
