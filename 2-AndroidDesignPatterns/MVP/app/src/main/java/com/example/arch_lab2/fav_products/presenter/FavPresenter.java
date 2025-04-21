package com.example.arch_lab2.fav_products.presenter;

import androidx.lifecycle.LiveData;

import com.example.arch_lab2.fav_products.view.IFavView;
import com.example.arch_lab2.model.pojo.Product;
import com.example.arch_lab2.model.repository.IRepository;

import java.util.List;

public class FavPresenter implements IFavpresenter {
    IFavView view;
    IRepository repo;

    public FavPresenter(IFavView view, IRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public LiveData<List<Product>> getLocalData() {
        return repo.getStoredProducts();
    }

    @Override
    public void remopveFromFav(Product product) {
        repo.deleteProduct(product);
    }
}
