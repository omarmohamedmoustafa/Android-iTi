package com.example.lab2

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ak_three2.R

class ImageViewActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        imageView = findViewById(R.id.imageView)
        val imagePath = intent?.getStringExtra("IMAGE_PATH")

        if (imagePath == null) {
            Toast.makeText(this, "No image to display", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        try {
            val bitmap = BitmapFactory.decodeFile(imagePath)
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap)
            } else {
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show()
                finish()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}