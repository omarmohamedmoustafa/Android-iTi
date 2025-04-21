package com.example.arch_lab2;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.arch_lab2.all_products.view.AllProductsActivity;
import com.example.arch_lab2.fav_products.view.FavProductsActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAll;
    Button btnFav;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAll = findViewById(R.id.btnShowAll);
        btnFav = findViewById(R.id.btnShowFav);
        btnExit = findViewById(R.id.btnExit);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllProductsActivity.class);
                startActivity(intent);
            }
        });
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavProductsActivity.class);
                startActivity(intent);

            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
