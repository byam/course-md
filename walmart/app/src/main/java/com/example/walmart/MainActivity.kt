package com.example.walmart

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.example.walmart.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val existingUsers = arrayListOf(
        User("Harry", "Potter", "bya.mng@gmail.com", "hp123"),
        User("Hermione", "Granger", "hg@gmail.com", "password234"),
        User("Ron", "Weasley", "rw@gmail.com", "password345"),
        User("Albus", "Dumbledore", "ad@gmail.com", "password456"),
        User("Lord", "Voldemort", "lv@gmail.com", "password567")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Interaction between Activity and Layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // sign in
        binding.signInButton.setOnClickListener {
            val email = binding.emailAddress.text.toString()
            val password = binding.password.text.toString()

            val user = findUserByEmailAndPassword(email, password)

            if (user != null) {
                Toast.makeText(this, "User found: ${user.firstName} ${user.lastName}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ShoppingActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            } else {
                Toast.makeText(this, "User not found or incorrect credentials", Toast.LENGTH_SHORT).show()
            }
        }

        // create account
        val createUserLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == Activity.RESULT_OK) {
                // Get the new user as a serialized extra
                val newUser = result.data?.getSerializableExtra("newUser") as User?
                newUser?.let {
                    // Add the new user to the existing users list
                    existingUsers.add(it)
                }
            }
        }

        binding.createAccountButton.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            createUserLauncher.launch(intent)
        }

        // forgot password
        binding.forgotPassword.setOnClickListener {
            Toast.makeText(this, "Forgot Password Clicked!", Toast.LENGTH_SHORT).show()

            var valid = true
            val userEmail = binding.emailAddress.text
            if(userEmail.isEmpty()) {
                binding.emailAddress.error = "Email address is required!"
                valid = false
            }

            if(valid) {
                // Find the user in the existingUsers ArrayList
                val user = existingUsers.find { it.username == userEmail.toString() }

                user?.let {
                    // Create the send intent
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "message/rfc822" // Use "text/plain" for generic send handlers
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(user.username)) // Receiver's email
                        putExtra(Intent.EXTRA_SUBJECT, "Your Password")
                        putExtra(Intent.EXTRA_TEXT, "Your password is: ${user.password}")
                    }

                    // Try to invoke the intent
                    try {
                        startActivity(Intent.createChooser(sendIntent, "Send password..."))
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(this, "No email client installed", Toast.LENGTH_SHORT).show()
                    }
                } ?: run {
                    // User not found
                    Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun findUserByEmailAndPassword(email: String, password: String): User? {
        return existingUsers.firstOrNull { it.username == email && it.password == password }
    }
}
