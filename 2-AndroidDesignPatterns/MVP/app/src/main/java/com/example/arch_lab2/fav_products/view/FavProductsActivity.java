package com.example.arch_lab2.fav_products.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arch_lab2.R;
import com.example.arch_lab2.fav_products.presenter.FavPresenter;
import com.example.arch_lab2.fav_products.presenter.IFavpresenter;
import com.example.arch_lab2.model.local_source.ProductLocalDataSource;
import com.example.arch_lab2.model.pojo.Product;
import com.example.arch_lab2.model.remote_source.ProductRemoteDataSource;
import com.example.arch_lab2.model.repository.Repository;

import java.util.List;

public class FavProductsActivity  extends AppCompatActivity implements IFavView, OnFavClickListener{
    RecyclerView rv;
    Adapter adapter;
    LinearLayoutManager layoutManager;
    IFavpresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_products);
        rv = findViewById(R.id.favProductsRV);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        presenter = new FavPresenter(this, Repository.getInstance(ProductLocalDataSource.getInstance(this), ProductRemoteDataSource.getInstance()));
        LiveData<List<Product>> liveData = presenter.getLocalData();
        liveData.observe(this, products -> {
            adapter = new Adapter(products, this);
            rv.setAdapter(adapter);
        });

    }

    @Override
    public void onUnFavClick(Product product) {
        presenter.remopveFromFav(product);
        adapter.notifyDataSetChanged();
    }
}
