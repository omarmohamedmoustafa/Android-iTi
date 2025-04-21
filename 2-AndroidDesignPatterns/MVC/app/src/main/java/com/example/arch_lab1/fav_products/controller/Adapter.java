package com.example.arch_lab1.fav_products.controller;

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

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.FavViewHolder> {
    private List<Product> products = new ArrayList<>();
    private OnFavClickListener clickListener;

    public Adapter(OnFavClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new FavViewHolder(view);
    }

    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
        holder.removeFromFavBtn.setOnClickListener(v -> clickListener.onUnFavClick(product));
        holder.addToFavBtn.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class FavViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, price, description;
        RatingBar rating;
        Button addToFavBtn;
        Button removeFromFavBtn;
        public FavViewHolder(@NonNull View itemView) {
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
