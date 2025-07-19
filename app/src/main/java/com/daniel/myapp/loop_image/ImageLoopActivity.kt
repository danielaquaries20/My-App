package com.daniel.myapp.loop_image

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.daniel.myapp.R

class ImageLoopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_image_loop)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputJumlah = findViewById<EditText>(R.id.inputJumlah)
        val btnTampilkan = findViewById<Button>(R.id.btnTampilkan)
        val layoutGambar = findViewById<LinearLayout>(R.id.layoutGambar)

        btnTampilkan.setOnClickListener {
            val jumlah = inputJumlah.text.toString().toIntOrNull() ?: 0
            layoutGambar.removeAllViews() // Bersihkan gambar sebelumnya

            for (i in 1..jumlah) {
                val imageView = ImageView(this)
                imageView.setImageResource(R.drawable.nasi_padang) // ganti dengan nama file gambarmu
                imageView.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 16, 0, 16) // beri jarak antar gambar
                }

                layoutGambar.addView(imageView)
            }
        }
    }
}