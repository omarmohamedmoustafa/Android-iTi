package com.example.lab3_1

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var myService: BoundService
    private var isBound: Boolean = false
    private lateinit var textViewDateTime: TextView

    private val myConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as BoundService.MyLocalBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewDateTime = findViewById(R.id.textViewDateTime)

        val intent = Intent(this, BoundService::class.java)
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE)
    }

    fun onGetTimeClick(view: View) {
        if (isBound) {
            val currentTime = myService.getCurrentTime()
            textViewDateTime.text = currentTime
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(myConnection)
            isBound = false
        }
    }
}