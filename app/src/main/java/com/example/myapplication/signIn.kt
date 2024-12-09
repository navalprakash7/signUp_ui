package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signIn : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference

    //use for passing data in WelcomeActivity.kt file
    companion object {
        const val KEY_NAME = "com.example.myapplication.signIn.name"
        const val KEY_UNAME = "com.example.myapplication.signIn.uname"
        const val KEY_EMAIL = "com.example.myapplication.signIn.email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signin)

        val etUsernameSignIn = findViewById<TextInputEditText>(R.id.etUsernameSignIn)
        val etUsernameSignInPass = findViewById<TextInputEditText>(R.id.etUsernameSignInPass)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)

        btnSignIn.setOnClickListener {
            val etUsernameSignInString = etUsernameSignIn.text.toString().trim()
            val etUsernameSignInPassString = etUsernameSignInPass.text.toString().trim()

            if (etUsernameSignInString.isNotEmpty() && etUsernameSignInPassString.isNotEmpty()) {
                readData(etUsernameSignInString, etUsernameSignInPassString)
            } else {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(username: String, password: String) {
        // Access Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        // Retrieve the user data from Firebase using the username
        databaseReference.child(username).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                // Retrieve values from Firebase
                val storedPass = snapshot.child("pass").value?.toString()?.trim() // Ensure "pass" is used for password
                val name = snapshot.child("name").value?.toString()
                val uname = snapshot.child("uname").value?.toString()
                val email = snapshot.child("email").value?.toString()

                // Trim the entered password to avoid any accidental spaces
                val enteredPass = password.trim()

                // Verify if the entered password matches the stored password
                if (storedPass == enteredPass) {
                    val intentWelcome = Intent(this, WelcomeActivity::class.java)
                    intentWelcome.putExtra(KEY_NAME, name)
                    intentWelcome.putExtra(KEY_UNAME, uname)
                    intentWelcome.putExtra(KEY_EMAIL, email)
                    startActivity(intentWelcome)
                } else {
                    Toast.makeText(this, "Incorrect password, please try again", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "User not found, please sign up first", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to retrieve data from the database", Toast.LENGTH_SHORT).show()
        }
    }
}
