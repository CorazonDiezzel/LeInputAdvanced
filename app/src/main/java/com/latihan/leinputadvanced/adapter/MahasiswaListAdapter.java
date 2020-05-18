package com.latihan.leinputadvanced.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.latihan.leinputadvanced.R;
import com.latihan.leinputadvanced.entities.Mahasiswa;


import java.util.List;

public class MahasiswaListAdapter extends ArrayAdapter<Mahasiswa> {
    public MahasiswaListAdapter(@NonNull Context context, int resource, @NonNull List<Mahasiswa> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_list_mahasiswa,parent,false);
        }

        Mahasiswa cMhs = getItem(position);

        ImageView ivImage = (ImageView) itemView.findViewById(R.id.list_mhs_img);
        ivImage.setImageBitmap(BitmapFactory.decodeFile(cMhs.getImagePath()));

        TextView tvNim = (TextView) itemView.findViewById(R.id.list_mhs_v_nim);
        tvNim.setText(Integer.valueOf(cMhs.getNim()).toString());

        TextView tvNama = (TextView) itemView.findViewById(R.id.list_mhs_v_nama);
        tvNama.setText(cMhs.getNama());

        TextView tvUsername = (TextView) itemView.findViewById(R.id.list_mhs_v_username);
        tvUsername.setText(cMhs.getUsername());

        return itemView;
    }
}
