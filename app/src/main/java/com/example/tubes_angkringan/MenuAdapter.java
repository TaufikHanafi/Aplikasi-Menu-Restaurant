package com.example.tubes_angkringan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tubes_angkringan.Menu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<Menu> menuList;
    private Context context;

    public void updateMenuList(List<Menu> updatedMenuList) {
        menuList = updatedMenuList;
        notifyDataSetChanged();
    }
    public MenuAdapter(List<Menu> menuList, Context context) {
        this.menuList = menuList;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu menu = menuList.get(position);

        // Set the data to the views
        holder.imageView.setImageBitmap(menu.getImageBitmap());
        holder.namaMakananTextView.setText(menu.getNamaMakanan());
        holder.keteranganMakananTextView.setText(menu.getKeteranganMakanan());
        holder.hargaMakananTextView.setText(String.valueOf(menu.getHargaMakanan()));
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView namaMakananTextView;
        TextView keteranganMakananTextView;
        TextView hargaMakananTextView;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            namaMakananTextView = itemView.findViewById(R.id.namaMakananTextView);
            keteranganMakananTextView = itemView.findViewById(R.id.keteranganMakananTextView);
            hargaMakananTextView = itemView.findViewById(R.id.hargaMakananTextView);
        }
    }

    // Method to refresh the menu list when data changes
    public void refreshMenuList() {
        notifyDataSetChanged();
    }

    public void removeItem(Menu menu) {
        int position = menuList.indexOf(menu);
        if (position != -1) {
            menuList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public List<Menu> getMenuList() {
        return menuList;
    }
}
