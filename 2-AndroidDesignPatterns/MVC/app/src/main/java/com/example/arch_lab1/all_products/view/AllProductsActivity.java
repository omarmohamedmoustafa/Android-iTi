package com.example.arch_lab1.all_products.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arch_lab1.R;
import com.example.arch_lab1.all_products.controller.Adapter;
import com.example.arch_lab1.all_products.controller.OnProductClickListener;
import com.example.arch_lab1.model.local_source.ProductLocalDataSource;
import com.example.arch_lab1.model.pojo.Product;
import com.example.arch_lab1.model.pojo.ProductResponse;
import com.example.arch_lab1.model.remote_source.NetworkCallback;
import com.example.arch_lab1.model.remote_source.ProductRemoteDataSource;
import com.example.arch_lab1.model.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class AllProductsActivity extends AppCompatActivity implements OnProductClickListener {

    private RecyclerView rv;
    private Adapter adapter;
    private LinearLayoutManager layoutManager;

    private List<Product> products = new ArrayList<>();

    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_products);
        rv= findViewById(R.id.allProductsRV);

        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        repository = Repository.getInstance(this);
        adapter = new Adapter(products,this);
        rv.setAdapter(adapter);

//        ProductRemoteDataSource client = ProductRemoteDataSource.getInstance();
//        client.makeNetworkCall(new NetworkCallback() {
//            @Override
//            public void onSuccess(List<Product> Products){
//                products.clear();
//                products.addAll(Products);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(String error) {
//                Toast.makeText(AllProductsActivity.this, error, Toast.LENGTH_SHORT).show();
//            }
//        });
        repository.getAllProductsFromRemoteSource(new NetworkCallback() {
            @Override
            public void onSuccess(List<Product> Products) {
                products.clear();
                products.addAll(Products);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(AllProductsActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void OnAddToFavClick(Product product) {
        repository.insertProduct(product);
    }

}
