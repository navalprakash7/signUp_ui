package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class splashScreen : AppCompatActivity() {

    private val splashScreenDuration = 2000L // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Launch a coroutine on the lifecycle scope
        lifecycleScope.launch {
            delay(splashScreenDuration)
            val intent = Intent(this@splashScreen, signUp::class.java)
            startActivity(intent)
            finish() // Close the splash activity
        }
    }
}
