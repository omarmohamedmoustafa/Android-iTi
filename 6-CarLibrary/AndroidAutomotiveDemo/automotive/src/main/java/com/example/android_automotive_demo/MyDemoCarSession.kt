package com.example.android_auto

import android.content.Intent
import android.util.Log
import androidx.car.app.Screen
import androidx.car.app.Session
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyDemoCarSession : Session() , DefaultLifecycleObserver{

    init {
        lifecycle.addObserver(this)
    }
    override fun onCreate(owner: LifecycleOwner) {
        //log the lifecycle event
        Log.i("SessionLifeCycle", "onCreate: ")
        super.onCreate(owner)
    }
    override fun onDestroy(owner: LifecycleOwner) {
        //log the lifecycle event
        Log.i("SessionLifeCycle", "onDestroy: ")
        super.onDestroy(owner)
    }
    override fun onStart(owner: LifecycleOwner) {
        //log the lifecycle event
        Log.i("SessionLifeCycle", "onStart: ")

        super.onStart(owner)
    }
    override fun onStop(owner: LifecycleOwner) {
        //log the lifecycle event
        Log.i("SessionLifeCycle", "onStop: ")
        super.onStop(owner)
    }
    override fun onResume(owner: LifecycleOwner) {
        //log the lifecycle event
        Log.i("SessionLifeCycle", "onResume: ")
        super.onResume(owner)
    }
    override fun onPause(owner: LifecycleOwner) {
        //log the lifecycle event
        Log.i("SessionLifeCycle", "onPause: ")
        super.onPause(owner)
    }

    override fun onCreateScreen(intent: Intent): Screen {
        return MyScreen(carContext)
    }
}