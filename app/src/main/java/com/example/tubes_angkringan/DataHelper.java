package com.example.tubes_angkringan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.example.tubes_angkringan.Menu;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "menu_db";
    private static final int DATABASE_VERSION = 1;

    // Nama tabel dan kolom-kolomnya
    private static final String TABLE_MENU = "menu";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_NAMA_MAKANAN = "nama_makanan";
    private static final String COLUMN_KETERANGAN_MAKANAN = "keterangan_makanan";
    private static final String COLUMN_HARGA_MAKANAN = "harga_makanan";

    private static final String CREATE_TABLE_MENU =
            "CREATE TABLE " + TABLE_MENU + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_IMAGE + " BLOB," +
                    COLUMN_NAMA_MAKANAN + " TEXT," +
                    COLUMN_KETERANGAN_MAKANAN + " TEXT," +
                    COLUMN_HARGA_MAKANAN + " REAL" +
                    ")";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_MENU);
        } catch (SQLException e) {
            Log.e("DataHelper", "Error creating tables", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade database jika versi berbeda
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        onCreate(db);
    }

    // Menambahkan menu ke database
    public long addMenu(Menu menu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE, getByteArrayFromBitmap(menu.getImageBitmap()));
        values.put(COLUMN_NAMA_MAKANAN, menu.getNamaMakanan());
        values.put(COLUMN_KETERANGAN_MAKANAN, menu.getKeteranganMakanan());
        values.put(COLUMN_HARGA_MAKANAN, menu.getHargaMakanan());

        long result = db.insert(TABLE_MENU, null, values);
        db.close();

        return result;
    }

    // Mendapatkan semua menu dari database
    public List<Menu> getAllMenu() {
        List<Menu> menuList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MENU;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndex(COLUMN_ID);
            int imageColumnIndex = cursor.getColumnIndex(COLUMN_IMAGE);
            int namaColumnIndex = cursor.getColumnIndex(COLUMN_NAMA_MAKANAN);
            int keteranganColumnIndex = cursor.getColumnIndex(COLUMN_KETERANGAN_MAKANAN);
            int hargaColumnIndex = cursor.getColumnIndex(COLUMN_HARGA_MAKANAN);

            do {
                Menu menu = new Menu();
                menu.setId(cursor.getInt(idColumnIndex));

                // Check if the column index is valid
                if (imageColumnIndex != -1) {
                    byte[] imageByteArray = cursor.getBlob(imageColumnIndex);
                    if (imageByteArray != null) {
                        menu.setImageByteArray(imageByteArray);
                    }
                }

                if (namaColumnIndex != -1) {
                    String namaMakanan = cursor.getString(namaColumnIndex);
                    if (namaMakanan != null) {
                        menu.setNamaMakanan(namaMakanan);
                    }
                }

                if (keteranganColumnIndex != -1) {
                    String keteranganMakanan = cursor.getString(keteranganColumnIndex);
                    if (keteranganMakanan != null) {
                        menu.setKeteranganMakanan(keteranganMakanan);
                    }
                }

                // Check if hargaColumnIndex is valid and not null
                if (hargaColumnIndex != -1 && !cursor.isNull(hargaColumnIndex)) {
                    double hargaMakanan = cursor.getDouble(hargaColumnIndex);
                    menu.setHargaMakanan(hargaMakanan);
                } else {
                    // Set a default value or handle it according to your application's logic
                    menu.setHargaMakanan(0.0); // You can set any default value here
                }

                menuList.add(menu);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return menuList;
    }

    // Mengupdate menu di database
    public int updateMenu(Menu menu, MenuAdapter adapter) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_IMAGE, getByteArrayFromBitmap(menu.getImageBitmap()));
            values.put(COLUMN_NAMA_MAKANAN, menu.getNamaMakanan());
            values.put(COLUMN_KETERANGAN_MAKANAN, menu.getKeteranganMakanan());
            values.put(COLUMN_HARGA_MAKANAN, menu.getHargaMakanan());

            int rowsAffected = db.update(TABLE_MENU, values, COLUMN_ID + " = ?", new String[]{String.valueOf(menu.getId())});

            if (rowsAffected > 0) {
                // Menggunakan callback untuk memberi tahu Adapter
                adapter.refreshMenuList();
            }

            return rowsAffected;
        } catch (SQLException e) {
            Log.e("DataHelper", "Error updating menu with ID: " + menu.getId(), e);
            return -1; // Menandakan bahwa ada kesalahan
        } finally {
            db.close();
        }
    }

    // Menghapus menu dari database
    public int deleteMenu(int menuId, MenuAdapter adapter) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            int rowsAffected = db.delete(TABLE_MENU, COLUMN_ID + " = ?", new String[]{String.valueOf(menuId)});

            if (rowsAffected > 0) {
                // Menggunakan callback untuk memberi tahu Adapter
                adapter.refreshMenuList();
            }

            return rowsAffected;
        } catch (SQLException e) {
            Log.e("DataHelper", "Error deleting menu with ID: " + menuId, e);
            return -1; // Menandakan bahwa ada kesalahan
        } finally {
            db.close();
        }
    }

    // Mengonversi byte array ke Bitmap
    public static Bitmap getBitmapFromByteArray(byte[] byteArray) {
        if (byteArray != null) {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } else {
            return null;
        }
    }

    // Make this method public
    public static byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Check if the bitmap is null before compressing
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        }
        return stream.toByteArray();
    }

}
