package com.example.lab3_1

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import android.content.*
import java.util.Date
import java.util.Locale

class BoundService : Service() {
    private val myBinder = MyLocalBinder()


    override fun onBind(intent: Intent): IBinder {
        return myBinder
    }
    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }
    inner class MyLocalBinder : Binder() {
        fun getService(): BoundService {
            return this@BoundService
        }
    }
}
