package com.example.lab2

import android.app.Service
import android.content.Intent
import android.os.Environment
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class Downloader : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val url = intent?.getStringExtra("URL") ?: run {
            return START_NOT_STICKY
        }
        thread {
            try {
                val imagePath = downloadImage(url)
                sendBroadcast(imagePath)
            } catch (e: Exception) {
                val errorIntent = Intent("DOWNLOAD_FAILED").apply {
                    putExtra("ERROR", e.message)
                }
                LocalBroadcastManager.getInstance(this).sendBroadcast(errorIntent)
            } finally {
                stopSelf()
            }
        }
        return START_NOT_STICKY
    }

    private fun downloadImage(urlString: String): String {
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        connection.connect()

        if (connection.responseCode != HttpURLConnection.HTTP_OK) {
            throw Exception("HTTP error code: ${connection.responseCode}")
        }

        val inputStream = connection.inputStream
        val fileName = "downloaded_${System.currentTimeMillis()}.jpg"
        val outputFile = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)

        FileOutputStream(outputFile).use { output ->
            inputStream.copyTo(output)
        }

        if (!outputFile.exists()) {
            throw Exception("File was not created at ${outputFile.absolutePath}")
        }

        return outputFile.absolutePath
    }

    private fun sendBroadcast(imagePath: String) {
        val intent = Intent("DOWNLOAD_COMPLETE").apply {
            putExtra("IMAGE_PATH", imagePath)
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}