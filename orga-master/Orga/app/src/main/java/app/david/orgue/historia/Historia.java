package app.david.orgue.historia;

public class Historia {

    private String titul;
    private String descripcio;
    private int imatge;

    public Historia(String titul, String descripcio, int imatge) {
        this.titul = titul;
        this.descripcio = descripcio;
        this.imatge = imatge;
    }

    public String getTitul() {
        return titul;
    }

    public void setTitul(String titul) {
        this.titul = titul;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getImatge() {
        return imatge;
    }

    public void setImatge(int imatge) {
        this.imatge = imatge;
    }
}
