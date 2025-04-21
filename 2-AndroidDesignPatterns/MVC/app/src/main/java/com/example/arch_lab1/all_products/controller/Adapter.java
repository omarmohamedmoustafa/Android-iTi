package com.example.arch_lab1.all_products.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.arch_lab1.R;
import com.example.arch_lab1.model.pojo.Product;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ProductViewHolder> {
    private List<Product> productList;
    private OnProductClickListener listener;
    public Adapter(List<Product> products,OnProductClickListener listener) {
        this.productList = products;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);

        holder.addToFavBtn.setOnClickListener(v -> {
            listener.OnAddToFavClick(product);
        });
        holder.removeFromFavBtn.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, price, description;
        RatingBar rating;
        Button addToFavBtn;
        Button removeFromFavBtn;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.pImage);
            title = itemView.findViewById(R.id.productTitle);
            price = itemView.findViewById(R.id.productPrice);
            description = itemView.findViewById(R.id.productDescription);
            rating = itemView.findViewById(R.id.productRating);
            addToFavBtn = itemView.findViewById(R.id.favBtn);
            removeFromFavBtn = itemView.findViewById(R.id.removeFavBtn);
        }

        public void bind(Product product) {
            title.setText(product.getTitle());
            price.setText(String.format("$%.2f", product.getPrice()));
            Glide.with(image.getContext())
                    .load(product.getThumbnail())
                    .into(image);
            description.setText(product.getDescription());
            rating.setRating((float) product.getRating());

        }
    }
}
