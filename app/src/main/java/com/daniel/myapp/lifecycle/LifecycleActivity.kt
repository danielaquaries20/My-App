package com.daniel.myapp.lifecycle

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.daniel.myapp.R

class LifecycleActivity : AppCompatActivity() {

    private lateinit var tvCount : TextView
    private var counter : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lifecycle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvCount = findViewById(R.id.tv_count)
        tvCount.text = counter.toString()
    }

    private fun counting() {
        counter++
        tvCount.text = counter.toString()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()
        counting()
    }


}