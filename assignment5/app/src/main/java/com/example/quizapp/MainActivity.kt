package com.example.quizapp

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.quizapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Interaction between Activity and Layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // submit button
        binding.submit.setOnClickListener {
            if (validateAnswers()) {
                val score = calculateScore()
                showResultDialog(score)
            } else {
                Toast.makeText(this, "Please pick answer for all questions.", Toast.LENGTH_SHORT).show()
            }
        }

        // reset button
        binding.reset.setOnClickListener {
            // Clear all selections
            binding.q1.clearCheck()
            binding.q2a1.isChecked = false
            binding.q2a2.isChecked = false
            binding.q2a3.isChecked = false
            binding.q2a4.isChecked = false

            Toast.makeText(this, "All answers cleared!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateAnswers(): Boolean {
        // Check if at least one answer is checked in Q1
        val q1Answered = binding.q1.checkedRadioButtonId != -1

        // Check if at least one answer is checked in Q2
        val q2Answered = binding.q2a1.isChecked || binding.q2a2.isChecked ||
                binding.q2a3.isChecked || binding.q2a4.isChecked

        // Return true only if both questions have been answered
        return q1Answered && q2Answered
    }

    private fun calculateScore(): Int {
        var score = 0

        // Assuming each correct answer is worth 50 points
        // q1: a2 is correct
        if (binding.q1a1.isChecked) score += 50

        // q2: a1 and a3 are correct
        if (binding.q2a1.isChecked && !binding.q2a2.isChecked && binding.q2a3.isChecked && !binding.q2a4.isChecked) score += 50

        return score
    }

    private fun showResultDialog(score: Int) {
        val currentDateAndTime = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).format(Date())
        val message = if (score >= 50) {
            "Congratulations! You submitted on $currentDateAndTime. You achieved $score%"
        } else {
            "Sorry! You submitted on $currentDateAndTime. You achieved $score%"
        }

        AlertDialog.Builder(this)
            .setTitle("Quiz Result")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}
