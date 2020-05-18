package com.latihan.leinputadvanced;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.latihan.leinputadvanced.adapter.MahasiswaListAdapter;
import com.latihan.leinputadvanced.services.MahasiswaService;

public class ViewMahasiswaActivity extends AppCompatActivity {
    MahasiswaService mahasiswaService;
    ListView lvMahasiswa;
    MahasiswaListAdapter mahasiswaListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mahasiswa);

        mahasiswaService = ((MahasiswaApp) getApplication()).getMahasiswaService();

        lvMahasiswa = (ListView) findViewById(R.id.listview_mahasiswa);
        mahasiswaListAdapter = new MahasiswaListAdapter(this,R.layout.item_list_mahasiswa,mahasiswaService.getMahasiswas());
        lvMahasiswa.setAdapter(mahasiswaListAdapter);
        lvMahasiswa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Hhhmmm...
            }
        });
    }
}
