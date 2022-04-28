package com.amlan.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.amlan.test.utils.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var numCellsEditText: EditText
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numCellsEditText = findViewById(R.id.no_of_cells_edit_text)
        nextButton = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            if (numCellsEditText.text.isEmpty() || isValidInteger().not()) {
                numCellsEditText.setError("Enter Some valid int value")
                return@setOnClickListener
            }
            val intent = Intent(this, CellsActivity::class.java)
            intent.putExtra(Constants.NUM_CELLS, Integer.parseInt(numCellsEditText.text.toString().trim()))
            startActivity(intent)
        }
    }

    private fun isValidInteger(): Boolean {
        val value = numCellsEditText.text.toString().trim()
        try {
            val intValue = Integer.parseInt(value)
            return intValue >= 1
        } catch (e: Exception) {
            return false
        }
    }
}
