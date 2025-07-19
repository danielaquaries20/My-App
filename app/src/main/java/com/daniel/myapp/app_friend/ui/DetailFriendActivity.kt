package com.daniel.myapp.app_friend.ui

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.daniel.myapp.R
import com.daniel.myapp.databinding.ActivityDetailFriendBinding
import androidx.core.graphics.drawable.toDrawable

class DetailFriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFriendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_detail_friend)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_friend)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()
    }
    private fun initView() {
        binding.name = intent.getStringExtra("nama")
        binding.school = intent.getStringExtra("sekolah")
        binding.resImg = byteArrayToDrawable(intent.getByteArrayExtra("foto"))
    }

    private fun byteArrayToDrawable(byteArray: ByteArray?): Drawable? {
        return if (byteArray != null) {
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

            val drawable = bitmap.toDrawable(resources)
            drawable
        } else null
    }
}