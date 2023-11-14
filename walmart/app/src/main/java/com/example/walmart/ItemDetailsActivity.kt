package com.example.walmart

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.walmart.databinding.ActivityItemDetailsBinding

class ItemDetailsActivity: AppCompatActivity()  {
    private lateinit var binding: ActivityItemDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Interaction between Activity and Layout
        binding = ActivityItemDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Fetch intent
        val curIntent = intent // getIntent() in Java
        val product = curIntent.getSerializableExtra("product") as Product
        binding.itemName.text = product.productName
        binding.itemDescription.text = product.productDescription
        binding.itemPrice.text = "$ ${product.cost}"
        binding.itemImage.setImageResource(product.imageId)

        // go to home
        binding.btnHome.setOnClickListener {
            val intent = Intent(this, ShoppingActivity::class.java)
            this.startActivity(intent)
        }
    }
}
