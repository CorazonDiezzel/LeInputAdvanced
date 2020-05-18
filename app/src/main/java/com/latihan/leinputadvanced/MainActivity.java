package com.latihan.leinputadvanced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.security.acl.Permission;


public class MainActivity extends AppCompatActivity {
    Button btToAddmahasiswa,btToViewMahasiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION_CODES.M > BuildConfig.VERSION_CODE
                && checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1000);
        }

        btToAddmahasiswa = (Button) findViewById(R.id.bt_toAddMahasiswa);
        btToViewMahasiswa = (Button) findViewById(R.id.bt_toViewMahasiswa);

        btToAddmahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,InputMahasiswaActivity.class);
                startActivity(i);
            }
        });
        btToViewMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ViewMahasiswaActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1000:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "EXT R Permission Granted!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "EXT R Permission Denied!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
