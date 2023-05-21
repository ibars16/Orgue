package app.david.orgue.historia;

public class Informacio {

    private String titul;
    private String descripcio;
    private int imatge;

    public Informacio(String titul, String descripcio, Integer imatge) {
        this.titul = titul;
        this.descripcio = descripcio;
        this.imatge = imatge != null ? imatge : 0;
    }

    public String getTitul() {
        return titul;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public int getImatge() {
        return imatge;
    }

}
