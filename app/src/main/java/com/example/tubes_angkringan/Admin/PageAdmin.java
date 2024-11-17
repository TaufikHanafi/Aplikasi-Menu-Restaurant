package com.example.tubes_angkringan.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes_angkringan.R;

public class PageAdmin extends AppCompatActivity {

    private TextView tvWelcomeMessage;
    private ImageButton menujualan;

    private ImageButton menupelanggan;

    private ImageButton backadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_admin);

        tvWelcomeMessage = findViewById(R.id.tvWelcomeMessage);
        menujualan = findViewById(R.id.imageButton11);
        menupelanggan = findViewById(R.id.imageButton12);
        backadmin = findViewById(R.id.imageButtonmenujualan);

        // Mendapatkan Username yang sudah diinputkan sebelumnya
        Intent intent = getIntent();
        if (intent.hasExtra("USERNAME")) {
            String username = intent.getStringExtra("USERNAME");
            tvWelcomeMessage.setText("Selamat Datang " + username + " !!");
        }

        menujualan.setOnClickListener(view -> {
            // Pindah ke PageMenuJualanAdmin
            Intent menuJualanIntent = new Intent(PageAdmin.this,PageListMenuJualanAdmin.class);
            startActivity(menuJualanIntent);
        });

        backadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Kembali ke
            }
        });

        menupelanggan.setOnClickListener(view -> {
            Intent menuPelangganIntent = new Intent(PageAdmin.this,PageListMenuPelangganAdmin.class);
            startActivity(menuPelangganIntent);
        });
    }
}

