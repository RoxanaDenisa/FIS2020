package sample;

import javafx.scene.layout.HBox;

public class Items {
    private String date;
    private HBox hb;

    public Items(String date, HBox hb) {
        this.date = date;
        this.hb = hb;
    }

    public String getDate() {
        return date;
    }

    public HBox getHb() {
        return hb;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVb(HBox vb) {
        this.hb = hb;
    }
}
