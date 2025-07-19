package com.daniel.myapp.app_friend.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.daniel.myapp.R
import com.daniel.myapp.app_friend.database.entity.FriendEntity
import com.daniel.myapp.app_friend.ui.viewmodel.FriendVMFactory
import com.daniel.myapp.app_friend.ui.viewmodel.FriendViewModel
import com.daniel.myapp.databinding.ActivityManageFriendBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ManageFriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageFriendBinding

    private lateinit var viewModel: FriendViewModel

    private var idFriend: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityManageFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModelFactory = FriendVMFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[FriendViewModel::class.java]

        initView()

        binding.btnAdd.setOnClickListener {
            addData()
        }
    }

    private fun initView() {
        idFriend = intent.getIntExtra("id", 0)
        if (idFriend == 0) return

        binding.tvTitle.text = "Your Friend Profile"
        binding.etName.isEnabled = false
        binding.etSchool.isEnabled = false
        binding.etHobby.isEnabled = false
        binding.btnAdd.isVisible = false

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.getFriendById(idFriend).collect { friend ->
                        binding.etName.editText?.setText(friend.name)
                        binding.etSchool.editText?.setText(friend.school)
                        binding.etHobby.editText?.setText(friend.hobby)
                    }
                }
            }
        }

    }

    private fun addData() {
        val name = binding.etName.editText?.text.toString().trim()
        val school = binding.etSchool.editText?.text.toString().trim()
        val hobby = binding.etHobby.editText?.text.toString().trim()

        if (name.isEmpty() || school.isEmpty() || hobby.isEmpty()) {
            Toast.makeText(this, "Please fill the blank form", Toast.LENGTH_SHORT).show()
            return
        }

        val data = FriendEntity(name, school, hobby)
        lifecycleScope.launch {
            viewModel.insertFriend(data)
        }
        finish()
    }


}