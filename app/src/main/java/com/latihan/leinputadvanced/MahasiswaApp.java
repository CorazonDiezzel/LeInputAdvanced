package com.latihan.leinputadvanced;

import android.app.Application;

import com.latihan.leinputadvanced.services.MahasiswaService;

public class MahasiswaApp extends Application {
    private static MahasiswaApp mahasiswaApp;
    private MahasiswaService mahasiswaService;

    @Override
    public void onCreate() {
        super.onCreate();
        mahasiswaApp = this;
        initialize();
    }

    private void initialize(){
        mahasiswaService = new MahasiswaService();
    }
    public MahasiswaService getInstance(){
        return mahasiswaService;
    }
    public MahasiswaService getMahasiswaService(){
        return mahasiswaService;
    }
}
