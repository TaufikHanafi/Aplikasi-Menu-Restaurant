package com.example.tubes_angkringan.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes_angkringan.R;

public class PageListMenuJualanAdmin extends AppCompatActivity {

    private ImageButton back;

    private ImageButton MakananAdmin;

    private ImageButton MinumanAdmin;

    private ImageButton CemilanAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_menu_jualan_admin);

        MakananAdmin = findViewById(R.id.imageButtonMakanan);
        MinumanAdmin = findViewById(R.id.imageButtonMinuman);
        CemilanAdmin = findViewById(R.id.imageButtonCemilan);
        back = findViewById(R.id.imageButtonmenujualan);

        MakananAdmin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent minumanjualan = new Intent(PageListMenuJualanAdmin.this, ListMenuMakanAdmin.class);
                startActivity(minumanjualan);
            }
        });

        MinumanAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent minumanjualan = new Intent(PageListMenuJualanAdmin.this, ListMenuMinumanAdmin.class);
                startActivity(minumanjualan);
            }
        });

        CemilanAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cemilanjualan = new Intent(PageListMenuJualanAdmin.this, ListMenuCemilanAdmin.class);
                startActivity(cemilanjualan);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Kembali ke PageAdmin
            }
        });

        // Tambahan kode lainnya untuk PageMenuJualanAdmin jika diperlukan
    }
}