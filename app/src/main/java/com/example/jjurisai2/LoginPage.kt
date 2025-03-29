package com.example.jjurisai2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jjurisai2.databinding.ActivityLoginpageBinding  // Correct View Binding import

class LoginPage : AppCompatActivity() {
    private val binding: ActivityLoginpageBinding by lazy {
        ActivityLoginpageBinding.inflate(layoutInflater)  // Correct way to initialize View Binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("email", null)
        val savedPassword = sharedPreferences.getString("password", null)

        // Sign-in button click listener
        binding.signin.setOnClickListener {
            val enteredEmail = binding.emailAddress.text.toString().trim()
            val enteredPassword = binding.password.text.toString().trim()

            if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (enteredEmail == savedEmail && enteredPassword == savedPassword) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()  // Finish the login activity
            } else {
                Toast.makeText(this, "Invalid Email or Password!", Toast.LENGTH_SHORT).show()
            }
        }

        // Sign-up button click listener
        binding.signup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
