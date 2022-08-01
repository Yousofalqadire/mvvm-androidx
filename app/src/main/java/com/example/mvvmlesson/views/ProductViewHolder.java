package com.example.mvvmlesson.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlesson.R;

public class ProductViewHolder extends RecyclerView.ViewHolder{
    private final TextView id;
    private final TextView brand;
    private final TextView price;
    private final TextView quantity;
    private final TextView category;
    private ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.product_id);
        brand = itemView.findViewById(R.id.product_Brand);
        price = itemView.findViewById(R.id.product_Price);
        quantity = itemView.findViewById(R.id.product_quantity);
        category = itemView.findViewById(R.id.product_category);
    }

    public void bind(int _id,String _brand,double p,int q,String c){
        id.setText(_id + "");
        brand.setText(_brand);
        price.setText(String.valueOf(p));
        quantity.setText(String.valueOf(q));
        category.setText(c);

    }




   public static ProductViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item,parent,false);
        return new ProductViewHolder(view);

   }
}
