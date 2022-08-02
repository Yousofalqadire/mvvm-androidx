package com.example.mvvmlesson;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmlesson.controllers.ProductsAdapter;
import com.example.mvvmlesson.database_room.Product;
import com.example.mvvmlesson.listeners.ProductListener;
import com.example.mvvmlesson.viewModels.ProductViewModel;

public class MainActivity extends AppCompatActivity implements ProductListener {
  private RecyclerView recyclerView;
  private ProductViewModel productViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.product_rc);
        ProductsAdapter adapter = new ProductsAdapter(new ProductsAdapter.ProductDiff(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProducts().observe(this, products -> {
          adapter.submitList(products);
        });
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddProductActivity.class);
                activityResultLauncher.launch(intent);
            }
        });
    }
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();
                       Product product = (Product) data.getSerializableExtra(AddProductActivity.PRODUCT_KEY);
                        productViewModel.addNewProduct(product);
                    }
                }
            }
    );

    @Override
    public void productIsClicked(Product product) {
        new AlertDialog.Builder(this).setTitle("delete from products")
                .setPositiveButton("Yes", (dialog, which) -> {
                    productViewModel.deleteProduct(product);
                    dialog.dismiss();

                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss()).create().show();
    }
}