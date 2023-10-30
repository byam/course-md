package com.example.dinnerdeciderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.dinnerdeciderapp.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val foodList = arrayListOf("Hamburger", "Pizza", "Mexican", "American", "Chinese")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Interaction between Activity and Layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Decide button
        binding.btnDecide.setOnClickListener{
            // Randomly select a value from the ArrayList
            val randomIndex = Random.nextInt(foodList.size)
            val randomFood = foodList[randomIndex]

            binding.tvDinnerName.text = randomFood
        }

        // Add food
        binding.btnAddFood.setOnClickListener {
            foodList.add(binding.etAddFood.text.toString())
            binding.etAddFood.text.clear()
        }
    }
}
