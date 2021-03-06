package com.dhairya.clock

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {

    lateinit var clock: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setStatusBarTransparent()
//        createNotificationChannel()
        supportActionBar?.hide()

        val imgview = findViewById<ImageView>(R.id.image)

        val animationScale = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        imgview.startAnimation(animationScale)
        Handler().postDelayed({
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
            }
            finish()
        }, 500)

    }

    private fun setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT in 19..20) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val winParameters = window.attributes
        if (on) {
            winParameters.flags = winParameters.flags or bits
        } else {
            winParameters.flags = winParameters.flags and bits.inv()
        }
        window.attributes = winParameters
    }

//    private fun createNotificationChannel() {
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val description = "Channel for sending notes notification"
//            val importance = NotificationManager.IMPORTANCE_HIGH
//            val channel = NotificationChannel(channelId, channelName, importance)
//            channel.description = description
//            channel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
//            channel.enableVibration(true)
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
}

//const val channelId = "alarmChannel"
//const val channelName = "Alarm Channel"