package com.example.tubes_angkringan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes_angkringan.Admin.PageLoginAdmin;
import com.example.tubes_angkringan.User.TampilanPelanggan;

public class TampilanUtama extends AppCompatActivity {

    private ImageButton admin;
    private ImageButton pelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_tampilan_utama);


        admin = findViewById(R.id.imageButton5);
        pelanggan = findViewById(R.id.imageButton10);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TampilanUtama.this, PageLoginAdmin.class);
                startActivity(intent);
            }
        });

        pelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pelanggan = new Intent(TampilanUtama.this, TampilanPelanggan.class);
                startActivity(pelanggan);
            }
        });
    }
}