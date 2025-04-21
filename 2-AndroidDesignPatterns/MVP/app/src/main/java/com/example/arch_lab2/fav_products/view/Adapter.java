package com.example.arch_lab2.fav_products.view;

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
import com.example.arch_lab2.R;
import com.example.arch_lab2.model.pojo.Product;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ProductViewHolder> {
    private List<Product> products;
    private OnFavClickListener listener;
    public Adapter(List<Product> products, OnFavClickListener listener) {
        this.products = products;
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
        Product product = products.get(position);
        holder.title.setText(product.getTitle());
        holder.price.setText(String.format("$%.2f", product.getPrice()));
        holder.description.setText(product.getDescription());
        holder.rating.setRating((float) product.getRating());
        Glide.with(holder.itemView.getContext())
                .load(product.getThumbnail())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);
        holder.removeFromFavBtn.setOnClickListener(v -> {
            listener.onUnFavClick(product);
        });
        holder.addToFavBtn.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
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
    }
}
