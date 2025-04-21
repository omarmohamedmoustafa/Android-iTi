package com.example.arch_lab1.fav_products.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arch_lab1.R;
import com.example.arch_lab1.fav_products.controller.Adapter;
import com.example.arch_lab1.fav_products.controller.OnFavClickListener;
import com.example.arch_lab1.model.pojo.Product;
import com.example.arch_lab1.model.repository.Repository;

import java.util.List;

public class FavProductsActivity  extends AppCompatActivity implements OnFavClickListener {
    RecyclerView rv;
    Adapter adapter;
    LinearLayoutManager layoutManager;
    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_products);
        rv = findViewById(R.id.favProductsRV);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        repository=Repository.getInstance(this);
        adapter = new Adapter(this);
        rv.setAdapter(adapter);
        repository.getStoredProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setProducts(products);
            }
        });
    }

    @Override
    public void onUnFavClick(Product product) {
        repository.deleteProduct(product);
    }
}
