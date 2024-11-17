package com.example.tubes_angkringan;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class PageKeranjang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_keranjang);

        ImageButton buttonKeranjang = findViewById(R.id.imageButtonbuttonkeranjang);
        ImageButton buttonOrder = findViewById(R.id.imageButton16);

        buttonKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logika yang akan dijalankan saat ImageButton buttonKeranjang diklik
                // Misalnya, Anda dapat menambahkan Intent untuk membuka aktivitas lain
                finish(); // Menutup aktivitas saat tombol kembali diklik
            }
        });

        // Menambahkan OnClickListener untuk ImageButton buttonOrder
        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logika yang akan dijalankan saat ImageButton buttonOrder diklik
                // Misalnya, Anda dapat menambahkan Intent untuk membuka aktivitas pemesanan
            }
        });
    }
}
