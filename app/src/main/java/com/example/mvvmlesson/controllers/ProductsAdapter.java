package com.example.mvvmlesson.controllers;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.mvvmlesson.database_room.Product;
import com.example.mvvmlesson.listeners.ProductListener;
import com.example.mvvmlesson.views.ProductViewHolder;

public class ProductsAdapter extends ListAdapter<Product, ProductViewHolder> {
   private ProductListener productListener;
    public ProductsAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback,ProductListener listener) {
        super(diffCallback);
        this.productListener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
          Product product = getItem(position);
           holder.bind(product.getId(), product.getBrand(),
                   product.getPrice(), product.getQuantity(),
                   product.getCategory());

           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   productListener.productIsClicked(getItem(position));
               }
           });
    }
    public static class ProductDiff extends DiffUtil.ItemCallback<Product> {

        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId() == newItem.getId() &&
                    oldItem.getBrand().equals(newItem.getBrand()) &&
                    oldItem.getPrice() == newItem.getPrice() &&
                    oldItem.getQuantity() == newItem.getQuantity() &&
                    oldItem.getCategory().equals(newItem.getCategory());
        }
    }
}
