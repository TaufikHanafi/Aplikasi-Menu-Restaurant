package com.example.tubes_angkringan.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes_angkringan.R;

public class PageLoginAdmin extends AppCompatActivity {

    private EditText etUsername, etPassword;

    private ImageButton back;

    private ImageButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_login_admin);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        back = findViewById(R.id.imageButtonloginadminlist);
        login = findViewById(R.id.imageButton4);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check username and password
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.equals("admin") && password.equals("admin")) {
                    // Jika benar, pindah ke PageAdmin
                    String adminussername = etUsername.getText().toString();

                    Intent pindah = new Intent(PageLoginAdmin.this, PageAdmin.class);
                    pindah.putExtra("USERNAME", adminussername);
                    startActivity(pindah);

                } else {
                    // Jika salah, tampilkan pesan kesalahan
                    Toast.makeText(PageLoginAdmin.this, "Username atau password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Kembali ke Page1Activity
            }
        });

        // Tambahan kode lainnya untuk PageLoginAdmin jika diperlukan
    }
}
