package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Retrieve data from intent with null safety
        val name = intent.getStringExtra(signIn.KEY_NAME) ?: "Guest"
        val username = intent.getStringExtra(signIn.KEY_UNAME) ?: "N/A"
        val email = intent.getStringExtra(signIn.KEY_EMAIL) ?: "N/A"

        // Find the TextViews by ID
        val textView1 = findViewById<TextView>(R.id.textView1)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val textView3 = findViewById<TextView>(R.id.textView3)

        // Set the text dynamically
        textView1.text = "Hello, $name"
        textView2.text = "Your username: $username"
        textView3.text = "Your email: $email"
    }
}
