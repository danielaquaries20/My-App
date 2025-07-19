package com.daniel.myapp.app_friend.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.daniel.myapp.R
import com.daniel.myapp.app_friend.session.SharedPref
import com.daniel.myapp.databinding.ActivityListFriendBinding
import com.daniel.myapp.databinding.ActivityTestSessionBinding

class TestSessionActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPref

    private lateinit var binding: ActivityTestSessionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityTestSessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPref = SharedPref(this)

        val name = sharedPref.getName()

        binding.tvName.text = if (name.isNullOrEmpty()) "Data Nama Kosong" else name

        binding.btnSave.setOnClickListener {
            val newName = binding.etName.text.toString().trim()
            sharedPref.saveName(newName)
            binding.tvName.text = newName
        }

    }
}