package com.example.tubes_angkringan.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes_angkringan.Admin.ListMenuMakanAdmin;
import com.example.tubes_angkringan.Menu;
import com.example.tubes_angkringan.MenuAdapter;
import com.example.tubes_angkringan.R;

import java.util.List;

public class DaftarMenuPelanggan extends AppCompatActivity {

    private TextView tvpelanggan;
    private ImageButton kembali;
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_menu_pelanggan);

        kembali = findViewById(R.id.imageButtonmenujualan);
        tvpelanggan = findViewById(R.id.tvWelcomePelangganMessage);

        recyclerView = findViewById(R.id.recyclerViewDaftarMenu);

        // Get the intent that started this activity
        Intent intent = getIntent();

        // Check if the parent activity is ListMenuMakanAdmin
        if (getParent() instanceof ListMenuMakanAdmin) {
            ListMenuMakanAdmin parentActivity = (ListMenuMakanAdmin) getParent();

            // Get the MenuAdapter from the parent activity
            menuAdapter = parentActivity.getMenuAdapter();

            // Check if the MenuAdapter is not null and has menuList
            if (menuAdapter != null && menuAdapter.getMenuList() != null) {
                // Set the menuList from MenuAdapter to RecyclerView
                List<Menu> menuList = menuAdapter.getMenuList();
                recyclerView.setAdapter(new MenuAdapter(menuList, this));

                // Set layout manager
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            } else {
                // Handle case when MenuAdapter or menuList is null
                // ...
            }
        } else {
            // Handle case when getParent() doesn't return an instance of ListMenuMakanAdmin
            // ...
        }

        // Set the welcome message
        if (intent.hasExtra("customerName")) {
            String customername = intent.getStringExtra("customerName");
            tvpelanggan.setText("Hallo " + customername + "!!");
        }

        // Set click listener for back button
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Go back to the parent activity
            }
        });
    }
}
