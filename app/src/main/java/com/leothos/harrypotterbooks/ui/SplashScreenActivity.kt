package com.leothos.harrypotterbooks.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Simple splash screen activity use to perform a smooth transition when the application is loading.
 * The goal is to prevent from showing a dummy white screen page before the main activity starts.
 * */

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
