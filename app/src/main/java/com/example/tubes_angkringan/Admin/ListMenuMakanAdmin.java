package com.example.tubes_angkringan.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes_angkringan.DataHelper;
import com.example.tubes_angkringan.Menu;
import com.example.tubes_angkringan.MenuAdapter;
import com.example.tubes_angkringan.R;
import com.example.tubes_angkringan.RecyclerItemClickListener;

import java.util.List;

public class ListMenuMakanAdmin extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageButton create;
    private DataHelper dataHelper;
    private MenuAdapter adapter;
    private ImageButton back;
    private ImageButton delete;
    private ImageButton update;
    private Menu selectedMenu;
    private int selectedItemPosition = RecyclerView.NO_POSITION;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_makan_admin);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        create = findViewById(R.id.imageButtonTambah);
        back = findViewById(R.id.imageButton);
        delete = findViewById(R.id.imageButton8);
        update = findViewById(R.id.imageButton7);

        // Initialize DataHelper and get the list of menus
        dataHelper = new DataHelper(this);
        List<Menu> menuList = dataHelper.getAllMenu();

        // Initialize the MenuAdapter with the menuList and set it to the RecyclerView
        adapter = new MenuAdapter(menuList, this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        long newMenuId = intent.getLongExtra("NEW_MENU_ID", -1);

        if (newMenuId != -1) {
            // Menu added successfully, refresh the RecyclerView
            List<Menu> updatedMenuList = dataHelper.getAllMenu();
            adapter.updateMenuList(updatedMenuList);
            adapter.refreshMenuList();
        }

        // Set click listeners
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createintent = new Intent(ListMenuMakanAdmin.this, PageTambahMenuMakananAdmin.class);
                startActivity(createintent);
            }
        });

        // Set click listeners for RecyclerView items
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Clear the background color of the previously selected item
                if (selectedItemPosition != RecyclerView.NO_POSITION) {
                    RecyclerView.ViewHolder prevSelectedViewHolder = recyclerView.findViewHolderForAdapterPosition(selectedItemPosition);
                    if (prevSelectedViewHolder != null) {
                        prevSelectedViewHolder.itemView.setBackgroundResource(android.R.color.transparent);
                    }
                }

                // Set the selectedMenu based on the clicked position
                selectedMenu = menuList.get(position);

                // Set the background color of the selected item
                selectedItemPosition = position;
                view.setBackgroundResource(R.color.selectedItemBackgroundColor);

                // Notify the adapter that the data set has changed to refresh the views
                adapter.notifyDataSetChanged();
            }
        }));


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if a menu is selected
                if (selectedMenu != null) {
                    // Pass the selectedMenu to PageEditMenuMakananAdmin activity
                    Intent updateIntent = new Intent(ListMenuMakanAdmin.this, PageEditMenuMakananAdmin.class);
                    updateIntent.putExtra("SELECTED_MENU", selectedMenu);
                    startActivityForResult(updateIntent, 1); // Replace with your actual request code
                } else {
                    // Handle the case where no menu is selected
                    Toast.makeText(ListMenuMakanAdmin.this, "Pilih menu terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteIntent = new Intent(ListMenuMakanAdmin.this, PageDeleteMakananAdmin.class);
                deleteIntent.putExtra("SELECTED_MENU", selectedMenu);
                startActivityForResult(deleteIntent, 2);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Kembali ke
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) { // Replace with your actual request code
            if (resultCode == RESULT_OK) {
                // Update successful, refresh the RecyclerView
                List<Menu> updatedMenuList = dataHelper.getAllMenu();
                adapter.updateMenuList(updatedMenuList);
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                // Delete successful, refresh the RecyclerView
                List<Menu> updatedMenuList = dataHelper.getAllMenu();
                adapter.updateMenuList(updatedMenuList);
                adapter.notifyDataSetChanged();
            }
        }
    }

    public MenuAdapter getMenuAdapter() {
        return adapter;

    }
}

