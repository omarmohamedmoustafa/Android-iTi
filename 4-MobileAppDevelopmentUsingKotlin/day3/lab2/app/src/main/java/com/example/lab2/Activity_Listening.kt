package com.example.lab2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.ak_three2.R
import java.io.File

class Activity_Listening : AppCompatActivity() {
    private lateinit var urlEditText: EditText
    private lateinit var downloadButton: Button
    private lateinit var downloadReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        urlEditText = findViewById(R.id.urlEditText)
        downloadButton = findViewById(R.id.downloadButton)

        downloadButton.setOnClickListener {
            val url = urlEditText.text.toString()
            if (url.isNotEmpty()) {
                startDownloadService(url)
            } else {
                Toast.makeText(this, "Please enter a URL", Toast.LENGTH_SHORT).show()
            }
        }

        downloadReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    "DOWNLOAD_COMPLETE" -> {
                        val imagePath = intent.getStringExtra("IMAGE_PATH")
                        runOnUiThread {
                            if (imagePath != null) {
                                showDownloadedImage(imagePath)
                            } else {
                                Toast.makeText(
                                    this@Activity_Listening,
                                    "Download complete but no image path received",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                    "DOWNLOAD_FAILED" -> {
                        val error = intent.getStringExtra("ERROR") ?: "Unknown error"
                        runOnUiThread {
                            Toast.makeText(this@Activity_Listening, "Download failed: $error", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

        val filter = IntentFilter().apply {
            addAction("DOWNLOAD_COMPLETE")
            addAction("DOWNLOAD_FAILED")
        }
        registerReceiver(downloadReceiver, filter,RECEIVER_EXPORTED)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(downloadReceiver)
    }

    private fun startDownloadService(url: String) {
        val intent = Intent(this, Downloader::class.java).apply {
            putExtra("URL", url)
        }
        startService(intent)
    }

    private fun showDownloadedImage(imagePath: String) {
        try {
            val file = File(imagePath)
            if (!file.exists()) {
                Toast.makeText(this, "Downloaded file not found", Toast.LENGTH_SHORT).show()
                return
            }

            val intent = Intent(this, ImageViewActivity::class.java).apply {
                putExtra("IMAGE_PATH", imagePath)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to display image", Toast.LENGTH_LONG).show()
        }
    }
}