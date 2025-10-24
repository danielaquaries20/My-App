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
import com.crocodic.core.base.activity.CoreActivity
import com.daniel.myapp.R
import com.daniel.myapp.app_friend.database.entity.FriendEntity
import com.daniel.myapp.app_friend.ui.viewmodel.FriendViewModel
import com.daniel.myapp.databinding.ActivityManageFriendBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ManageFriendActivity :
    CoreActivity<ActivityManageFriendBinding, FriendViewModel>(R.layout.activity_manage_friend) {

    private var idFriend: Int = 0

    private lateinit var oldFriend: FriendEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()

        binding.btnAdd.setOnClickListener {
            if (idFriend == 0) {
                addData()
            } else {
                updateData()
            }
        }

        binding.btnEdit.setOnClickListener {
            stateViewEdit(true)
            /* binding.tvTitle.text = "UPDATE FRIEND"
             binding.etName.isEnabled = true
             binding.etSchool.isEnabled = true
             binding.etHobby.isEnabled = true
             binding.etPhone.isEnabled = true
             binding.btnAdd.text = "Update"
             binding.btnAdd.isVisible = true
             binding.btnEdit.isVisible = false*/
        }
    }

    private fun stateViewEdit(edit: Boolean = false) {
        if (edit) {
            binding.tvTitle.text = "UPDATE FRIEND"
            binding.btnAdd.text = "Update"
        } else binding.tvTitle.text =
            "Your Friend Profile"
        binding.etName.isEnabled = edit
        binding.etSchool.isEnabled = edit
        binding.etHobby.isEnabled = edit
        binding.etPhone.isEnabled = edit
        binding.btnAdd.isVisible = edit
        binding.btnEdit.isVisible = !edit
    }

    private fun initView() {
        idFriend = intent.getIntExtra("id", 0)
        if (idFriend == 0) return

        stateViewEdit(false)
        /*binding.tvTitle.text = "Your Friend Profile"
        binding.etName.isEnabled = false
        binding.etSchool.isEnabled = false
        binding.etHobby.isEnabled = false
        binding.etPhone.isEnabled = false
        binding.btnAdd.isVisible = false
        binding.btnEdit.isVisible = true*/

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.getFriendById(idFriend).collect { friend ->
                        binding.etName.editText?.setText(friend.name)
                        binding.etSchool.editText?.setText(friend.school)
                        binding.etHobby.editText?.setText(friend.hobby)
                        binding.etPhone.editText?.setText(friend.phone)
                        oldFriend = friend
                    }
                }
            }
        }

    }

    private fun updateData() {
        val name = binding.etName.editText?.text.toString().trim()
        val school = binding.etSchool.editText?.text.toString().trim()
        val hobby = binding.etHobby.editText?.text.toString().trim()

        if (name.isEmpty() || school.isEmpty() || hobby.isEmpty()) {
            Toast.makeText(this, "Please fill the blank form", Toast.LENGTH_SHORT).show()
            return
        }

        if (name == oldFriend.name && school == oldFriend.school && hobby == oldFriend.hobby) {
            Toast.makeText(this, "There is no change", Toast.LENGTH_SHORT).show()
            return
        }

        val data = oldFriend.copy(name = name, school = school, hobby = hobby).apply {
            id = idFriend
        }
        lifecycleScope.launch {
            viewModel.insertFriend(data)
        }
        finish()
    }


    private fun addData() {
        val name = binding.etName.editText?.text.toString().trim()
        val school = binding.etSchool.editText?.text.toString().trim()
        val hobby = binding.etHobby.editText?.text.toString().trim()
        val phone = binding.etPhone.editText?.text.toString().trim()

        if (name.isEmpty() || school.isEmpty() || hobby.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill the blank form", Toast.LENGTH_SHORT).show()
            return
        }

        val data = FriendEntity(name, school, hobby, phone)
        lifecycleScope.launch {
            viewModel.insertFriend(data)
        }
        finish()
    }


}