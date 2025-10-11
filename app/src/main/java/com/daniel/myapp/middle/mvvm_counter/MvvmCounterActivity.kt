package com.daniel.myapp.middle.mvvm_counter

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.crocodic.core.base.activity.CoreActivity
import com.crocodic.core.extension.openActivity
import com.daniel.myapp.R
import com.daniel.myapp.databinding.ActivityMvvmCounterBinding
import com.daniel.myapp.middle.aa_shared_pref.MiddleSession
import com.daniel.myapp.middle.counter.CounterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MvvmCounterActivity : CoreActivity<ActivityMvvmCounterBinding, MvvmCounterViewModel>(R.layout.activity_mvvm_counter) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_mvvm_counter)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_counter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        viewModel = ViewModelProvider(this)[MvvmCounterViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

    }

}