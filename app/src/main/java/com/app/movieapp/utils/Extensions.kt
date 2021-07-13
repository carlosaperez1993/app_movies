package com.app.movieapp.utils

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.toast(message : String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}