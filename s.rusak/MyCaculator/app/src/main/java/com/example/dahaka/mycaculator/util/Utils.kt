package com.example.dahaka.mycaculator.util

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast
import com.example.dahaka.mycaculator.App
import com.example.dahaka.mycaculator.di.component.AppComponent

val Activity.daggerComponent: AppComponent
    get() {
        return (applicationContext as App).daggerComponent
    }

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}