package com.daniel.myapp.app_tour.ui.home

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.crocodic.core.base.activity.CoreActivity
import com.crocodic.core.data.CoreSession
import com.crocodic.core.extension.openActivity
import com.daniel.myapp.R
import com.daniel.myapp.app_tour.ui.login.AuthViewModel
import com.daniel.myapp.app_tour.ui.login.LoginActivity
import com.daniel.myapp.databinding.ActivityHomeTourBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeTourActivity :
    CoreActivity<ActivityHomeTourBinding, AuthViewModel>(R.layout.activity_home_tour) {

    @Inject
    lateinit var session: CoreSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val desc = session.getInt(LoginActivity.ID).toString() + "\n" + session.getString(
            LoginActivity.EMAIL
        ) + "\n" + session.getString(LoginActivity.USERNAME) + "\n" + session.getString(
            LoginActivity.FIRST_NAME
        ) + " " + session.getString(LoginActivity.LAST_NAME)

        binding.tvDetail.text = desc

        binding.btnLogout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnLogout -> logout()
        }
    }

    private fun logout() {
        session.clearAll()
        session.setValue(CoreSession.PREF_UID, 0)
        finishAffinity()
        openActivity<LoginActivity>()
    }
}