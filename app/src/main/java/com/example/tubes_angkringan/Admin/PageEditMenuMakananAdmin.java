package com.example.tubes_angkringan.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tubes_angkringan.DataHelper;
import com.example.tubes_angkringan.Menu;
import com.example.tubes_angkringan.MenuAdapter;
import com.example.tubes_angkringan.R;

import java.util.List;

// Inside PageEditMenuMakananAdmin.java

public class PageEditMenuMakananAdmin extends AppCompatActivity {

    private EditText editNamaMakanan;
    private EditText editKeteranganMakanan;
    private EditText editHargaMakanan;
    private ImageButton updateButton;
    private ImageView imageView;
    private ImageButton back;

    private Menu selectedMenu;
    private DataHelper dataHelper;
    private MenuAdapter adapter;
    private List<Menu> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_edit_menu_makanan); // Replace with your actual layout file

        // Initialize views
        editNamaMakanan = findViewById(R.id.editNamaMakanan);
        editKeteranganMakanan = findViewById(R.id.editKeteranganMakanan);
        editHargaMakanan = findViewById(R.id.editHargaMakanan);
        updateButton = findViewById(R.id.imageButton21);
        imageView = findViewById(R.id.imageView15);
        back = findViewById(R.id.imageButton17);

        // Initialize DataHelper
        dataHelper = new DataHelper(this);

        // Retrieve the selected Menu object from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("SELECTED_MENU")) {
            selectedMenu = intent.getParcelableExtra("SELECTED_MENU");

            // Populate EditText fields with existing values
            editNamaMakanan.setText(selectedMenu.getNamaMakanan());
            editKeteranganMakanan.setText(selectedMenu.getKeteranganMakanan());
            editHargaMakanan.setText(String.valueOf(selectedMenu.getHargaMakanan()));
            // You may need to handle image loading here
        }

        menuList = dataHelper.getAllMenu();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Set click listener for the update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to perform the update operation
                updateMenu();
            }
        });
    }

    private void updateMenu() {
        // Retrieve updated values from EditText fields
        String updatedNamaMakanan = editNamaMakanan.getText().toString().trim();
        String updatedKeteranganMakanan = editKeteranganMakanan.getText().toString().trim();
        String updatedHargaMakananString = editHargaMakanan.getText().toString().trim();

        // Check if any of the fields is empty
        if (updatedNamaMakanan.isEmpty() || updatedKeteranganMakanan.isEmpty() || updatedHargaMakananString.isEmpty()) {
            // Handle the case where any field is empty
            Toast.makeText(this, "Semua bidang harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert the updated price to a double
        double updatedHargaMakanan = Double.parseDouble(updatedHargaMakananString);

        // Update the selectedMenu object with the updated values
        selectedMenu.setNamaMakanan(updatedNamaMakanan);
        selectedMenu.setKeteranganMakanan(updatedKeteranganMakanan);
        selectedMenu.setHargaMakanan(updatedHargaMakanan);

        if (adapter == null) {
            adapter = new MenuAdapter(dataHelper.getAllMenu(), this);
        }

        // Perform the update operation in the database
        int rowsAffected = dataHelper.updateMenu(selectedMenu, adapter);

        if (rowsAffected > 0) {
            // Update successful, set result code
            setResult(RESULT_OK);
            finish();
            Toast.makeText(this, "Menu berhasil diperbarui!", Toast.LENGTH_SHORT).show();
        } else {
            // Error updating menu, handle it accordingly
            Toast.makeText(this, "Gagal memperbarui menu.", Toast.LENGTH_SHORT).show();
        }

        // Finish the current activity (PageEditMenuMakananAdmin)
        finish();
    }
}

