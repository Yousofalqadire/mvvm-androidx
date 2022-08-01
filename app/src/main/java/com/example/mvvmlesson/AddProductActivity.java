package com.example.mvvmlesson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvvmlesson.database_room.Product;

public class AddProductActivity extends AppCompatActivity {
    public static final String PRODUCT_KEY = "product";
private EditText brandEdt,priceEdt,quantityEdt,categoryEdt;
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initWidget();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProductActivity.this,MainActivity.class);
                if(TextUtils.isEmpty(brandEdt.getText())||
                        TextUtils.isEmpty(priceEdt.getText()) ||
                       TextUtils.isEmpty(quantityEdt.getText())||
                       TextUtils.isEmpty(categoryEdt.getText())){
                    setResult(Activity.RESULT_CANCELED,intent);

                } else{
                    Product product = new Product(0,brandEdt.getText().toString(),
                            Double.parseDouble(priceEdt.getText().toString()),
                            Integer.parseInt(quantityEdt.getText().toString()),
                            categoryEdt.getText().toString());
                    intent.putExtra(PRODUCT_KEY,product);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });

    }

    private void initWidget(){
        brandEdt = findViewById(R.id.brand_edt);
        priceEdt = findViewById(R.id.price_edt);
        quantityEdt = findViewById(R.id.quantity_edt);
        categoryEdt = findViewById(R.id.category_edt);
        btn = findViewById(R.id.add_button);
    }
}