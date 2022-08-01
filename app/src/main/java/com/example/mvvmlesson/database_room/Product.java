package com.example.mvvmlesson.database_room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "products")
public class Product implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    private int id;
    @NonNull
    @ColumnInfo(name = "product_brand")
    private String brand;
    @ColumnInfo(name = "product_price")
    private double price;
    @ColumnInfo(name = "product_quantity")
    private int quantity;
    @NonNull
    @ColumnInfo(name = "product_category")
    private String category;

    public Product(int id, @NonNull String brand, double price, int quantity, @NonNull String category) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                '}';
    }
}
