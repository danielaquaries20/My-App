package com.daniel.myapp.kalkulator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.daniel.myapp.R

class BasicCalculatorActivity : AppCompatActivity() {

    private lateinit var input1: EditText
    private lateinit var input2: EditText

    private lateinit var output: TextView

    private var operator: String = "+" //"x" //":" //"-"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_basic_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)

        output = findViewById(R.id.textOutput)

        val btnTambah = findViewById<Button>(R.id.btnTambah)
        val btnKurang = findViewById<Button>(R.id.btnKurang)
        val btnKali = findViewById<Button>(R.id.btnKali)
        val btnBagi = findViewById<Button>(R.id.btnBagi)
        val btnHitung = findViewById<Button>(R.id.btnHitung)

        btnTambah.setOnClickListener { operator = "+" }
        btnKurang.setOnClickListener { operator = "-" }
        btnKali.setOnClickListener { operator = "x" }
        btnBagi.setOnClickListener { operator = ":" }

        btnHitung.setOnClickListener {
            hitung()
        }
    }

    private fun hitung() {
        val nilai1 = input1.text.toString().toDoubleOrNull()
        val nilai2 = input2.text.toString().toDoubleOrNull()

        if (nilai1 == null || nilai2 == null) {
            Toast.makeText(this, "Masukkan angka yang valid!", Toast.LENGTH_SHORT).show()
        } else {
            val hasil = when (operator) {
                "+" -> nilai1 + nilai2
                "-" -> nilai1 - nilai2
                "x" -> nilai1 * nilai2
                ":" -> if (nilai2 != 0.0) nilai1 / nilai2 else {
                    Toast.makeText(this, "Tidak bisa bagi 0", Toast.LENGTH_SHORT).show()
                    return
                }
                else -> 0.0
            }

            output.text = hasil.toString()
        }

    }
}