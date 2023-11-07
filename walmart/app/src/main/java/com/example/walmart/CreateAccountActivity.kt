package com.example.walmart

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.walmart.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Interaction between Activity and Layout
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // create account
        binding.createAccountButton.setOnClickListener {
            var isValid = true

            val firstName = binding.firstName.text
            val lastName = binding.lastName.text
            val email = binding.emailAddress.text
            val password = binding.password.text

            // Validate first name
            if (firstName.isEmpty()) {
                binding.firstName.error = "First name is required"
                isValid = false
            }

            // Validate last name
            if (lastName.isEmpty()) {
                binding.lastName.error = "Last name is required"
                isValid = false
            }

            // Validate email
            if (email.isEmpty()) {
                binding.emailAddress.error = "Email is required"
                isValid = false
            }

            // Validate password
            if (password.isEmpty()) {
                binding.password.error = "Password is required"
                isValid = false
            }

            // If all fields are valid, proceed with creating the account
            if (isValid) {
                val newUser = User(firstName.toString(), lastName.toString(), email.toString(), password.toString())
                // Your code to handle the creation of a new account
                Toast.makeText(this, "User(${newUser.username}) is created!", Toast.LENGTH_SHORT).show()

                val returnIntent = intent
                returnIntent.putExtra("newUser", newUser)
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        }
    }
}
