package com.example.jjurisai2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jjurisai2.databinding.ActivitySignupBinding


@Suppress("NAME_SHADOWING")
class SignupActivity : AppCompatActivity() {
    private val binding:ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.signup.setOnClickListener{



            val name = binding.username.text.toString()
            val email = binding.emailAddress.text.toString()
            val password = binding.password.text.toString()

            // Save user data in SharedPreferences
            val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("name", name)
            editor.putString("email", email)
            editor.putString("password", password)
            editor.apply()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Save user credentials
                val sharedPreferences: SharedPreferences =
                    getSharedPreferences("UserPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("name", name)
                editor.putString("email", email)
                editor.putString("password", password)
                editor.apply()

                Toast.makeText(this, "Sign-up Successful!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Email & Password cannot be empty!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.signin.setOnClickListener{
            val intent=Intent(this, LoginPage::class.java)
            startActivity(intent)
        }
    }
}