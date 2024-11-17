package com.example.tubes_angkringan.Admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes_angkringan.DataHelper;
import com.example.tubes_angkringan.Menu;
import com.example.tubes_angkringan.MenuAdapter;
import com.example.tubes_angkringan.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class PageTambahMenuMakananAdmin extends AppCompatActivity {

    private ImageButton kembaliButton;
    private ImageButton tambahButton;
    private ImageButton uploadImageButton;
    private EditText editTextNamaMakanan;
    private EditText editTextKeteranganMakanan;
    private EditText editTextHargaMakanan;
    private DataHelper dataHelper;
    private MenuAdapter adapter;
    private List<Menu> menuList;

    private static final int PICK_IMAGE = 1;
    private Bitmap selectedImageBitmap;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_tambah_menu_makanan_admin);

        kembaliButton = findViewById(R.id.imageButton13);
        tambahButton = findViewById(R.id.imageButton15);
        uploadImageButton = findViewById(R.id.imageButton26);
        editTextNamaMakanan = findViewById(R.id.editTextNamaMakanan);
        editTextKeteranganMakanan = findViewById(R.id.editTextKeteranganMakanan);
        editTextHargaMakanan = findViewById(R.id.editTextHargaMakanan);

        dataHelper = new DataHelper(this);
        menuList = dataHelper.getAllMenu();

        kembaliButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        tambahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahMenu();
            }
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            try {
                InputStream imageStream = getContentResolver().openInputStream(data.getData());
                selectedImageBitmap = BitmapFactory.decodeStream(imageStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void tambahMenu() {
        // Get the input values from the EditText fields
        String namaMakanan = editTextNamaMakanan.getText().toString().trim();
        String keteranganMakanan = editTextKeteranganMakanan.getText().toString().trim();
        String hargaMakananString = editTextHargaMakanan.getText().toString().trim();

        // Check if any of the fields is empty
        if (namaMakanan.isEmpty() || keteranganMakanan.isEmpty() || hargaMakananString.isEmpty()) {
            // Handle the case where any field is empty
            Toast.makeText(this, "Semua bidang harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert the price to a double
        double hargaMakanan = Double.parseDouble(hargaMakananString);

        // Create a new Menu object with the input values
        Menu newMenu = new Menu();
        newMenu.setNamaMakanan(namaMakanan);
        newMenu.setKeteranganMakanan(keteranganMakanan);
        newMenu.setHargaMakanan(hargaMakanan);

        // Add the new menu to the database
        long newMenuId = dataHelper.addMenu(newMenu);

        if (newMenuId != -1) {
            // Menu added successfully to the database
            // Pass the newMenuId to the ListMenuMakanAdmin activity
            Intent successIntent = new Intent(PageTambahMenuMakananAdmin.this, ListMenuMakanAdmin.class);
            successIntent.putExtra("NEW_MENU_ID", newMenuId);
            startActivity(successIntent);

            // Finish the current activity (PageTambahMenuMakananAdmin)
            finish();
        } else {
            // Error adding menu to the database
            // Handle the error as needed
        }
    }
}
