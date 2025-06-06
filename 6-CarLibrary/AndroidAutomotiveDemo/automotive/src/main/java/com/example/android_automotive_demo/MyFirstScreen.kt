package com.example.android_automotive_demo

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.GridTemplate
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Template

class MyFirstScreen(context: CarContext) : Screen(context) {

    override fun onGetTemplate(): Template {
        val msgTemp = MessageTemplate.Builder("Hello, Automotive World!")
            .setTitle("Welcome to My Car App")
            .build()

        val strip = ActionStrip.Builder()
            .addAction(Action.BACK)
            .build()
        val template = GridTemplate.Builder()
            .setSingleList()

        return msgTemp
    }
}
