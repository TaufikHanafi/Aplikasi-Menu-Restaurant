package com.example.tubes_angkringan.Admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes_angkringan.Menu;
import com.example.tubes_angkringan.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class PageTambahMenuMinumanAdmin extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 2;

    private ImageView imageView;
    private EditText etNamaMinuman;
    private EditText etKeteranganMinuman;
    private EditText etHargaMinuman;
    private ImageButton uploadButton;
    private ImageButton createButton;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_tambah_menu_minuman_admin);

        imageView = findViewById(R.id.imageViewminum);
        etNamaMinuman = findViewById(R.id.editTextNamaMinuman);
        etKeteranganMinuman = findViewById(R.id.editTextKeteranganMinuman);
        etHargaMinuman = findViewById(R.id.editTextHargaMinuman);
        uploadButton = findViewById(R.id.imageButtonuploadminuman);
        createButton = findViewById(R.id.imageButtontambahminuman);
        back = findViewById(R.id.imageButtonkembaliminum);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memilih gambar dari galeri
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, REQUEST_IMAGE_PICK);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Konversi data ke dalam objek Menu
                Bitmap yourBitmap = getBitmapFromImageView(imageView);
                String namaMakanan = etNamaMinuman.getText().toString();
                String keteranganMakanan = etKeteranganMinuman.getText().toString();

                double hargaMinuman = 0.0;
                try {
                    hargaMinuman = Double.parseDouble(etHargaMinuman.getText().toString());
                } catch (NumberFormatException e) {
                    // Handle jika input harga tidak valid
                    e.printStackTrace();
                }

                Menu menu = new Menu();
                menu.setImageByteArray(getByteArrayFromBitmap(yourBitmap));
                menu.setNamaMakanan(namaMakanan);
                menu.setKeteranganMakanan(keteranganMakanan);
                menu.setHargaMakanan(hargaMinuman);

                // Kirim objek Menu ke ListMenuMakanAdmin
                Intent intent = new Intent(PageTambahMenuMinumanAdmin.this, ListMenuMinumanAdmin.class);
//                intent.putExtra("menu", menu);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_PICK) {
            if (data != null) {
                Uri selectedImage = data.getData();
                try {
                    // Mengonversi URI gambar menjadi Bitmap
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    // Menampilkan gambar ke ImageView
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Bitmap getBitmapFromImageView(ImageView imageView) {
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
        imageView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    private byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private String formatCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return formatter.format(amount);
    }
}
