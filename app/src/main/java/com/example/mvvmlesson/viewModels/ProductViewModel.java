package com.example.mvvmlesson.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmlesson.database_room.Product;
import com.example.mvvmlesson.database_room.ShopRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    LiveData<List<Product>> products;
    ShopRepository repository;
    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ShopRepository(application);
        products = repository.getProducts();
    }

    public LiveData<List<Product>> getProducts(){
        return products;
    }
    public void addNewProduct(Product product){
        repository.addProduct(product);
    }
    public void deleteProduct(Product product){
        repository.deleteProduct(product);
    }
}
