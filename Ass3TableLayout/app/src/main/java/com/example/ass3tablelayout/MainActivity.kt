package com.example.ass3tablelayout

import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.setPadding
import com.example.ass3tablelayout.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Interaction between Activity and Layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // add button
        binding.btnAdd.setOnClickListener {
            Toast.makeText(this, "ADD button pressed", Toast.LENGTH_LONG).show()
            addRowToTable()
        }
    }
    private fun  addRowToTable(){
        val andVer = binding.andVer.text.toString()
        val andCodename = binding.andCodename.text.toString()

        // clear
        binding.andVer.text.clear()
        binding.andCodename.text.clear()

        // create new row
        val tableRow = TableRow(this).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT,
            )
            setPadding(5)
        }

        // Version Textview
        val versionView = TextView(this).apply {
            text = andVer
            layoutParams = TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f
            )
            setBackgroundColor(resources.getColor(R.color.red, null))
            setTextColor(resources.getColor(R.color.white, null))
            setPadding(5)
        }
        tableRow.addView(versionView)

        // Code name Textview
        val codeNameView = TextView(this).apply {
            text = andCodename
            layoutParams = TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f
            )
            setBackgroundColor(resources.getColor(R.color.red, null))
            setTextColor(resources.getColor(R.color.white, null))
            setPadding(5)
        }
        tableRow.addView(codeNameView)

        // Add the TableRow to the TableLayout
        binding.tableLayout.addView(tableRow)
    }
}
