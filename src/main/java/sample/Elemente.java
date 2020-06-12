package sample;

public class Elemente {
    private String detalii, status;

    public Elemente(String detalii, String status) {
        this.detalii = detalii;
        this.status = status;
    }

    public String getDetalii() {
        return detalii;
    }

    public String getStatus() {
        return status;
    }

    public void setDetalii(String detalii) {
        this.detalii = detalii;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
