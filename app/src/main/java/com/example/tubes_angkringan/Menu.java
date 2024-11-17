package com.example.tubes_angkringan;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {
    private int id;
    private byte[] imageByteArray;
    private String namaMakanan;
    private String keteranganMakanan;
    private double hargaMakanan;

    // Default constructor
    public Menu() {
    }

    // Parcelable constructor
    protected Menu(Parcel in) {
        id = in.readInt();
        imageByteArray = in.createByteArray();
        namaMakanan = in.readString();
        keteranganMakanan = in.readString();
        hargaMakanan = in.readDouble();
    }

    // Getter and setter methods for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageByteArray() {
        return imageByteArray;
    }

    public void setImageByteArray(byte[] imageByteArray) {
        this.imageByteArray = imageByteArray;
    }

    public Bitmap getImageBitmap() {
        // Convert byte array to Bitmap (use the method from your DataHelper class)
        return DataHelper.getBitmapFromByteArray(imageByteArray);
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public String getKeteranganMakanan() {
        return keteranganMakanan;
    }

    public void setKeteranganMakanan(String keteranganMakanan) {
        this.keteranganMakanan = keteranganMakanan;
    }

    public double getHargaMakanan() {
        return hargaMakanan;
    }

    public void setHargaMakanan(double hargaMakanan) {
        this.hargaMakanan = hargaMakanan;
    }

    // Parcelable methods
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByteArray(imageByteArray);
        dest.writeString(namaMakanan);
        dest.writeString(keteranganMakanan);
        dest.writeDouble(hargaMakanan);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };
}

