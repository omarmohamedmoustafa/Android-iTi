package com.example.arch_lab2.model.local_source;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.arch_lab2.model.pojo.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    private static ProductDatabase instance = null;
    public abstract ProductDAO ProductDAO();
    public static synchronized ProductDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ProductDatabase.class, "productsdb")
                    .build();
        }
        return instance;
    }
}
