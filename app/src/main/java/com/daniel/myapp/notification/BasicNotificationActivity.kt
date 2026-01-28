package com.daniel.myapp.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.crocodic.core.base.activity.CoreActivity
import com.crocodic.core.base.activity.NoViewModelActivity
import com.crocodic.core.extension.snacked
import com.crocodic.core.extension.text
import com.crocodic.core.extension.tos
import com.daniel.myapp.R
import com.daniel.myapp.app_tour.ui.home.HomeViewModel
import com.daniel.myapp.databinding.ActivityBasicNotificationBinding
import com.daniel.myapp.maps.MapsActivity
import com.daniel.myapp.notification.worker.InAppNotificationWorker
import com.daniel.myapp.notification.worker.NotificationWorker
import com.daniel.myapp.the_twin_binding.HomeActivity
import com.google.firebase.messaging.FirebaseMessaging
import java.util.concurrent.TimeUnit

class BasicNotificationActivity :
    CoreActivity<ActivityBasicNotificationBinding, HomeViewModel>(R.layout.activity_basic_notification) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkNotificationPermission()
        }

        binding.btnShowNotif.setOnClickListener {
            showNotification("Hallo Gais!", "Saya sedang belajar membuat notifikasi.")
        }

        binding.btnReqNotif.setOnClickListener {
            createWorkNotification()
        }

        binding.btnInAppNotif.setOnClickListener {
            createInAppNotification()
        }

        getFcmToken()
    }

    private fun getFcmToken() {
        generateFirebaseToken { FcmToken ->
            Log.d("firebase-token", FcmToken)
        }
    }

    private fun generateFirebaseToken(result: (String) -> Unit) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (!it.isSuccessful) {
                Log.w("firebase-token", "Fetching FCM registration token failed", it.exception)
                return@addOnCompleteListener
            }
            result(it.result)
        }
    }

    private fun createInAppNotification() {
        val input = binding.etCd.text.toString()
        if (input.isEmpty()) {
            tos("Isi Countdown dahulu")
            return
        }

        val inputSecond = input.toLong()
        val notificationWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<InAppNotificationWorker>()
                .setInitialDelay(inputSecond, TimeUnit.SECONDS)
                .build()

        WorkManager.getInstance(this).enqueue(notificationWorkRequest)

        showCountDown(inputSecond)
    }

    private fun showCountDown(countdown: Long) {
        object : CountDownTimer(TimeUnit.SECONDS.toMillis(countdown), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvCd.text("${millisUntilFinished / 1000}")
            }

            override fun onFinish() {}
        }.start()
    }

    private fun createWorkNotification() {
        val notificationWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(15, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(this).enqueue(notificationWorkRequest)

        tos("Aplikasi tertutup dan tunggu 15 detik ya")

        finishAffinity()
    }

    private fun showNotification(title: String, content: String) {

        createNotificationChannel()

        val intent = Intent(this, MapsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_circle_notification)
            .setContentTitle(title)
            .setContentText(content)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@BasicNotificationActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(1, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESCRIPTION
            }

            // Register the channel with the system.
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkNotificationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
            }

            shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                binding.root.snacked(
                    message = R.string.lbl_permission_notification,
                    action = R.string.lbl_setting,
                    listener = {
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        intent.data = Uri.fromParts("package", packageName, null)
                        startActivity(intent)
                    }
                )
            }

            else -> {
                // You can directly ask for the permission.
                requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 100)
            }
        }
    }

    companion object {
        const val CHANNEL_ID = "Kelas Industri"
        const val CHANNEL_NAME = "Kelas Industri Android"
        const val CHANNEL_DESCRIPTION = "Kelas Industri Android Level Middle"
    }
}