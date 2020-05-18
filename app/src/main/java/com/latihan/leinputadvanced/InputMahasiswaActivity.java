package com.latihan.leinputadvanced;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.latihan.leinputadvanced.entities.Mahasiswa;
import com.latihan.leinputadvanced.services.MahasiswaService;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputMahasiswaActivity extends AppCompatActivity {
    MahasiswaService mahasiswaService;
    Button btAddMahasiswa,btLoadImage;
    TextView tvImagePath;
    ImageView ivImage;
    EditText etNIM,etName,etUsername,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_mahasiswa);

        this.mahasiswaService = ((MahasiswaApp) getApplication()).getMahasiswaService();

        tvImagePath = (TextView) findViewById(R.id.tv_image_path);
        ivImage = (ImageView) findViewById(R.id.imageView);

        etNIM = (EditText) findViewById(R.id.et_input_nim);
        etName = (EditText) findViewById(R.id.et_input_name);
        etUsername = (EditText) findViewById(R.id.et_input_username);
        etPassword = (EditText) findViewById(R.id.et_input_password);

        btAddMahasiswa = (Button) findViewById(R.id.bt_add_mahasiswa);
        btLoadImage = (Button) findViewById(R.id.bt_load_image);

        btAddMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMahasiswa();
            }
        });
        btLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialFilePicker().withActivity(InputMahasiswaActivity.this)
                        .withHiddenFiles(true)
                        .withFilterDirectories(false)
                        .withRequestCode(1000)
                        .withFilter(Pattern.compile("[^\\s]+(\\.(?i)(jpg|png|gif|bmp))$"))
                        .start();
            }
        });
    }

    private void addMahasiswa(){
        if(
                validateInput(etNIM,Pattern.compile("^(\\d)*$"),"Masukkan angka!") &&
                validateInput(etName,null,null) &&
                validateInput(etUsername,null,null) &&
                validateInput(etPassword,null,null) &&
                validateInput(tvImagePath,Pattern.compile("^[^(^\\.\\.\\.$)]+[^\\s]+[(\\.(?i)(jpg|png|gif|bmp))]$"),"Belum ada gambar!")
        ){
            Mahasiswa nMhs = new Mahasiswa(
                    Integer.valueOf(etNIM.getText().toString()),
                    etName.getText().toString(),
                    etUsername.getText().toString(),
                    etPassword.getText().toString(),
                    tvImagePath.getText().toString()
            );

            if(mahasiswaService.addMahasiswa(nMhs)){
                Toast.makeText(this,"Mahasiswa Berhasil Disimpan!",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Mahasiswa Gagal Disimpan!",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Input not valid!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInput(Object textView,@Nullable Pattern pattern,@Nullable String errMessage){ //Experimental
        boolean valid = false;
        if(textView instanceof TextView){
            TextView tv = (TextView) textView;
            pattern = pattern != null ? pattern : Pattern.compile("^(?!\\s*$).+");
            errMessage = errMessage != null ? errMessage : "field tidak boleh kosong!";
            Matcher mather = pattern.matcher(tv.getText().toString());
            valid = mather.matches();
            if(textView instanceof EditText && !valid){
                ((EditText) textView).setError(errMessage); //does this make any sense ?
            }else if(!valid){
                tv.setError(errMessage);
            }
        }
        return valid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == 1000) && (resultCode == RESULT_OK)){
            try {
                String imagePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                ivImage.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                tvImagePath.setText(imagePath);
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this,"Load Image Failed!",Toast.LENGTH_SHORT);
            }
        }
    }
}
