package com.example.mvvmlesson.database_room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void addNewProduct(Product product);

    @Query("SELECT * FROM products WHERE product_category = :category")
    LiveData<List<Product>> getProductsByCategory(String category);

    @Query("SELECT * FROM products ORDER BY product_brand ASC")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM products WHERE product_id = :id")
    LiveData<Product> getProductById(int id);

    @Query("SELECT * FROM products WHERE product_brand = :brand")
    LiveData<List<Product>> getProductsByBrand(String brand);

    @Update
    void modifyProduct(Product product);

    @Delete
    void deleteProduct(Product product);

    @Query("DELETE FROM products")
    void deleteAllProducts();



}
