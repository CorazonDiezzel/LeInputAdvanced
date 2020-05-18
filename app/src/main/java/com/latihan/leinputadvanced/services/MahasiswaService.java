package com.latihan.leinputadvanced.services;

import com.latihan.leinputadvanced.entities.Mahasiswa;

import java.util.ArrayList;

public class MahasiswaService {
    private ArrayList<Mahasiswa> mahasiswas;

    public MahasiswaService() {
        this.mahasiswas = new ArrayList<Mahasiswa>();
    }

    public boolean addMahasiswa(Mahasiswa newMhs){
        boolean success = false;
        if(mahasiswas.isEmpty()){
            success = mahasiswas.add(newMhs);
        }else{
            for (Mahasiswa m: mahasiswas) {
                if(m.getNim() == newMhs.getNim()) success = false; //NIM duplicate is not allowed.
                if (success) mahasiswas.add(newMhs);
            }
        }
        return success;
    }

    public ArrayList<Mahasiswa> getMahasiswas() {
        return mahasiswas;
    }
}
