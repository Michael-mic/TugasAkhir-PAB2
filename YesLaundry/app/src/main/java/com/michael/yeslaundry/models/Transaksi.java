package com.michael.yeslaundry.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaksi implements Parcelable {
    private int id, kg, harga;
    private String namapelanggan, alamat, jenis, status, nohp;

    public Transaksi() {
    }

    protected Transaksi(Parcel in) {
        id = in.readInt();
        nohp = in.readString();
        kg = in.readInt();
        harga = in.readInt();
        namapelanggan = in.readString();
        alamat = in.readString();
        jenis = in.readString();
        status = in.readString();
    }

    public static final Creator<Transaksi> CREATOR = new Creator<Transaksi>() {
        @Override
        public Transaksi createFromParcel(Parcel in) {
            return new Transaksi(in);
        }

        @Override
        public Transaksi[] newArray(int size) {
            return new Transaksi[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getNamapelanggan() {
        return namapelanggan;
    }

    public void setNamapelanggan(String namapelanggan) {
        this.namapelanggan = namapelanggan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nohp);
        parcel.writeInt(kg);
        parcel.writeInt(harga);
        parcel.writeString(namapelanggan);
        parcel.writeString(alamat);
        parcel.writeString(jenis);
        parcel.writeString(status);
    }
}
