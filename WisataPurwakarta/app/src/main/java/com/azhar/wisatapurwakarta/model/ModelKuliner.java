package com.azhar.wisatapurwakarta.model;

import java.io.Serializable;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class ModelKuliner implements Serializable {

    private String idKuliner, txtNamaKuliner, txtAlamatKuliner, txtOpenTime, Koordinat, GambarKuliner, KategoriKuliner;

    public String getIdKuliner() {
        return idKuliner;
    }

    public void setIdKuliner(String idKuliner) {
        this.idKuliner = idKuliner;
    }

    public String getTxtNamaKuliner() {
        return txtNamaKuliner;
    }

    public void setTxtNamaKuliner(String txtNamaKuliner) {
        this.txtNamaKuliner = txtNamaKuliner;
    }

    public String getTxtAlamatKuliner() {
        return txtAlamatKuliner;
    }

    public void setTxtAlamatKuliner(String txtAlamatKuliner) {
        this.txtAlamatKuliner = txtAlamatKuliner;
    }

    public String getTxtOpenTime() {
        return txtOpenTime;
    }

    public void setTxtOpenTime(String txtOpenTime) {
        this.txtOpenTime = txtOpenTime;
    }

    public String getKoordinat() {
        return Koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.Koordinat = koordinat;
    }

    public String getGambarKuliner() {
        return GambarKuliner;
    }

    public void setGambarKuliner(String gambarKuliner) {
        this.GambarKuliner = gambarKuliner;
    }

    public String getKategoriKuliner() {
        return KategoriKuliner;
    }

    public void setKategoriKuliner(String kategoriKuliner) {
        this.KategoriKuliner = kategoriKuliner;
    }
}
