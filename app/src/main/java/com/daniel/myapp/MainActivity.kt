package com.daniel.myapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.daniel.myapp.the_twin_binding.HomeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnShow: Button

    private lateinit var profile : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnShow = findViewById(R.id.btn_show)

//        btnShow.setOnClickListener { showAlertDialog() }
        btnShow.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            with(intent) {
                putExtra("id", 1)
                putExtra("nama", "Bella")
                putExtra("sekolah", "SMKN 11 Semarang")
            }
        }

        val msg : String? = "Halo There!"
        msg?.run { profile = this }
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Peringatan")
        builder.setMessage("Apakah kamu yakin ingin keluar?")

        builder.setPositiveButton("Ya") { dialog, which ->
            // Aksi ketika tombol "Ya" ditekan
            finish()
        }

        builder.setNegativeButton("Tidak") { dialog, which ->
            // Tutup dialog
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}