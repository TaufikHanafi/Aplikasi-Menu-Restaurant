package com.example.tubes_angkringan.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes_angkringan.R;
import com.example.tubes_angkringan.User.DaftarMenuPelanggan;

public class TampilanPelanggan extends AppCompatActivity {

    private EditText etInputName;
    private ImageButton keranjang;
    private ImageButton kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_tampilan_pelanggan);

        etInputName = findViewById(R.id.etInputName);  // Replace 'yourEditTextId' with the actual ID from your layout
        keranjang = findViewById(R.id.imageButton2);
        kembali = findViewById(R.id.imageButton3);

        keranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerName = etInputName.getText().toString();

                Intent intent = new Intent(TampilanPelanggan.this, DaftarMenuPelanggan.class);
                intent.putExtra("customerName", customerName);
                startActivity(intent);
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Kembali ke Page1Activity
            }
        });
    }
}
