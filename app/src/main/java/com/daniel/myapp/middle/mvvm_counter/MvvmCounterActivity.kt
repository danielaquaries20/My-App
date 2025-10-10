package com.daniel.myapp.middle.mvvm_counter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.daniel.myapp.R
import com.daniel.myapp.databinding.ActivityMvvmCounterBinding

class MvvmCounterActivity : AppCompatActivity() {

    private lateinit var viewModel: MvvmCounterViewModel
    private lateinit var binding: ActivityMvvmCounterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_mvvm_counter)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_counter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[MvvmCounterViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


    }
}