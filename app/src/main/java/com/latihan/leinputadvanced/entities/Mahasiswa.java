package com.latihan.leinputadvanced.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Mahasiswa implements Parcelable {
    private String nama,username,password, imagePath;
    private int nim;

    public Mahasiswa(int nim, String nama,String username,String password,String imagePath){
        this.nim = nim;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.imagePath = imagePath;
    }
    protected Mahasiswa(Parcel in) {
        this.nim = in.readInt();
        this.nama = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.imagePath = in.readString();
    }

    public int getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getImagePath() {
        return imagePath;
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel in) {
            return new Mahasiswa(in);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(nim);
        parcel.writeString(nama);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(imagePath);
    }
}
