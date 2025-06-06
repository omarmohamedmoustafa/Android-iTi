package com.example.android_automotive_demo

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Template

class MySecondScreen(context: CarContext, private val itemIndex: Int) : Screen(context) {
    // This second screen will show the details of the selected item.
    override fun onGetTemplate(): Template {
        return MessageTemplate.Builder("Details for Item $itemIndex")
            .setHeaderAction(Action.BACK)
            .build()
    }
}