package com.example.tubes_angkringan.Admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes_angkringan.R;

public class ListMenuCemilanAdmin extends AppCompatActivity {

    private ImageView imageView;
    private ImageButton back;
    private ImageButton tambah;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cemilan_admin);

        imageView = findViewById(R.id.imageViewCemilan);
//        back = findViewById(R.id.imageButton);
//        tambah = findViewById(R.id.imageButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tambahmakanan = new Intent(ListMenuCemilanAdmin.this, PageTambahMenuCemilanAdmin.class);
                startActivity(tambahmakanan);
            }
        });

        // Terima ByteArray dari intent
        byte[] byteArray = getIntent().getByteArrayExtra("imageByteArray");

        // Konversi ByteArray kembali menjadi Bitmap
        if (byteArray != null) {
            Bitmap imageBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imageView.setImageBitmap(imageBitmap);
        }
    }
}

