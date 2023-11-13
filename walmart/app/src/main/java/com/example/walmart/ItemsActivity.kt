package com.example.walmart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.walmart.databinding.ActivityItemsBinding

class ItemsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemsBinding
    private var user: User?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Interaction between Activity and Layout
        binding = ActivityItemsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Fetch intent
        val curIntent = intent // getIntent() in Java
        user = curIntent.getSerializableExtra("user") as User
        binding.username.text = "Welcome ${user!!.username}"
        binding.categoryName.text = curIntent.getStringExtra("categoryName")
    }
}
