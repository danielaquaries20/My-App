package com.daniel.myapp.test.lifecycle

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.daniel.myapp.R

class CobaLifecycleActivity : AppCompatActivity() {

    private lateinit var tvCount: TextView

    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_coba_lifecycle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvCount = findViewById(R.id.tv_count)
        showCount()

    }

    private fun showCount() {
        count++
        tvCount.text = count.toString()
    }

    override fun onStop() {
        showCount()
        super.onStop()
    }
}