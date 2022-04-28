package com.amlan.test

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TableRow.LayoutParams
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amlan.test.utils.Constants

class CellsActivity : AppCompatActivity() {

    private var numCells: Int = Int.MIN_VALUE
    private lateinit var tableLayout: TableLayout
    private val TAG = CellsActivity::class.java.simpleName

    // Lets make no of colums to be 3
    private val numColums = 3;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cells)

        if (intent.hasExtra(Constants.NUM_CELLS)) {
            numCells = intent.getIntExtra(Constants.NUM_CELLS, Int.MIN_VALUE)
        }

        if (numCells == Int.MIN_VALUE) {
            Toast.makeText(this, "The number is not suitable to proceed", Toast.LENGTH_SHORT).show()
            return
        }

        tableLayout = findViewById(R.id.table_layout)
        showCells(numCells)
    }

    private fun showCells(numCells: Int) {
        Log.e(TAG, "No of cells to be printed: " + numCells)
        tableLayout.removeAllViews()
        var tableRow: TableRow? = null
        for (i in 1..numCells) {

            // Create a tablerow for each 3rd(numColums) occurance
            if (i % numColums == 1) {
                if (tableRow != null) {
                    tableLayout.addView(tableRow)
                }
                tableRow = TableRow(this)


                // determine if its L-->R or R-->L
                if ((i / 3) % 2 == 0) {
                    // L--> R( TODO So set gravity start)
                    val layoutParams = TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                    layoutParams.gravity = Gravity.LEFT
//                    tableLayout.layoutDirection = View.LAYOUT_DIRECTION_LTR
                    tableRow.apply {
                        weightSum = 3.0f
                        this.layoutParams = layoutParams
                    }

                } else {
                    // R-->L( TODO Set gravity right )
                    val layoutParams = TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                    layoutParams.gravity = Gravity.END
                    tableRow.layoutDirection = View.LAYOUT_DIRECTION_RTL
                    tableRow.weightSum = 3.0f
                    tableRow.layoutParams = layoutParams

                }
            }

            val view = createSingleCell(i)
            tableRow?.addView(view)
        }

        // Add the remaining views at the end of the loop

        tableLayout.addView(
            tableRow
        )
    }

    private fun createSingleCell(value: Int): TextView {
        val textView = TextView(this)
        textView.setText(value.toString())
        val layoutParams = TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.weight = 1.0f
        textView.layoutParams = layoutParams
        textView.gravity = Gravity.CENTER
        return textView

    }
}
