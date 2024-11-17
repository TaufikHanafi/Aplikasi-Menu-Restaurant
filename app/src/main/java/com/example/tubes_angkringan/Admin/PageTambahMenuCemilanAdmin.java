package com.example.tubes_angkringan.Admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes_angkringan.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PageTambahMenuCemilanAdmin extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 2;

    private ImageView imageView;
    private ImageButton uploadButton;
    private ImageButton createButton;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_tambah_menu_cemilan_admin);

        imageView = findViewById(R.id.imageView23);
        uploadButton = findViewById(R.id.imageButton39);
        createButton = findViewById(R.id.imageButton40);
        back = findViewById(R.id.imageButton37);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memilih gambar dari galeri
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, REQUEST_IMAGE_PICK);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Example intent to the next page
                Bitmap yourBitmap = getBitmapFromImageView(imageView);
                if (yourBitmap != null) {
                    // Konversi Bitmap ke ByteArray
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    yourBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    // Kirim ByteArray ke NextActivity
                    Intent intent = new Intent(PageTambahMenuCemilanAdmin.this, ListMenuCemilanAdmin.class);
                    intent.putExtra("imageByteArray", byteArray);
                    startActivity(intent);
                }
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
}