package com.example.arch_lab2.model.pojo;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products_table")
public class Product {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;
    private double price;
    private double rating;
    private String thumbnail;

    // Constructors
    public Product() {
    }

    public Product(String title, String description, double price, double rating, String thumbnail) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.thumbnail = thumbnail;
    }

}
