package com.example.walmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.walmart.databinding.ActivityShoppingBinding

class ShoppingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Interaction between Activity and Layout
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var curIntent = intent // getIntent() in Java
        val temp = curIntent.getSerializableExtra("user")
        val user = temp as User
        binding.username.text = "Welcome " + user.username

        // click category
        binding.electronics.setOnClickListener {
            Toast.makeText(this, "electronics is clicked", Toast.LENGTH_SHORT).show()
        }
        binding.clothing.setOnClickListener {
            Toast.makeText(this, "clothing is clicked", Toast.LENGTH_SHORT).show()
        }
        binding.beauty.setOnClickListener {
            Toast.makeText(this, "beauty is clicked", Toast.LENGTH_SHORT).show()
        }
        binding.food.setOnClickListener {
            Toast.makeText(this, "food is clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
