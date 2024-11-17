package com.example.tubes_angkringan.Admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes_angkringan.DataHelper;
import com.example.tubes_angkringan.Menu;
import com.example.tubes_angkringan.MenuAdapter;
import com.example.tubes_angkringan.R;

import java.util.ArrayList;
import java.util.List;

public class ListMenuMinumanAdmin extends AppCompatActivity {

//    private RecyclerView recyclerView;
//    private MenuAdapter menuAdapter;
//    private List<Menu> menuList;
//    private ImageButton tambah;
//    private ImageButton update;
//    private ImageButton delete;
//    private ImageButton back;
//    private DataHelper dataHelper;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_minum_admin);
//
//
//
//        recyclerView = findViewById(R.id.recyclerView);
//        menuList = new ArrayList<>();
////        menuAdapter = new MenuAdapter(menuList);
//        tambah = findViewById(R.id.imageButtonTambahminum);
//        back = findViewById(R.id.imageButtonminumadmin);
//        update = findViewById(R.id.imageButtoneditminum);
//        delete = findViewById(R.id.imageButtondeleteminum);
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish(); // Kembali ke
//            }
//        });
//
//        tambah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent tambah = new Intent(ListMenuMinumanAdmin.this, PageTambahMenuMinumanAdmin.class);
////                intent.putExtra("dataHelper", dataHelper);
////                intent.putExtra("menuAdapter", menuAdapter);
//                startActivityForResult(tambah, 1);
//            }
//        });
//
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Mendapatkan posisi item yang dipilih
//                int selectedItemPosition = menuAdapter.getSelectedItemPosition();
//
//                if (selectedItemPosition != RecyclerView.NO_POSITION) {
//                    // Jika ada item yang dipilih, buka PageEditMenuMakananAdmin
//                    Menu selectedMenu = menuList.get(selectedItemPosition);
//                    Intent editIntent = new Intent(ListMenuMinumanAdmin.this, PageEditMenuMinumanAdmin.class);
////                    editIntent.putExtra("menu", selectedMenu);
//                    editIntent.putExtra("position", selectedItemPosition);
//                    startActivityForResult(editIntent, 2); // Gunakan requestCode yang berbeda, misalnya 2
//                } else {
//                    Log.d("ListMenuMakanAdmin", "No item selected for update");
//                }
//            }
//        });
//
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int selectedItemPosition = menuAdapter.getSelectedItemPosition();
//
//                if (selectedItemPosition != RecyclerView.NO_POSITION) {
//                    // Jika ada item yang dipilih, buka PageDeleteMakananAdmin
//                    Menu selectedMenu = menuList.get(selectedItemPosition);
//                    Intent deleteIntent = new Intent(ListMenuMinumanAdmin.this, PageDeleteMinumanAdmin.class);
////                    deleteIntent.putExtra("menu", selectedMenu);
//                    startActivityForResult(deleteIntent, 3); // Gunakan requestCode yang berbeda, misalnya 3
//                } else {
//                    Log.d("ListMenuMakanAdmin", "No item selected for delete");
//                }
//            }
//        });
//
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(menuAdapter);
//
//        // Terima objek Menu dari intent
//        Intent intent = getIntent();
//        if (intent.hasExtra("menu")) {
//            Menu menu = intent.getParcelableExtra("menu");
//            menuList.add(menu);
//            // Pastikan tidak ada kode yang mengatur menuAdapter ke null
//            menuAdapter.notifyDataSetChanged();
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            if (data != null) {
//                if (data.hasExtra("menu")) {
//                    // Menambahkan menu baru
//                    Menu menu = data.getParcelableExtra("menu");
//                    menuList.add(menu);
//                } else if (data.hasExtra("updatedMenu")) {
//                    // Memperbarui menu yang sudah ada
//                    Menu updatedMenu = data.getParcelableExtra("updatedMenu");
//                    int position = data.getIntExtra("position", -1);
//
//                    if (position != -1) {
//                        // Memastikan posisi valid sebelum memperbarui
//                        menuList.set(position, updatedMenu);
//                    }
//                }
//
//                // Memperbarui tampilan RecyclerView
//                menuAdapter.notifyDataSetChanged();
//
////                // Simpan data ke SharedPreferences
////                saveDataToSharedPreferences();
//            }
//        } else if (requestCode == 2 && resultCode == RESULT_OK) {
//            // Handle hasil dari PageEditMenuMakananAdmin.java
//            if (data != null && data.hasExtra("updatedMenu")) {
//                Menu updatedMenu = data.getParcelableExtra("updatedMenu");
//                int position = data.getIntExtra("position", -1);
//
//                if (position != -1) {
//                    // Memastikan posisi valid sebelum memperbarui
//                    menuList.set(position, updatedMenu);
//                    menuAdapter.notifyDataSetChanged();
//                }
//            }
//        }
//        if (requestCode == 3 && resultCode == RESULT_OK) {
//            // Handle hasil dari PageDeleteMakananAdmin
//            if (data != null && data.hasExtra("deletedMenu")) {
//                Menu deletedMenu = data.getParcelableExtra("deletedMenu");
//
//                if (deletedMenu != null) {
//                    // Hapus item dari list dan perbarui tampilan RecyclerView
//                    menuList.remove(deletedMenu);
//                    menuAdapter.notifyDataSetChanged();
//
//                }
//            }
//        }
//    }
}


