package com.example.ashlihlana.cobamaps.module;

/**
 * Created by Kuncoro on 03/29/2016.
 */
public class Jarak {

    private String nama_Reseller, jarak, gambar, id;

    public Jarak() {
    }

    public Jarak(String id, String nama_Reseller, String jarak, String gambar) {
        this.nama_Reseller = nama_Reseller;
        this.jarak = jarak;
        this.gambar = gambar;
        this.id = id;

    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getNama() {
        return nama_Reseller;
    }

    public void setNama(String nama_Reseller) {
        this.nama_Reseller = nama_Reseller;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }



}
