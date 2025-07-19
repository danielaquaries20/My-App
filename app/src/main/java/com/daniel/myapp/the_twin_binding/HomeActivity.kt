package com.daniel.myapp.the_twin_binding

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.daniel.myapp.R
import com.daniel.myapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    /*private lateinit var tvName1: TextView
    private lateinit var tvName2: TextView
    private lateinit var tvName3: TextView

    private lateinit var tvSchool1: TextView
    private lateinit var tvSchool2: TextView
    private lateinit var tvSchool3: TextView

    private lateinit var card1: CardView
    private lateinit var card2: CardView
    private lateinit var card3: CardView*/

//    private lateinit var binding: ActivityHomeBinding
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        binding = ActivityHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*tvName1 = findViewById(R.id.tv_name1)
        tvName2 = findViewById(R.id.tv_name2)
        tvName3 = findViewById(R.id.tv_name3)

        tvSchool1 = findViewById(R.id.tv_school1)
        tvSchool2 = findViewById(R.id.tv_school2)
        tvSchool3 = findViewById(R.id.tv_school3)

        card1 = findViewById(R.id.card1)
        card2 = findViewById(R.id.card2)
        card3 = findViewById(R.id.card3)*/

        binding.card1.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("id", 1)
                putExtra("nama", binding.tvName1.text)
                putExtra("sekolah", binding.tvSchool1.text)
            }
            startActivity(intent)
        }

        binding.card2.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java).also {
                it.putExtra("id", 2)
                it.putExtra("nama", binding.tvName2.text)
                it.putExtra("sekolah", binding.tvSchool2.text)
            }
            startActivity(intent)

        }

        binding.card3.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            with(intent) {
                putExtra("id", 3)
                putExtra("nama", binding.tvName3.text)
                putExtra("sekolah", binding.tvSchool3.text)
            }
            startActivity(intent)
        }
    }
}