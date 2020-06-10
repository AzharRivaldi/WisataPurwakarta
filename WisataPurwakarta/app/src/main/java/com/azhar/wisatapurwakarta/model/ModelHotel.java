package com.azhar.wisatapurwakarta.model;

import java.io.Serializable;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class ModelHotel implements Serializable {

    private String txtNamaHotel, txtAlamatHotel, txtNoTelp, Koordinat, GambarHotel;

    public String getTxtNamaHotel() {
        return txtNamaHotel;
    }

    public void setTxtNamaHotel(String txtNamaHotel) {
        this.txtNamaHotel = txtNamaHotel;
    }

    public String getTxtAlamatHotel() {
        return txtAlamatHotel;
    }

    public void setTxtAlamatHotel(String txtAlamatHotel) {
        this.txtAlamatHotel = txtAlamatHotel;
    }

    public String getTxtNoTelp() {
        return txtNoTelp;
    }

    public void setTxtNoTelp(String txtNoTelp) {
        this.txtNoTelp = txtNoTelp;
    }

    public String getKoordinat() {
        return Koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.Koordinat = koordinat;
    }

    public String getGambarHotel() {
        return GambarHotel;
    }

    public void setGambarHotel(String gambarHotel) {
        this.GambarHotel = gambarHotel;
    }
}
