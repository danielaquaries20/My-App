package com.daniel.myapp.app_tour.ui.settings

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.crocodic.core.base.activity.CoreActivity
import com.crocodic.core.data.CoreSession
import com.crocodic.core.extension.openActivity
import com.daniel.myapp.R
import com.daniel.myapp.app_tour.ui.login.AuthViewModel
import com.daniel.myapp.app_tour.ui.login.LoginActivity
import com.daniel.myapp.databinding.ActivitySettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity :
    CoreActivity<ActivitySettingsBinding, AuthViewModel>(R.layout.activity_settings) {

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

        checkBiometricCapability()

        binding.swBiometric.isChecked = session.getBoolean(BIOMETRIC_STATUS)
        binding.swBiometric.setOnCheckedChangeListener { compoundButton, b ->
            session.setValue(BIOMETRIC_STATUS, b)
        }

        binding.btnLogout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnLogout -> logout()
        }
    }

    private fun logout() {

        val bioSet = session.getBoolean(BIOMETRIC_STATUS)
        val username = session.getString(LoginActivity.USERNAME)
        val pass = session.getString(LoginActivity.PASS)
        session.clearAll()
        session.setValue(BIOMETRIC_STATUS, bioSet)
        session.setValue(LoginActivity.USERNAME, username)
        session.setValue(LoginActivity.PASS, pass)

        /*session.setValue(LoginActivity.ID, 0)
        session.setValue(LoginActivity.EMAIL, "")
        session.setValue(LoginActivity.FIRST_NAME, "")
        session.setValue(LoginActivity.LAST_NAME, "")
        session.setValue(LoginActivity.GENDER, "")
        session.setValue(LoginActivity.IMAGE, "")
        session.setValue(CoreSession.PREF_UID, "")*/
        finishAffinity()
        openActivity<LoginActivity>()
    }

    private fun checkBiometricCapability() {
        val biometricManager = BiometricManager.from(this)
        val canUseBiometric =
            when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
                BiometricManager.BIOMETRIC_SUCCESS -> {
                    // App can authenticate using biometrics.
                    true
                }

                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                    // No biometric features available on this device.
                    false
                }

                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                    // Biometric features are currently unavailable.
                    false
                }

                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    // Prompts the user to create credentials that your app accepts.

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                            putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED, BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
                        }
                        activityLauncher.launch(enrollIntent) {
                            checkBiometricCapability()
                        }
                    }
                    false

                }

                else -> false
            }

        binding.swBiometric.isVisible = canUseBiometric
    }

    companion object {
        const val BIOMETRIC_STATUS = "biometric_status"
    }
}