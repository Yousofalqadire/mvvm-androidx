package com.example.mvvmlesson.database_room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlin.jvm.Volatile;

@Database(entities = {Product.class}, version = 1,exportSchema = false)
public abstract class ShopRoomDatabase extends RoomDatabase {
    public abstract ProductDao productDao(); // using product DAO
    private static volatile  ShopRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(4);
     public static ShopRoomDatabase getDatabase(Context context){
         if(INSTANCE == null){
             synchronized(ShopRoomDatabase.class){
                 INSTANCE = Room.databaseBuilder(context,ShopRoomDatabase.class,"shop_database")
                         .addCallback(callback).build();
             }
         }
         return INSTANCE;
     }
     // to add default values
     private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){

         @Override
         public void onCreate(@NonNull SupportSQLiteDatabase db) {
             super.onCreate(db);
             databaseWriterExecutor.execute(() -> {
                 ProductDao dao = INSTANCE.productDao();
                 // delete all items when install the application
                // dao.deleteAllProducts();
                 ArrayList<Product> products = new ArrayList<>();
                 products.add(new Product(0,"Tello",25.0,8,"Slim-fit Jeans"));
                 products.add(new Product(0,"Contra",25.0,8,"Slim-fit Jeans"));
                 products.add(new Product(0,"Tello",25.0,8,"Straight Jeans"));
                 products.add(new Product(0,"MassemoDutti",25.0,8,"Regular Jeans"));
                 products.add(new Product(0,"Brango",25.0,8,"Slim-fit shirt"));
                 for(Product product:products){
                     dao.addNewProduct(product);
                 }

             });
         }
     };
}
