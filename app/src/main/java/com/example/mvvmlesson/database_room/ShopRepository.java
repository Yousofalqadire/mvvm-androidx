package com.example.mvvmlesson.database_room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ShopRepository {
    LiveData<List<Product>> productsByCategory;
    LiveData<List<Product>> productsByBrand;
    LiveData<List<Product>> products;
    LiveData<Product> product;
    ProductDao productDao;

    public ShopRepository(Application application) {
        ShopRoomDatabase shopRoomDatabase = ShopRoomDatabase.getDatabase(application);
        productDao = shopRoomDatabase.productDao();
        products = productDao.getAllProducts();

    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }
    public void addProduct(Product product){
        ShopRoomDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.addNewProduct(product);
            }
        });
    }
    public void deleteProduct(Product product){
        ShopRoomDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.deleteProduct(product);
            }
        });
    }
    public LiveData<Product> getProductById(int id){
       product = productDao.getProductById(id);
       return product;
    }
    public LiveData<List<Product>> getProductsByCategory(String category){
        productsByCategory = productDao.getProductsByCategory(category);
        return products;
    }
}
