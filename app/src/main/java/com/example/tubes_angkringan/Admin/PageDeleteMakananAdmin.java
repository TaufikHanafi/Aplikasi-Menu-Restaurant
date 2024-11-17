package com.example.tubes_angkringan.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes_angkringan.DataHelper;
import com.example.tubes_angkringan.Menu;
import com.example.tubes_angkringan.MenuAdapter;
import com.example.tubes_angkringan.R;

import java.util.ArrayList;

public class PageDeleteMakananAdmin extends AppCompatActivity {

    private ImageButton deleteButton;
    private ImageButton cancelButton;
    private Menu selectedMenu;
    private DataHelper dataHelper;
    private MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_delete_makanan_admin);

        // Initialize views
        deleteButton = findViewById(R.id.imageButton9);
        cancelButton = findViewById(R.id.imageButton6);

        // Initialize DataHelper
        dataHelper = new DataHelper(this);

        // Retrieve the selected Menu object from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("SELECTED_MENU")) {
            selectedMenu = intent.getParcelableExtra("SELECTED_MENU");
        }

        adapter = new MenuAdapter(new ArrayList<>(), this);

        // Set click listener for the delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to perform the delete operation
                deleteMenu();
            }
        });

        // Set click listener for the cancel button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity (PageDeleteMakananAdmin)
                finish();
            }
        });
    }

    private void deleteMenu() {
        if (selectedMenu != null) {
            // Perform the delete operation in the database
            int rowsAffected = dataHelper.deleteMenu(selectedMenu.getId(), adapter);

            if (rowsAffected > 0) {
                // Delete successful, notify adapter and set result code
                adapter.removeItem(selectedMenu);
                setResult(RESULT_OK);
                finish();
                Toast.makeText(this, "Menu berhasil dihapus!", Toast.LENGTH_SHORT).show();
            } else {
                // Error deleting menu, handle it accordingly
                Toast.makeText(this, "Gagal menghapus menu.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle the case where no menu is selected
            Toast.makeText(this, "Pilih menu terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }

}
