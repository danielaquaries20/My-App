package com.daniel.myapp.middle.counter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.crocodic.core.base.activity.CoreActivity
import com.daniel.myapp.R
import com.daniel.myapp.databinding.ActivityCounterBinding
import com.daniel.myapp.middle.aa_shared_pref.MiddleSession
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CounterActivity : CoreActivity<ActivityCounterBinding, CounterViewModel>(R.layout.activity_counter) {

//    private lateinit var viewModel: CounterViewModel
//    private lateinit var binding: ActivityCounterBinding

    // 2. Inisialisasi ViewModel menggunakan 'by viewModels()'.
    //    Ini adalah delegate dari library KTX yang akan
    //    mengurus semuanya untuk kita. Hilt akan bekerja
    //    di belakang layar untuk meng-inject semua dependensi
    //    yang dibutuhkan ViewModel.
//    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 1. Buat instance dari Repository.
        //    'applicationContext' aman digunakan di sini.
//        val session = MiddleSession(this)
        // 2. Buat instance dari Factory dan berikan repository kepadanya.
//        val factory = CounterViewModelFactory(session)

//        viewModel = ViewModelProvider(this)[CounterViewModel::class.java]
        // 3. Gunakan factory tersebut untuk membuat ViewModel.
//        viewModel = ViewModelProvider(this, factory)[CounterViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}


