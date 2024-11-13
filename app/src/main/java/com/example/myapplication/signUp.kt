package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signUp : AppCompatActivity() {

    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signup)

        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPass = findViewById<TextInputEditText>(R.id.etPass)

        btnSignUp.setOnClickListener {
            val name = etName.text.toString()
            val uname = etUsername.text.toString()
            val email = etEmail.text.toString()
            val pass = etPass.text.toString()

            val user = User(name, uname, email, pass)

            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uname).setValue(user).addOnSuccessListener {
                etName.text?.clear()
                etUsername.text?.clear()
                etEmail.text?.clear()
                etPass.text?.clear()
                Toast.makeText(this,"User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
            }
        }

        val tvSignIn = findViewById<TextView>(R.id.tvSignIn)
        tvSignIn.setOnClickListener {
            val openSignInActivity = Intent(this, signIn::class.java)
            startActivity(openSignInActivity)
        }
    }
}
