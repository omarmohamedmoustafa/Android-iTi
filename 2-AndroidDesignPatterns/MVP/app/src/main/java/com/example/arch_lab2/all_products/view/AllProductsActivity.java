package com.example.arch_lab2.all_products.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arch_lab2.R;
import com.example.arch_lab2.all_products.presenter.Presenter;
import com.example.arch_lab2.model.local_source.ProductLocalDataSource;
import com.example.arch_lab2.model.pojo.Product;
import com.example.arch_lab2.model.remote_source.ProductRemoteDataSource;
import com.example.arch_lab2.model.repository.Repository;

import java.util.List;

public class AllProductsActivity extends AppCompatActivity implements IView,OnProductClickListener{

    RecyclerView rv;
    Adapter adapter;
    Presenter presenter;
    LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_products);
        rv= findViewById(R.id.allProductsRV);

        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        presenter = new Presenter(this, Repository.getInstance(ProductLocalDataSource.getInstance(this), ProductRemoteDataSource.getInstance()));
        presenter.getAllProducts();

    }


    @Override
    public void showData(List<Product> products) {
        adapter = new Adapter(products,this);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnAddToFavClick(Product product) {
        presenter.addToFavProducts(product);
        Toast.makeText(this, "Item Added to fav", Toast.LENGTH_SHORT).show();
    }

}
