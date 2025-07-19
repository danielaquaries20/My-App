package com.daniel.myapp.the_twin_binding

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.daniel.myapp.R
import com.daniel.myapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    /*private lateinit var tvName: TextView
    private lateinit var tvSchool: TextView
    private lateinit var ivPp: ImageView*/

//    private lateinit var binding: ActivityDetailBinding
    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
//        setContentView(R.layout.activity_detail)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.name = intent.getStringExtra("nama")
        binding.school = intent.getStringExtra("sekolah")
        val id = intent.getIntExtra("id", 0)

        /*tvName = findViewById(R.id.tv_name)
        tvSchool = findViewById(R.id.tv_school)
        ivPp = findViewById(R.id.iv_pp)*/

        /*tvName.text = name
        tvSchool.text = school*/

        when (id) {
            1 -> {
                binding.ivPp.setImageResource(R.drawable.img_friend)
//                binding.resImg = AppCompatResources.getDrawable(this, R.drawable.img_friend)
            }

            2 -> {
                binding.ivPp.setImageResource(R.drawable.icon_friend_app_1)
//                binding.resImg = AppCompatResources.getDrawable(this, R.drawable.icon_friend_app_1)
            }

            3 -> {
                binding.ivPp.setImageResource(R.drawable.icon_friend_app_2)
//                binding.resImg = AppCompatResources.getDrawable(this, R.drawable.icon_friend_app_2)
            }
        }
    }
}