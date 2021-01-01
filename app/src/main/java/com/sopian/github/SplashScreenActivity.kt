package com.sopian.github

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Executors.newSingleThreadScheduledExecutor().schedule({
            processToMain()
        }, 1, TimeUnit.SECONDS)
    }

    private fun processToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}